package br.barbearia.admin;

import br.barbearia.model.Usuarios; // Importe sua classe
import br.barbearia.repository.ServicosRepository;
import br.barbearia.repository.UsuarioRepository; // Importe seu repositório


class TesteLogin {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO TESTE COM JSON ---");


        UsuarioRepository repositorioDeUsuarios = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");


        Usuarios usuarioParaCadastrar = new Usuarios();

        String nomeDigitado = "Arthur";
        String cpfDigitado = "12345678900"; // CPF com 11 dígitos
        String telefoneDigitado = "11999998888";
        String loginDigitado = "arthur";
        String senhaDigitada = "senha123";

        usuarioParaCadastrar.setNome(nomeDigitado);
        usuarioParaCadastrar.setCpf(cpfDigitado);
        usuarioParaCadastrar.setTelefone(telefoneDigitado);
        usuarioParaCadastrar.setLogin(loginDigitado);
        usuarioParaCadastrar.setSenhaHash(senhaDigitada);

        try {
            System.out.printf("Entregando dados ao service para validação");

            repositorioDeUsuarios.adicionarUsuario(usuarioParaCadastrar);

            System.out.println("Usuário Cadastrado!");
        }catch (Exception e){
            System.out.printf("Falha no cadastro" + e.getMessage());
        }





    }


    }

