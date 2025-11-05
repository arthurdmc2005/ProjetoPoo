package br.barbearia.agendamento.service;


import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.model.Usuarios;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * O "Gerente" (Cérebro) que cuida de toda a lógica de agendamento.
 * Ele "conversa" com todos os almoxarifes (Repositórios).
 */
public class AgendamentoServices {

    private AgendamentoRepository agendamentoRepository;
    private UsuarioRepository usuarioRepository;
    private ServicosRepository servicosRepository;

    /**
     * O "Contrato de Trabalho" do Gerente.
     * Para contratá-lo (no Controller ou main),
     * você DEVE entregar os 3 repositórios para ele.
     */
    public AgendamentoServices(AgendamentoRepository agendamentoRepository, UsuarioRepository usuarioRepository, ServicosRepository servicosRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicosRepository = servicosRepository;
    }

    /**
     * Calcula os horários livres para um dia específico.
     * (Esta é a lógica que você descreveu)
     *
     * @param dataSelecionada O dia que o usuário escolheu na tela.
     * @return Uma Lista de LocalTime (só os horários livres).
     */
    public List<LocalTime> buscarHorariosDisponiveis(LocalDate dataSelecionada) {


        List<LocalTime> agendaCompleta = new ArrayList<>();
        agendaCompleta.add(LocalTime.of(8, 0));  // 08:00
        agendaCompleta.add(LocalTime.of(9, 0));  // 09:00
        agendaCompleta.add(LocalTime.of(10, 0)); // 10:00
        agendaCompleta.add(LocalTime.of(11, 0)); // 11:00
        //Almoço
        agendaCompleta.add(LocalTime.of(13, 0)); // 13:00
        agendaCompleta.add(LocalTime.of(14, 0)); // 14:00
        agendaCompleta.add(LocalTime.of(15, 0)); // 15:00
        agendaCompleta.add(LocalTime.of(16, 0)); // 16:00
        agendaCompleta.add(LocalTime.of(17, 0)); // 17:00
        agendaCompleta.add(LocalTime.of(18,0));  // 18:00

        System.out.println("LOG [Service]: Template da agenda criado com " + agendaCompleta.size() + " horários.");


        List<Agendamento> agendamentosFeitos = agendamentoRepository.buscarPorData(dataSelecionada);
        System.out.println("LOG [Service]: Encontrados " + agendamentosFeitos.size() + " agendamentos já feitos.");


        List<LocalTime> horasOcupadas = new ArrayList<>();
        for (Agendamento ag : agendamentosFeitos) {
            horasOcupadas.add(ag.getHora()); // Ex: 09:00, 11:00
        }

        agendaCompleta.removeAll(horasOcupadas);

        System.out.println("LOG [Service]: Retornando " + agendaCompleta.size() + " horários disponíveis.");
        return agendaCompleta;
    }


    /**
     * Cria e salva um novo agendamento, após validar tudo.
     */
    public Agendamento salvarNovoAgendamento(LocalDate data, LocalTime hora, int clienteId, int servicoId, int funcionarioId) throws Exception {

        System.out.println("LOG [Service]: Recebido pedido de agendamento. Validando...");


        Usuarios cliente = usuarioRepository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new Exception("Cliente com ID " + clienteId + " não encontrado.");
        }

        Servicos servico = servicosRepository.buscarPorId(servicoId);
        if (servico == null) {
            throw new Exception("Serviço com ID " + servicoId + " não encontrado.");
        }

        Usuarios funcionario = usuarioRepository.buscarPorId(funcionarioId);
        if (funcionario == null) {
            throw new Exception("Funcionário com ID " + funcionarioId + " não encontrado.");
        }

        if (!buscarHorariosDisponiveis(data).contains(hora)) {
            throw new Exception("ERRO: O horário " + hora + " não está mais disponível. Por favor, escolha outro.");
        }

        System.out.println("LOG [Service]: Validações OK. Montando objeto...");

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setData(data);
        novoAgendamento.setHora(hora);
        novoAgendamento.setClienteId(clienteId);
        novoAgendamento.setServicoId(servicoId);
        novoAgendamento.setFuncionarioId(funcionarioId);
        novoAgendamento.setStatus("AGENDADO");

        novoAgendamento.setValorCobrado(servico.getValor());

        agendamentoRepository.adicionarAgendamento(novoAgendamento);

        System.out.println("LOG [Service]: Agendamento salvo com ID " + novoAgendamento.getId());
        return novoAgendamento;
    }
}