package br.barbearia.test;

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
            usuarioServices.cadastrarUsuario("chrisdaocu","01973214679","31887566713","Cliente","aaaaaaa","aaaa");
            usuarioServices.cadastrarUsuario("chrismama","01973214678","31887566712","Cliente","aaaaaa","aaaaa");
            usuarioServices.cadastrarUsuario("marcinlixo","01973214676","31887566711","Cliente","aaaaa","aaaaaaa");
            usuarioRepository.salvarNoJson();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
