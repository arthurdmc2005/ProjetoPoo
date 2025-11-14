package br.barbearia.Financeiro.repository;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroDeVendasRepository {

    ProdutosRepository produtosRepository = new ProdutosRepository("BarbeariaComMaven/Produtos.JSON");


    private GerenciadorJSON<RegistroDeVendas> gerenciadorJSON;

    private List<RegistroDeVendas> listaDeVendas;

    public RegistroDeVendasRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<RegistroDeVendas>>() {
        });

        this.listaDeVendas = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeVendas));
    }

    public List<Produtos> produtosDisponiveis = new ArrayList<>(produtosRepository.listarProdutos());
    public List<RegistroDeVendas> registroDeVendasList = new ArrayList<>();


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

    public RegistroDeVendas buscarVendaRegistrada(String nomeDoProduto){
        for(RegistroDeVendas registroDeVendas : listaDeVendas){
            if(registroDeVendas.getNomeProduto().equalsIgnoreCase(nomeDoProduto)){
                return registroDeVendas;
            }
        }
        return null;
    }

    public RegistroDeVendas emitirExtratoVendas(String nomeDoProduto){
        RegistroDeVendas vendaEncontrada = buscarVendaRegistrada(nomeDoProduto);

        if(vendaEncontrada == null){
            System.out.printf("Nenhuma venda encontrada");
            return null;
        }

        return vendaEncontrada;
    }


}
