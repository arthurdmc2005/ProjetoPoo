package br.barbearia.admin;

import br.barbearia.model.Usuarios; // Importe sua classe
import br.barbearia.repository.UsuarioRepository; // Importe seu repositório
import br.barbearia.repository.ClientesRepository;
import br.barbearia.repository.FuncionariosRepository;
import br.barbearia.model.Cliente;

class TesteLogin {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO TESTE COM JSON ---");


        UsuarioRepository repositorioDeUsuarios = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");



        if (repositorioDeUsuarios.buscarPorLogin("admin") == null) {

            System.out.println("Usuário 'admin' não encontrado. Criando agora...");

            Usuarios admin = new Usuarios("admin", "senha123");


            repositorioDeUsuarios.adicionarUsuario(admin);

            System.out.println("Usuário 'admin' salvo no JSON.");

        } else {
            System.out.println("Usuário 'admin' já foi carregado do JSON.");
        }

        System.out.println("--- TESTE FINALIZADO ---");

        }


    }

