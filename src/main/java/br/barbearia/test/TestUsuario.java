package br.barbearia.test;

import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.*;
import br.barbearia.model.*;

import java.util.Comparator;
import java.util.List;

public class TestUsuario {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);

        System.out.println("TESTANDO O CADASTRO DE USUARIOS NO JSON");

        try{
            List<Usuarios> listaParaOrdenar = usuarioRepository.listaDeUsuarios();
            System.out.println("Lista antes: " + listaParaOrdenar);
            Comparator<Usuarios> ordenadorDeNomes = new CompareNameCliente();
            listaParaOrdenar.sort(ordenadorDeNomes);
            System.out.printf("Lista Depois: " + listaParaOrdenar);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
