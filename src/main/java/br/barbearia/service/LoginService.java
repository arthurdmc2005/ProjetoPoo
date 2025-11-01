package br.barbearia.service;


import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;

public class LoginService {

    private UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuarios cadastrarUsuario(String nome, String cpf, String telefone, String senha,String login)throws Exception{
        Usuarios usuarioParaCadastrar = new Usuarios();
        usuarioParaCadastrar.setNome(nome);
        usuarioParaCadastrar.setCpf(cpf);
        usuarioParaCadastrar.setTelefone(telefone);
        usuarioParaCadastrar.setLogin(login);
        usuarioParaCadastrar.setSenhaHash(senha);

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
        System.out.println("Todas as validações foram atendidas. Cliente salvo.");
        usuarioRepository.adicionarUsuario(novoUsuario);
        return novoUsuario;


    }


}
