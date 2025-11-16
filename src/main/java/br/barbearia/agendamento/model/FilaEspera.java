package br.barbearia.agendamento.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Representa uma entrada na fila de espera do sistema.
 * <p>
 * Esta classe (POJO) armazena os dados de um cliente que solicitou um
 * agendamento em um horário indisponível e foi colocado em uma
 * fila para ser chamado caso o horário seja liberado.
 * </p>
 *
 * @see br.barbearia.agendamento.repository.FilaEsperaRepository
 * @see br.barbearia.agendamento.service.FilaEsperaService
 * @see ComparateFilaDeEspera
 */
public class FilaEspera {

    /** O identificador único para esta entrada na fila. */
    private int id;

    /** A chave estrangeira (ID) do cliente que está na fila. */
    private int clienteId;

    /** A chave estrangeira (ID) do serviço desejado pelo cliente. */
    private int servicoId;

    /** A data que o cliente originalmente desejava para o agendamento. */
    private LocalDate dataDesejada;

    /** A hora que o cliente originalmente desejava para o agendamento. */
    private LocalTime horaDesejada;

    /** O carimbo de data e hora exato de quando o cliente entrou na fila (usado para prioridade FIFO). */
    private LocalDateTime dataEntradafila;

    /**
     * Retorna uma representação textual completa do objeto FilaEspera.
     *
     * @return Uma String formatada com todos os campos da classe.
     */
    @Override
    public String toString() {
        return "FilaEspera{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", servicoId=" + servicoId +
                ", dataDesejada=" + dataDesejada +
                ", horaDesejada=" + horaDesejada +
                ", dataEntradafila=" + dataEntradafila +
                '}';
    }

    /**
     * Construtor completo para criar uma entrada na fila de espera.
     *
     * @param id O ID único da entrada.
     * @param clienteId O ID do cliente.
     * @param servicoId O ID do serviço desejado.
     * @param dataDesejada A data originalmente desejada.
     * @param horaDesejada A hora originalmente desejada.
     * @param dataEntradafila O carimbo de data/hora de entrada na fila.
     */
    public FilaEspera(int id, int clienteId, int servicoId, LocalDate dataDesejada, LocalTime horaDesejada, LocalDateTime dataEntradafila) {
        this.id = id;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataDesejada = dataDesejada;
        this.horaDesejada = horaDesejada;
        this.dataEntradafila = dataEntradafila;
    }

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public FilaEspera(){

    }

    /**
     * Retorna o ID do cliente.
     * @return O ID do cliente.
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * Define o ID do cliente.
     * @param clienteId O novo ID do cliente.
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Retorna o ID do serviço.
     * @return O ID do serviço.
     */
    public int getServicoId() {
        return servicoId;
    }

    /**
     * Define o ID do serviço.
     * @param servicoId O novo ID do serviço.
     */
    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    /**
     * Retorna o ID desta entrada na fila.
     * @return O ID da entrada.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID desta entrada na fila.
     * @param id O novo ID da entrada.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a data desejada pelo cliente.
     * @return A data desejada.
     */
    public LocalDate getDataDesejada() {
        return dataDesejada;
    }

    /**
     * Define a data desejada pelo cliente.
     * @param dataDesejada A nova data desejada.
     */
    public void setDataDesejada(LocalDate dataDesejada) {
        this.dataDesejada = dataDesejada;
    }

    /**
     * Retorna a hora desejada pelo cliente.
     * @return A hora desejada.
     */
    public LocalTime getHoraDesejada() {
        return horaDesejada;
    }

    /**
     * Define a hora desejada pelo cliente.
     * @param horaDesejada A nova hora desejada.
     */
    public void setHoraDesejada(LocalTime horaDesejada) {
        this.horaDesejada = horaDesejada;
    }

    /**
     * Retorna o carimbo de data/hora de entrada na fila.
     * @return O {@link LocalDateTime} da entrada.
     */
    public LocalDateTime getDataEntradafila() {
        return dataEntradafila;
    }

    /**
     * Define o carimbo de data/hora de entrada na fila.
     * @param dataEntradafila O novo carimbo de data/hora.
     */
    public void setDataEntradafila(LocalDateTime dataEntradafila) {
        this.dataEntradafila = dataEntradafila;
    }
}