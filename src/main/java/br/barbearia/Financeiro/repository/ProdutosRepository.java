package br.barbearia.Financeiro.repository;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Repositório para gerenciamento (CRUD) de {@link Produtos} no estoque.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados dos produtos,
 * utilizando um {@link GerenciadorJSON} para carregar e persistir a
 * lista de produtos de/para um arquivo JSON.
 * </p>
 *
 * @see Produtos
 * @see br.barbearia.repository.GerenciadorJSON
 * @see br.barbearia.Financeiro.service.ProdutosService
 */
public class ProdutosRepository {

    /** O gerenciador genérico para manipulação do arquivo JSON de produtos. */
    private GerenciadorJSON<Produtos> gerenciadorJSON;

    /** A lista de produtos em estoque, carregada em memória. */
    private List<Produtos> listaDeProdutosEstoque;

    /**
     * Construtor do repositório de produtos.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de produtos existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/Produtos.JSON").
     */
    public ProdutosRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Produtos>>() {
        });

        this.listaDeProdutosEstoque = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeProdutosEstoque} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeProdutosEstoque));
    }


    /**
     * Encontra o próximo ID disponível para um novo produto.
     * <p>
     * Itera sobre a lista, encontra o ID mais alto em uso e retorna
     * esse valor + 1.
     * </p>
     *
     * @return O próximo ID inteiro disponível.
     */
    private int proximoId() {
        int maxId = 0;
        for (Produtos produtos : listaDeProdutosEstoque) {
            if (produtos.getId() > maxId) {
                maxId = produtos.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Adiciona um produto ao estoque.
     * <p>
     * Primeiro, itera pela lista e atualiza (soma) a quantidade de qualquer
     * produto existente com o mesmo nome (case-insensitive).
     * Em seguida, adiciona o {@code produtoParaAdicionar} à lista.
     * </p>
     * <p>
     * <b>Nota:</b> Esta lógica pode resultar em duplicatas se o
     * {@code produtoParaAdicionar} não tiver um ID 0 e for tratado
     * pela camada de serviço (como em {@code ProdutosService}).
     * </p>
     *
     * @param produtoParaAdicionar O {@link Produtos} a ser adicionado ou
     * cuja quantidade deve ser somada.
     */
    public void adicionarProdutoAoEstoque(Produtos produtoParaAdicionar){
        for(Produtos produto : listaDeProdutosEstoque){
            if(produto.getNomeProduto().equalsIgnoreCase(produtoParaAdicionar.getNomeProduto())){
                produto.setQuantidade(produto.getQuantidade() + produtoParaAdicionar.getQuantidade());
            }
        }
        listaDeProdutosEstoque.add(produtoParaAdicionar);
    }

    /**
     * Remove (subtrai) uma quantidade do estoque de um produto.
     * <p>
     * Encontra o produto pelo nome (case-insensitive) e subtrai
     * o valor da sua quantidade. Para assim que encontra o primeiro
     * produto correspondente.
     * </p>
     *
     * @param nomeDoProduto O nome do produto a ter o estoque diminuído.
     * @param quantidadeParaRemover A quantidade a ser subtraída.
     */
    public void removerQuantidadeDoProduto(String nomeDoProduto, double quantidadeParaRemover){
        for(Produtos produto :listaDeProdutosEstoque){
            if(produto.getNomeProduto()!=null && produto.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                double novaQuantidade = produto.getQuantidade()-quantidadeParaRemover;
                produto.setQuantidade(novaQuantidade);
                break;
            }
        }
    }

    /**
     * Adiciona (soma) uma quantidade ao estoque de um produto.
     * <p>
     * Encontra o produto pelo nome (case-insensitive) e soma
     * o valor à sua quantidade. Para assim que encontra o primeiro
     * produto correspondente.
     * </p>
     *
     * @param nomeDoProduto O nome do produto a ter o estoque aumentado.
     * @param quantidadeParaAdicionar A quantidade a ser somada.
     */
    public void adicionarQuantidadeDoProduto(String nomeDoProduto, double quantidadeParaAdicionar){
        for(Produtos produtos : listaDeProdutosEstoque){
            if(produtos.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                double novaQuantidade = produtos.getQuantidade() + quantidadeParaAdicionar;
                produtos.setQuantidade(novaQuantidade);
                break;
            }
        }
    }

    /**
     * Atualiza um produto existente na lista.
     * <p>
     * Procura o produto pelo ID e substitui a instância antiga
     * pela nova instância {@code novoProduto}.
     * </p>
     * <p>
     * <b>Nota:</b> A implementação atual substitui o item no índice 0
     * e retorna, independentemente da correspondência de ID.
     * A intenção provável era que a substituição ocorresse
     * dentro do bloco 'if'.
     * </p>
     *
     * @param novoProduto O objeto {@link Produtos} com os
     * dados atualizados (deve conter o ID do produto original).
     */
    public void atualizarProdutoNoEstoque(Produtos novoProduto){
        for(int i = 0; i<listaDeProdutosEstoque.size();i++){
            Produtos estoqueAntigo = listaDeProdutosEstoque.get(i);
            if(novoProduto.getId()==estoqueAntigo.getId()){}
            listaDeProdutosEstoque.set(i,novoProduto);
            return;

        }
    }

    /**
     * Busca um produto no estoque pelo seu nome (case-insensitive).
     *
     * @param produtoBuscado O nome do produto a ser procurado.
     * @return O objeto {@link Produtos} se encontrado, ou {@code null}
     * se não for encontrado.
     */
    public Produtos buscarProdutoPorNome(String produtoBuscado){
        for(Produtos estoqueAtual : listaDeProdutosEstoque){
            if(estoqueAtual.getNomeProduto().equalsIgnoreCase(produtoBuscado)){
                return estoqueAtual;
            }
        }
        return null;
    }

    /**
     * Busca um produto no estoque pelo seu ID.
     *
     * @param idBuscado O ID do produto a ser procurado.
     * @return O objeto {@link Produtos} se encontrado, ou {@code null}
     * se não for encontrado.
     */
    public Produtos buscarProdutoPorId(int idBuscado){
        for(Produtos estoqueAtual : listaDeProdutosEstoque){
            if(estoqueAtual.getId()==idBuscado){
                return estoqueAtual;
            }
        }
        return null;
    }


    /**
     * Retorna uma referência direta à lista de produtos em estoque.
     *
     * @return A {@link List} de {@link Produtos}.
     */
    public List<Produtos> listarProdutos() {
        return listaDeProdutosEstoque;
    }
}