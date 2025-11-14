package br.barbearia.test;

import br.barbearia.agendamento.Memento.AgendamentoCaraTaker;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.AgendamentoServices;
import br.barbearia.repository.UsuarioRepository;

public class TestAgendamento {

    public static void main(String[] args) {

        AgendamentoRepository agendamentoRepository = new AgendamentoRepository("BarbeariaComMaven/Agendamento.JSON");
        ServicosRepository servicosRepository = new ServicosRepository("BarbeariaComMaven/Servicos.JSON");
        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        EstacaoRepository  estacaoRepository = new EstacaoRepository();
        AgendamentoCaraTaker caraTaker = new AgendamentoCaraTaker();
        AgendamentoServices agendamentoServices = new AgendamentoServices(agendamentoRepository,usuarioRepository,servicosRepository, estacaoRepository,caraTaker);


        try{







        } catch (Exception e) {
            System.out.printf("FALHOU");
            throw new RuntimeException(e);
        }
    }
}