package br.barbearia.Financeiro.service;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import java.time.LocalDate;

public class RegistroDeVendasServices {

    private RegistroDeVendasRepository registroDeVendasRepository;
    private UsuarioRepository usuarioRepository;

    public RegistroDeVendasServices(RegistroDeVendasRepository registroDeVendasRepository, UsuarioRepository usuarioRepository){
        this.registroDeVendasRepository = registroDeVendasRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public RegistroDeVendas registrarVenda(String nomeProduto, LocalDate dataSaida, int usuarioId, double valor, double quantidadeVendida) throws Exception {

        Usuarios usuario = usuarioRepository.buscarPorId(usuarioId);
        if (usuario == null) {
            throw new Exception("Usuário com ID " + usuarioId + " não encontrado.");
        }


        Produtos produtoEncontrado = null;
        for (Produtos produto : registroDeVendasRepository.produtosDisponiveis) {
            if (produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                produtoEncontrado = produto;
                break;
            }
        }

        if (produtoEncontrado == null) {
            throw new Exception("Produto '" + nomeProduto + "' não encontrado.");
        }


        RegistroDeVendas novaVenda = new RegistroDeVendas();
        novaVenda.setNomeProduto(produtoEncontrado.getNomeProduto());
        novaVenda.setSaida(dataSaida);
        novaVenda.setClienteId(usuario.getId());
        novaVenda.setValor(valor);
        novaVenda.setQuantidadeVendida(quantidadeVendida);


        registroDeVendasRepository.registrarVenda(novaVenda);

        System.out.println("Venda registrada com sucesso para o produto " + nomeProduto);

        return novaVenda;
    }
}
