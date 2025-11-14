package br.barbearia.Financeiro.repository;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ProdutosRepository {

    private GerenciadorJSON<Produtos> gerenciadorJSON;

    private List<Produtos> listaDeProdutosEstoque;

    public ProdutosRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Produtos>>() {
        });

        this.listaDeProdutosEstoque = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeProdutosEstoque));
    }


    private int proximoId() {
        int maxId = 0;
        for (Produtos produtos : listaDeProdutosEstoque) {
            if (produtos.getId() > maxId) {
                maxId = produtos.getId();
            }
        }
        return maxId + 1;
    }

    public void adicionarProdutoAoEstoque(Produtos produtoParaAdicionar){
        for(Produtos produto : listaDeProdutosEstoque){
            if(produto.getNomeProduto().equalsIgnoreCase(produtoParaAdicionar.getNomeProduto())){
                produto.setQuantidade(produto.getQuantidade() + produtoParaAdicionar.getQuantidade());
            }
        }
        listaDeProdutosEstoque.add(produtoParaAdicionar);
    }

    public void removerQuantidadeDoProduto(String nomeDoProduto, double quantidadeParaRemover){
        for(Produtos produto :listaDeProdutosEstoque){
            if(produto.getNomeProduto()!=null && produto.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                double novaQuantidade = produto.getQuantidade()-quantidadeParaRemover;
                produto.setQuantidade(novaQuantidade);
                break;
            }
        }
    }
    public void adicionarQuantidadeDoProduto(String nomeDoProduto, double quantidadeParaAdicionar){
        for(Produtos produtos : listaDeProdutosEstoque){
            if(produtos.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                double novaQuantidade = produtos.getQuantidade() + quantidadeParaAdicionar;
                produtos.setQuantidade(novaQuantidade);
                break;
            }
        }
    }
    public void atualizarProdutoNoEstoque(Produtos novoProduto){
        for(int i = 0; i<listaDeProdutosEstoque.size();i++){
            Produtos estoqueAntigo = listaDeProdutosEstoque.get(i);
            if(novoProduto.getId()==estoqueAntigo.getId()){}
                listaDeProdutosEstoque.set(i,novoProduto);
            return;

        }
    }

    public Produtos buscarProdutoPorNome(String produtoBuscado){
        for(Produtos estoqueAtual : listaDeProdutosEstoque){
            if(estoqueAtual.getNomeProduto().equalsIgnoreCase(produtoBuscado)){
                return estoqueAtual;
            }
        }
        return null;
    }

    public Produtos buscarProdutoPorId(int idBuscado){
        for(Produtos estoqueAtual : listaDeProdutosEstoque){
            if(estoqueAtual.getId()==idBuscado){
                return estoqueAtual;
            }
        }
        return null;
    }


    public List<Produtos> listarProdutos() {
        return listaDeProdutosEstoque;
    }




}





