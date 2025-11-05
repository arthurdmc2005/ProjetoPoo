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
            usuarioServices.cadastrarUsuario("marciobundadura","01973214611","31998777425","Cliente","mameigostosoopelli","fodasemerda");


        } catch (Exception e) {
            System.out.println("Falha no cadastro");
            throw new RuntimeException(e);
        }
        try{
            usuarioServices.removerUsuario(1);
        } catch (Exception e) {
            System.out.printf("deu merda");
            throw new RuntimeException(e);
        }
        try{
            usuarioServices.atualizarUsuarioNaLista();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
