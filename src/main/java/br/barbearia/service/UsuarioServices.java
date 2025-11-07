package br.barbearia.service;


import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;

import java.rmi.server.ExportException;

public class UsuarioServices {

    private UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuarios cadastrarUsuario(String nome, String cpf, String telefone,String tipo, String senha,String login)throws Exception{
        Usuarios usuarioParaCadastrar = new Usuarios();
        usuarioParaCadastrar.setNome(nome);
        usuarioParaCadastrar.setCpf(cpf);
        usuarioParaCadastrar.setTelefone(telefone);
        usuarioParaCadastrar.setLogin(login);
        usuarioParaCadastrar.setSenhaHash(senha);
        usuarioParaCadastrar.setTipo(tipo);

        return this.cadastrarUsuario(usuarioParaCadastrar);
    }

    public Usuarios cadastrarUsuario(Usuarios novoUsuario)throws Exception {
        if (novoUsuario.getNome() == null || novoUsuario.getNome().trim().isEmpty()) {
            throw new Exception("Existem campos obrigátorios que não foram preenchidos");
        }
        if (novoUsuario.getCpf() == null || novoUsuario.getCpf().replaceAll("[^0-9]", "").length() != 11) {
            throw new Exception("Coloque o formato correto para CPF");
        }
        if (novoUsuario.getTelefone() == null || novoUsuario.getTelefone().trim().isEmpty()) {
            throw new Exception("Preencha o campo TELEFONE");
        }
        if (usuarioRepository.buscarUsuarioPorCpf(novoUsuario.getCpf()) != null) {
            throw new Exception("Este CPF já está cadastrado");
        }

        String tipoDoUsuario = novoUsuario.getTipo();
        if(novoUsuario.getTipo()==null && novoUsuario.getTipo().trim().isEmpty()){
            throw new Exception("Preencha o tipo de Usuário");
        }

        boolean tipoValido = tipoDoUsuario.equalsIgnoreCase("Gerente")
                || tipoDoUsuario.equalsIgnoreCase("Cliente")
                || tipoDoUsuario.equalsIgnoreCase("Funcionario");
        if(!tipoValido){
            throw new Exception("Usuário Inválido");
        }


        System.out.println("Todas as validações foram atendidas. Cliente salvo.");
        usuarioRepository.adicionarUsuario(novoUsuario);
        return novoUsuario;



    }

    public void removerUsuario(int idParaRemover)throws Exception{
        if(idParaRemover<=0) {
            throw new Exception("Id inválido");
        }
            Usuarios usuarioParDeletar = usuarioRepository.buscarPorId(idParaRemover);

            if(usuarioParDeletar==null){
                throw new Exception("Usuário não encontrado");



            }

        usuarioRepository.removerUsuarioPeloId(idParaRemover);


    }

    public Usuarios buscarUsuarioPorCpf(String cpfBuscado)throws Exception{

        if(cpfBuscado==null || cpfBuscado.trim().isEmpty()){
            throw new Exception("Cpf nulo não pode bosta");
        }

        Usuarios usuarioEncontrado = usuarioRepository.buscarUsuarioPorCpf(cpfBuscado);

        if(usuarioEncontrado==null){
            throw new Exception("Não encontrei esse cpf");

        }

        return usuarioEncontrado;

    }

    public void atualizarUsuarioNaLista(Usuarios usuarioAtualizado)throws Exception{
        if(usuarioAtualizado.getNome()==null || usuarioAtualizado.getNome().trim().isEmpty()){
            throw new Exception("Coloca o nome merda");
        }
        Usuarios usuarioAntigo = usuarioRepository.buscarPorId(usuarioAtualizado.getId());

        if(usuarioAntigo==null){
            throw new Exception("cade o usuario imundo");
        }

        usuarioRepository.atualizarUsuarioNaLista(usuarioAtualizado);
        //volte aqui depois;
    }




}
