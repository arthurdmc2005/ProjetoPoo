package br.barbearia.test;

import br.barbearia.Loja.model.Produtos;
import br.barbearia.Loja.repository.ProdutosRepository;
import br.barbearia.Loja.service.ProdutosService;
import br.barbearia.agendamento.service.ServicesRoles;
import java.util.List;

public class TestProdutos {
    public static void main(String[] args) throws Exception {

        ProdutosRepository produtosRepository = new ProdutosRepository("BarbeariaComMaven/Produtos.JSON");
        ProdutosService produtosService = new ProdutosService(produtosRepository);



}
}
