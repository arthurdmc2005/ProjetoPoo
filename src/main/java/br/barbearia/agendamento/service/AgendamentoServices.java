package br.barbearia.agendamento.service;


import br.barbearia.agendamento.Memento.AgendamentoCaraTaker;
import br.barbearia.agendamento.Memento.AgendamentoMemento;
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.model.Usuarios;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviço (Service Layer) responsável pela lógica de negócio
 * principal relacionada a Agendamentos.
 * <p>
 * O "Gerente" (Cérebro) que cuida de toda a lógica de agendamento.
 * Ele "conversa" com todos os almoxarifes (Repositórios) para validar
 * e criar novos agendamentos, bem como gerenciar seus estados (finalizar,
 * desfazer) usando o padrão Memento.
 * </p>
 *
 * @see br.barbearia.agendamento.repository.AgendamentoRepository
 * @see br.barbearia.repository.UsuarioRepository
 * @see br.barbearia.agendamento.repository.ServicosRepository
 * @see br.barbearia.agendamento.repository.EstacaoRepository
 * @see br.barbearia.agendamento.Memento.AgendamentoCaraTaker
 */
public class AgendamentoServices {

    /** Repositório para acesso aos dados de agendamentos. */
    private AgendamentoRepository agendamentoRepository;

    /** Repositório para acesso aos dados de usuários (clientes, funcionários). */
    private UsuarioRepository usuarioRepository;

    /** Repositório para acesso aos dados de serviços. */
    private ServicosRepository servicosRepository;

    /** Repositório para gerenciamento das estações de atendimento. */
    private EstacaoRepository estacaoRepository;

    /** Zelador (Caretaker) do padrão Memento, para salvar/restaurar estados. */
    private AgendamentoCaraTaker caraTaker;


    /**
     * Construtor da classe de serviço de agendamento.
     * <p>
     * Realiza a injeção de todas as dependências (repositórios e o Caretaker)
     * necessárias para a operação desta classe.
     * </p>
     *
     * @param agendamentoRepository Repositório de agendamentos.
     * @param usuarioRepository Repositório de usuários.
     * @param servicosRepository Repositório de serviços.
     * @param estacaoRepository Repositório de estações.
     * @param caraTaker O Zelador (Caretaker) do padrão Memento.
     */
    public AgendamentoServices(AgendamentoRepository agendamentoRepository, UsuarioRepository usuarioRepository, ServicosRepository servicosRepository, EstacaoRepository estacaoRepository,AgendamentoCaraTaker caraTaker) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicosRepository = servicosRepository;
        this.estacaoRepository = estacaoRepository;
        this.caraTaker = caraTaker;

    }

    /**
     * Calcula os horários livres para um dia específico.
     * <p>
     * O método cria uma lista de todos os horários de atendimento padrões,
     * busca os agendamentos já existentes para {@code dataSelecionada},
     * e remove os horários ocupados da lista completa.
     * </p>
     *
     * @param dataSelecionada O dia ({@link LocalDate}) que o usuário
     * deseja verificar.
     * @return Uma {@link List} de {@link LocalTime} contendo apenas
     * os horários disponíveis.
     */
    public List<LocalTime> buscarHorariosDisponiveis(LocalDate dataSelecionada) {


        List<LocalTime> agendaCompleta = new ArrayList<>();
        agendaCompleta.add(LocalTime.of(8, 0));
        agendaCompleta.add(LocalTime.of(9, 0));
        agendaCompleta.add(LocalTime.of(10, 0));
        agendaCompleta.add(LocalTime.of(11, 0));
        //12:00 horário utilizado pra almoço ( simulação - gustavo)
        agendaCompleta.add(LocalTime.of(13, 0));
        agendaCompleta.add(LocalTime.of(14, 0));
        agendaCompleta.add(LocalTime.of(15, 0));
        agendaCompleta.add(LocalTime.of(16, 0));
        agendaCompleta.add(LocalTime.of(17, 0));
        agendaCompleta.add(LocalTime.of(18,0));

        System.out.println("LOG [Service]: Template da agenda criado com " + agendaCompleta.size() + " horários.");


        List<Agendamento> agendamentosFeitos = agendamentoRepository.buscarPorData(dataSelecionada);
        System.out.println("LOG [Service]: Encontrados " + agendamentosFeitos.size() + " agendamentos já feitos.");


        List<LocalTime> horasOcupadas = new ArrayList<>();
        for (Agendamento ag : agendamentosFeitos) {
            horasOcupadas.add(ag.getHora());
        }

        agendaCompleta.removeAll(horasOcupadas);

        System.out.println("LOG [Service]: Retornando " + agendaCompleta.size() + " horários disponíveis.");
        return agendaCompleta;
    }


    /**
     * Cria e salva um novo agendamento após validar todas as regras de negócio.
     * <p>
     * Validações executadas:
     * 1. Verifica se Cliente, Serviço e Funcionário existem.
     * 2. Verifica se o horário ainda está disponível (usando
     * {@link #buscarHorariosDisponiveis}).
     * 3. Tenta ocupar a estação de atendimento.
     * </p>
     * Se todas as validações passarem, cria o objeto {@link Agendamento},
     * define seu valor com base no serviço e o salva no repositório.
     *
     * @param data        A data desejada para o agendamento.
     * @param hora        A hora desejada para o agendamento.
     * @param clienteId   O ID do cliente.
     * @param servicoId   O ID do serviço.
     * @param funcionarioId O ID do funcionário.
     * @param estacaoId   O ID da estação a ser ocupada.
     * @return O objeto {@link Agendamento} recém-criado e salvo.
     * @throws Exception Se qualquer validação falhar (ex: cliente não
     * encontrado, horário ocupado, estação ocupada).
     */
    public Agendamento salvarNovoAgendamento(LocalDate data, LocalTime hora, int clienteId, int servicoId, int funcionarioId, int estacaoId) throws Exception {

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

        boolean ocupou = estacaoRepository.ocuparEstacao(estacaoId);
        if(!ocupou){
            throw new Exception("A estação" + estacaoId + "já está ocupada");
        }

        System.out.println("LOG [Service]: Validações OK. Montando objeto...");

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setData(data);
        novoAgendamento.setHora(hora);
        novoAgendamento.setClienteId(clienteId);
        novoAgendamento.setServicoId(servicoId);
        novoAgendamento.setEstacaoNumero(estacaoId);
        novoAgendamento.setFuncionarioId(funcionarioId);
        novoAgendamento.setStatus("AGENDADO");

        novoAgendamento.setValorCobrado(servico.getValor());


        agendamentoRepository.adicionarAgendamento(novoAgendamento);

        System.out.println("LOG [Service]: Agendamento salvo com ID " + novoAgendamento.getId());
        return novoAgendamento;
    }

    /**
     * Finaliza um agendamento que estava em andamento.
     * <p>
     * Esta ação:
     * 1. Salva o estado atual (antes de finalizar) no {@link AgendamentoCaraTaker}
     * (para permitir o "desfazer").
     * 2. Muda o status do agendamento para "Finalizado".
     * 3. Atualiza o agendamento no repositório.
     * 4. Libera a estação de atendimento que estava em uso.
     * </p>
     *
     * @param agendamentoId O ID do agendamento a ser finalizado.
     * @throws Exception Se o agendamento com o {@code agendamentoId}
     * não for encontrado.
     */
    public void finalizarAgendamento(int agendamentoId)throws Exception{
        Agendamento agendamento = agendamentoRepository.buscarPorId(agendamentoId);
        if(agendamento == null){
            throw new Exception("Agendamento não encontrado");
        }
        caraTaker.salvar(agendamento.salvarEstado());

        agendamento.setStatus("Finalizado");

        agendamentoRepository.atualizarAgendamento(agendamento);

        boolean liberou = estacaoRepository.liberarEstacao(agendamento.getEstacaoNumero());
        if(liberou){
            System.out.printf("Estação liberada");
        }else{
            System.out.println("Estação já está livre");
        }
    }

    /**
     * Desfaz a última ação de "finalizar agendamento" usando o padrão Memento.
     * <p>
     * Esta ação:
     * 1. Busca o agendamento pelo ID.
     * 2. Pede ao {@link AgendamentoCaraTaker} o último {@link AgendamentoMemento}
     * salvo.
     * 3. Restaura o estado do agendamento (status e estação) para o
     * estado salvo no Memento.
     * 4. Atualiza o agendamento no repositório.
     * </p>
     *
     * @param id O ID do agendamento que teve sua finalização desfeita.
     * @throws Exception Se o agendamento não for encontrado ou se
     * não houver estado anterior salvo no Caretaker.
     */
    public void desfazerFinalizacao(int id)throws Exception{
        Agendamento agendamento = agendamentoRepository.buscarPorId(id);
        if(agendamento == null) throw new Exception("Agendamento não encontrado");

        AgendamentoMemento anterior = caraTaker.desfazer();
        if(anterior == null)throw new Exception("Nenhum estado anterior salvo");

        agendamento.restaurarEstado(anterior);


        agendamentoRepository.atualizarAgendamento(agendamento);
        System.out.println("Estado restaurado");
    }



}