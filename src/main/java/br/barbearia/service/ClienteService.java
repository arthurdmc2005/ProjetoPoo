package br.barbearia.service;

import br.barbearia.model.Cliente;
import br.barbearia.repository.ClientesRepository;

public class ClienteService  {

    private ClientesRepository clientesRepository = new ClientesRepository();

    public Cliente cadastrarCliente(Cliente novoCliente)throws Exception{
        if(novoCliente.getNome() == null ||novoCliente.getNome().trim().isEmpty()){
            throw new Exception("Existem campos obrigátorios que não foram preenchidos");
        }
        if(novoCliente.getCpf() == null || novoCliente.getCpf().replaceAll("[^0-9]", "").length()!=11){
            throw new Exception("Coloque o formato correto para CPF");
        }
        if(novoCliente.getTelefone() == null || novoCliente.getTelefone().trim().isEmpty()) {
            throw new Exception("Preencha o campo TELEFONE");
        }
        if(clientesRepository.buscarClientePorCpf(novoCliente.getCpf())!=null){
            throw new Exception("Este CPF já está cadastrado");
        }
        System.out.println("Todas as validações foram atendidas. Cliente salvo.");
        //return clientesRepository.salvarDados(novoCliente);
        return null;


    }


}
