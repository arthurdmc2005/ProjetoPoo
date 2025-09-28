package br.barbearia.admin;

import br.barbearia.usuarios.Pessoa;
import java.util.*;

public class GestaoClientes {


    private List<Pessoa> listaDeClientes;

    public GestaoClientes() {
        this.listaDeClientes = new ArrayList<>();
    }


    public void adicionarCliente(Pessoa novoCliente) {
        this.listaDeClientes.add(novoCliente);
    }


    public Pessoa buscarClientePorCpf(String cpf) {
        for (Pessoa cliente : this.listaDeClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean removerClientePorCpf(String cpf) {
        Pessoa clienteParaRemover = buscarClientePorCpf(cpf);
        if (clienteParaRemover != null) {
            this.listaDeClientes.remove(clienteParaRemover);
            return true;
        }
        return false;
    }

    public void listarTodosOsClientes() {
        for (Pessoa cliente : this.listaDeClientes) {

        }
    }

}