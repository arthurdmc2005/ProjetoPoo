package br.barbearia.test;

import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.AgendamentoServices;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.*;

public class TestAgendamento {

    public static void main(String[] args) {

        AgendamentoRepository agendamentoRepository = new AgendamentoRepository("BarbeariaComMaven/Agendamento.JSON");
        ServicosRepository servicosRepository = new ServicosRepository("BarbeariaComMaven/Servicos.JSON");
        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        AgendamentoServices agendamentoServices = new AgendamentoServices(agendamentoRepository,usuarioRepository,servicosRepository);


        try{
            System.out.println("TESTE: BUSCANDO HORÁRIOS");
            List<LocalTime> horarios = agendamentoServices.buscarHorariosDisponiveis(LocalDate.now());

            System.out.println("Horários encontrados: " + horarios);


        } catch (Exception e) {
            System.out.printf("FALHOU");
            throw new RuntimeException(e);
        }
    }
}