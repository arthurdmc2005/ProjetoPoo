package br.barbearia.Loja.repository;

import br.barbearia.Loja.model.Produtos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosRepository {

    private ObjectMapper objectMapper;
    private File arquivoJson;

    List<Produtos> listaDeProdutosEstoque;

    public ProdutosRepository(String caminhoDoArquivo){
        this.objectMapper = new ObjectMapper();

        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.arquivoJson = new File(caminhoDoArquivo);

        this.listaDeProdutosEstoque = carregarDoJson();
    }
    private List<Produtos> carregarDoJson() {
        try {
            if (!arquivoJson.exists()) {
                return new ArrayList<>();
            }


            return objectMapper.readValue(arquivoJson, new TypeReference<List<Produtos>>() {});

        } catch (IOException e) {
            System.err.println("ERRO: Falha ao carregar JSON de serviços: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    private void salvarNoJson() {
        try {
            objectMapper.writeValue(arquivoJson, listaDeProdutosEstoque);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar JSON de serviços: " + e.getMessage());
        }
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
                salvarNoJson();
            }
        }
        listaDeProdutosEstoque.add(produtoParaAdicionar);
        salvarNoJson();
    }

    public void removerQuantidadeDoProduto(String nomeDoProduto, double quantidadeParaRemover){
        for(Produtos produto :listaDeProdutosEstoque){
            if(produto.getNomeProduto()!=null && produto.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                double novaQuantidade = produto.getQuantidade()-quantidadeParaRemover;
                produto.setQuantidade(novaQuantidade);
                salvarNoJson();
                break;
            }
        }
    }
    public void adicionarQuantidadeDoProduto(String nomeDoProduto, double quantidadeParaAdicionar){
        for(Produtos produtos : listaDeProdutosEstoque){
            if(produtos.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                double novaQuantidade = produtos.getQuantidade() + quantidadeParaAdicionar;
                produtos.setQuantidade(novaQuantidade);
                salvarNoJson();
                break;
            }
        }
    }
    public void atualizarProdutoNoEstoque(Produtos novoProduto){
        for(int i = 0; i<listaDeProdutosEstoque.size();i++){
            Produtos estoqueAntigo = listaDeProdutosEstoque.get(i);
            if(novoProduto.getId()==estoqueAntigo.getId()){}
                listaDeProdutosEstoque.set(i,novoProduto);
            salvarNoJson();
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





