package br.barbearia.admin;

// Importe todas as suas "ferramentas"
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.service.AgendamentoServices;
import br.barbearia.model.*;

// Importe as "peças" (LocalDate, LocalTime)
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Sistema {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO TESTE DO AGENDAMENTO SERVICE ---");


        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        ServicosRepository servicosRepository = new ServicosRepository("BarbeariaComMaven/Servicos.JSON");
        AgendamentoRepository agendamentoRepository = new AgendamentoRepository("BarbeariaComMaven/Agendamentos.JSON");

        System.out.println("LOG: Repositórios criados.");


        AgendamentoServices agendamentoService = new AgendamentoServices(
                agendamentoRepository,
                usuarioRepository,
                servicosRepository
        );

        System.out.println("LOG: Service de Agendamento criado.");



        try {

            LocalDate diaParaTestar = LocalDate.of(2025, 11, 25);
            System.out.println("\nBuscando horários livres para " + diaParaTestar + "...");


            List<LocalTime> horarios = agendamentoService.buscarHorariosDisponiveis(diaParaTestar);

            System.out.println("Horários encontrados: " + horarios);


            System.out.println("\nTentando salvar um novo agendamento...");

            Agendamento novoAg = agendamentoService.salvarNovoAgendamento(
                    diaParaTestar,      // A Data
                    LocalTime.of(9, 0),
                    1,
                    1,
                    2
            );

            System.out.println("SUCESSO! Agendamento salvo com ID: " + novoAg.getId());

        } catch (Exception e) {

            System.out.println("TESTE FALHOU: " + e.getMessage());
            e.printStackTrace();
        }
    }
}