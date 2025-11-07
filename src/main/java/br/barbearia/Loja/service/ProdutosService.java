package br.barbearia.Loja.service;

import br.barbearia.Loja.model.Produtos;
import br.barbearia.Loja.repository.ProdutosRepository;
import br.barbearia.model.Usuarios;

public class ProdutosService {

    private ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository){
        this.produtosRepository = produtosRepository;
    }

    public Produtos cadastrarProdutoNoEstoque(String nomeDoProduto, double quantidadeDoProduto, String fornecedor)throws Exception{
        Produtos produtosParaCadastrar = new Produtos();
        produtosParaCadastrar.setNomeProduto(nomeDoProduto);
        produtosParaCadastrar.setFornecedor(fornecedor);
        produtosParaCadastrar.setQuantidade(quantidadeDoProduto);

        return this.cadastrarProdutoNoEstoque(produtosParaCadastrar);

    }

    public Produtos cadastrarProdutoNoEstoque(Produtos novoProduto)throws Exception{
        if(novoProduto.getNomeProduto()==null || novoProduto.getNomeProduto().trim().isEmpty()){
            throw new Exception("O nome do produto não pode estar vazio");
        }
        if(novoProduto.getFornecedor()==null || novoProduto.getFornecedor().trim().isEmpty()){
            throw new Exception("Informe o fornecedor. Caso ele não exista digite NENHUM");
        }
        if(novoProduto.getQuantidade()<0){
            throw new Exception("A quantidade não pode ser maior que 0");
        }
        if(novoProduto.getQuantidade()>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        System.out.println("Validado com sucesso");
        produtosRepository.adicionarProdutoAoEstoque(novoProduto);
        return novoProduto;

    }

    public void removerProdutoDoEstoque(String produtoAlterado, double quantidadeParaRemover)throws Exception{
        if(produtoAlterado == null || produtoAlterado.trim().isEmpty()){
            throw new Exception("Não existe produto no estoque");
        }
        if(quantidadeParaRemover>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        if(quantidadeParaRemover<0){
            throw new Exception("O numero para remover não pode ser negativo");
        }
        System.out.println("Validado com sucesso");
        produtosRepository.removerQuantidadeDoProduto(produtoAlterado,quantidadeParaRemover);
    }
    public void adicionarProdutoNoEstoque(String produtoAlterado, double quantidadeParaAdicionar)throws Exception{
        if(produtoAlterado == null || produtoAlterado.trim().isEmpty()){
            throw new Exception("Coloque o nome do produto a ser alterado");
        }
        if(quantidadeParaAdicionar<0){
            throw new Exception("A quantidade para ser adicionada tem que ser maior que 0");
        }
        if(quantidadeParaAdicionar>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        produtosRepository.adicionarQuantidadeDoProduto(produtoAlterado,quantidadeParaAdicionar);
        System.out.println("Validado com sucesso");
    }
    public Produtos buscarProdutoPorNome(String nomeDoProduto)throws Exception{
        if(nomeDoProduto==null || nomeDoProduto.trim().isEmpty()){
            throw new Exception("O produto buscado não pode ser vazio");
        }
        Produtos produtoBuscado = produtosRepository.buscarProdutoPorNome(nomeDoProduto);

        if(produtoBuscado==null){
            throw new Exception("Usuário não encontrado");
        }
        produtosRepository.buscarProdutoPorNome(nomeDoProduto);
        System.out.println("Validado com sucesso");
        return produtoBuscado;
    }


}
