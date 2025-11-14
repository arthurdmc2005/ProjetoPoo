package br.barbearia.test;

import br.barbearia.Financeiro.repository.ProdutosRepository;
import br.barbearia.Financeiro.service.ProdutosService;

public class TestProdutos {
    public static void main(String[] args) throws Exception {

        ProdutosRepository produtosRepository = new ProdutosRepository("BarbeariaComMaven/Produtos.JSON");
        ProdutosService produtosService = new ProdutosService(produtosRepository);



}
}
