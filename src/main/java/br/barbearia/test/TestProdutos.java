package br.barbearia.test;

import br.barbearia.Loja.model.Produtos;
import br.barbearia.Loja.repository.ProdutosRepository;
import br.barbearia.Loja.service.ProdutosService;
import br.barbearia.agendamento.service.ServicesRoles;

public class TestProdutos {

    public static void main(String[] args) {

        ProdutosRepository produtosRepository = new ProdutosRepository("BarbeariaComMaven/Produtos.JSON");
        ProdutosService produtosService = new ProdutosService(produtosRepository);

        try{
           Produtos produtoEncontrado = produtosService.buscarProdutoPorNome("Minox");
            System.out.println(produtoEncontrado);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o produto" + e.getMessage());
        }
    }
}
