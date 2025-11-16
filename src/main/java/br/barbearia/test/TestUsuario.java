package br.barbearia.test;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateDateAgendamento;
import br.barbearia.repository.GerenciadorJSON;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.*;
import br.barbearia.model.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Comparator;
import java.util.List;

public class TestUsuario {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);
        GerenciadorJSON gerenciadorJSON = new GerenciadorJSON<>("BarbeariaComMaven/Usuarios.JSON", new TypeReference<List<Usuarios >>() {
        });

        System.out.println("TESTANDO O CADASTRO DE USUARIOS NO JSON");

        try{
            List<Usuarios> listaDeUsuarios = usuarioRepository.listaDeUsuarios();

            Comparator<Usuarios> ordenacaoPorNome = new CompareNameCliente();

            listaDeUsuarios.sort(ordenacaoPorNome);
            System.out.println(listaDeUsuarios);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
