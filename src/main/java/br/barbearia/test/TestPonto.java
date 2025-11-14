package br.barbearia.test;

import br.barbearia.repository.GerenciarPontoRepository;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.GerenciarPontoService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestPonto {

    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        GerenciarPontoRepository gerenciarPontoRepository = new GerenciarPontoRepository("BarbeariaComMaven/RegistroPontos.JSON");
        GerenciarPontoService gerenciarPontoService = new GerenciarPontoService(gerenciarPontoRepository,usuarioRepository);


        try {

            gerenciarPontoService.registroPonto(3,"ENTRADA");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
