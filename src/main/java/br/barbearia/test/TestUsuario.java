package br.barbearia.test;

import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.*;
import br.barbearia.model.*;

public class TestUsuario {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);

        System.out.println("TESTANDO O CADASTRO DE USUARIOS NO JSON");

        try{
            usuarioServices.cadastrarUsuario("Marcos","01973214610","3199987774155","Funcionarioo","fodase123","admin");

        } catch (Exception e) {
            System.out.println("Falha no cadastro");
            throw new RuntimeException(e);
        }

    }
}
