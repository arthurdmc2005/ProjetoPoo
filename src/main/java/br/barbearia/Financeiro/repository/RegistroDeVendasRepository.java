package br.barbearia.Financeiro.repository;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório para gerenciamento (CRUD) de {@link RegistroDeVendas}.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados dos registros de vendas,
 * utilizando um {@link GerenciadorJSON} para carregar e persistir a
 * lista de vendas em um arquivo JSON.
 * </p>
 * <p>
 * Ela também interage com {@link ProdutosRepository} para verificar e
 * atualizar o estoque quando uma venda é registrada.
 * </p>
 *
 * @see RegistroDeVendas
 * @see ProdutosRepository
 * @see br.barbearia.repository.GerenciadorJSON
 */
public class RegistroDeVendasRepository {

    /**
     * Instância do repositório de produtos para verificar e abater o estoque.
     */
    ProdutosRepository produtosRepository = new ProdutosRepository("BarbeariaComMaven/Produtos.JSON");


    /** O gerenciador genérico para manipulação do arquivo JSON de vendas. */
    private GerenciadorJSON<RegistroDeVendas> gerenciadorJSON;

    /** A lista de registros de vendas, carregada em memória. */
    private List<RegistroDeVendas> listaDeVendas;

    /**
     * Construtor do repositório de registro de vendas.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de vendas existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/Vendas.JSON").
     */
    public RegistroDeVendasRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<RegistroDeVendas>>() {
        });

        this.listaDeVendas = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeVendas} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeVendas));
    }

    /**
     * Lista de produtos disponíveis no estoque, carregada do {@link ProdutosRepository}.
     * Usada para validação de vendas.
     */
    public List<Produtos> produtosDisponiveis = new ArrayList<>(produtosRepository.listarProdutos());

    /**
     * Lista de registros de vendas.
     * <b>Nota:</b> Esta lista parece ser uma duplicata de {@code listaDeVendas}.
     * Geralmente, apenas uma lista ({@code listaDeVendas}) seria necessária.
     */
    public List<RegistroDeVendas> registroDeVendasList = new ArrayList<>();


    /**
     * Registra uma nova venda no sistema.
     * <p>
     * Este método valida se o produto e o cliente são válidos. Em seguida,
     * verifica se há quantidade suficiente do produto em estoque
     * (consultando {@code produtosDisponiveis}).
     * </p>
     * <p>
     * Se a venda for válida, ele abate a quantidade do estoque e
     * adiciona a {@code novaVenda} à {@code listaDeVendas}.
     * </p>
     *
     * @param novaVenda O objeto {@link RegistroDeVendas} a ser processado.
     * @return A {@code novaVenda} se o registro for bem-sucedido, ou
     * {@code null} se a validação falhar (ex: estoque insuficiente).
     */
    public RegistroDeVendas registrarVenda(RegistroDeVendas novaVenda){
        if(novaVenda.getNomeProduto()==null && novaVenda.getClienteId()==0){
            System.out.println("Nome do produto ou Id de cliente inválido");
            return null;
        }
        for(Produtos produtos : produtosDisponiveis ){
            if(produtos.getNomeProduto().equalsIgnoreCase(novaVenda.getNomeProduto())){
                double quantidadeParaRemover = novaVenda.getQuantidadeVendida();

                if(produtos.getQuantidade() < quantidadeParaRemover){
                    System.out.println("Quantidade insuficiente");
                    return null;
                }
                double novaQuantidade = produtos.getQuantidade() - quantidadeParaRemover;
                produtos.setQuantidade(novaQuantidade);
                System.out.println("Venda efetuada");

                listaDeVendas.add(novaVenda);
                return novaVenda;
            }
        }

        return null;
    }

    /**
     * Busca um registro de venda pelo nome do produto (case-insensitive).
     * <p>
     * <b>Nota:</b> Retorna o *primeiro* registro encontrado que corresponda ao nome.
     * </p>
     *
     * @param nomeDoProduto O nome do produto a ser buscado.
     * @return O primeiro {@link RegistroDeVendas} encontrado, ou {@code null}
     * se nenhuma venda for encontrada para esse produto.
     */
    public RegistroDeVendas buscarVendaRegistrada(String nomeDoProduto){
        for(RegistroDeVendas registroDeVendas : listaDeVendas){
            if(registroDeVendas.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                return registroDeVendas;
            }
        }
        return null;
    }

    /**
     * Emite um extrato de venda (um único registro) com base no nome do produto.
     * <p>
     * Utiliza {@link #buscarVendaRegistrada(String)} para encontrar o registro.
     * </p>
     *
     * @param nomeDoProduto O nome do produto para o qual o extrato é desejado.
     * @return O {@link RegistroDeVendas} encontrado, ou {@code null} se
     * nenhuma venda for encontrada.
     */
    public RegistroDeVendas emitirExtratoVendas(String nomeDoProduto){
        RegistroDeVendas vendaEncontrada = buscarVendaRegistrada(nomeDoProduto);

        if(vendaEncontrada == null){
            System.out.printf("Nenhuma venda encontrada");
            return null;
        }

        return vendaEncontrada;
    }


}