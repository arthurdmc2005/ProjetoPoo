package br.barbearia.admin;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.model.Usuarios; // Importe sua classe
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.repository.UsuarioRepository; // Importe seu repositório


class TesteLogin {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO TESTE COM JSON ---");


        UsuarioRepository repositorioDeUsuarios = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");

        AgendamentoRepository repositorioDeServicos = new AgendamentoRepository("BarbeariaComMaven/Servicos.JSON");


        Usuarios usuarioParaCadastrar = new Usuarios();
        Agendamento servicoParaCadastrar = new Agendamento();

        try {
            System.out.printf("Entregando dados ao service para validação");

            repositorioDeUsuarios.adicionarUsuario(usuarioParaCadastrar);

            System.out.println("Usuário Cadastrado!");
        }catch (Exception e){
            System.out.printf("Falha no cadastro" + e.getMessage());
        }





    }


    }

