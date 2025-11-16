package br.barbearia.agendamento.model;

import br.barbearia.agendamento.Memento.AgendamentoMemento;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa a entidade Agendamento no sistema.
 * Esta classe é um POJO (Plain Old Java Object) que armazena os dados de um
 * agendamento de serviço.
 * <p>
 * Atua como o 'Originator' (Originador) no padrão de projeto Memento,
 * permitindo que seu estado (status e estação) seja salvo e restaurado.
 *
 * @see AgendamentoMemento
 * @see br.barbearia.agendamento.Memento.AgendamentoCaraTaker
 */
public class Agendamento {

    /** A data do agendamento. */
    private LocalDate data;

    /** O identificador único do agendamento. */
    private int id;

    /** O status atual do agendamento (ex: "AGENDADO", "FINALIZADO"). */
    private String status;

    /** A hora de início do agendamento. */
    private LocalTime hora;

    /** O valor final cobrado pelo serviço no agendamento. */
    private double valorCobrado;

    /** O número da estação de atendimento designada para o agendamento. */
    private int estacaoNumero;

    /** A chave estrangeira (ID) do cliente associado a este agendamento. */
    private int clienteId;

    /** A chave estrangeira (ID) do serviço principal deste agendamento. */
    private int servicoId;

    /** A chave estrangeira (ID) do funcionário que realizará o atendimento. */
    private int funcionarioId;

    /**
     * Construtor completo para criar uma instância de Agendamento com todos os dados.
     *
     * @param data           A data do agendamento.
     * @param id             O ID do agendamento.
     * @param status         O status inicial (ex: "AGENDADO").
     * @param hora           A hora do agendamento.
     * @param valorCobrado   O valor cobrado.
     * @param estacaoNumero  O número da estação de atendimento.
     * @param clienteId      O ID do cliente.
     * @param servicoId      O ID do serviço.
     * @param funcionarioId  O ID do funcionário.
     */
    public Agendamento(LocalDate data, int id, String status, LocalTime hora, double valorCobrado, int estacaoNumero, int clienteId, int servicoId, int funcionarioId) {
        this.data = data;
        this.id = id;
        this.status = status;
        this.hora = hora;
        this.valorCobrado = valorCobrado;
        this.estacaoNumero = estacaoNumero;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.funcionarioId = funcionarioId;
    }

    //--- Métodos do Padrão Memento (Originator) ---

    /**
     * Salva o estado atual (status e estação) do agendamento em um Memento.
     *
     * @return um novo {@link AgendamentoMemento} contendo o estado atual.
     */
    public AgendamentoMemento salvarEstado(){
        return new AgendamentoMemento(this.status,this.estacaoNumero);
    }

    /**
     * Restaura o estado do agendamento a partir de um Memento salvo.
     *
     * @param memento O {@link AgendamentoMemento} contendo o estado para o qual
     * o agendamento deve ser revertido.
     */
    public void restaurarEstado(AgendamentoMemento memento){
        this.status = memento.getStatus();
        this.estacaoNumero = memento.getEstacaoNumero();
    }

    //--- Getters e Setters ---

    /**
     * Retorna a hora do agendamento.
     * @return A hora do agendamento.
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Retorna o valor cobrado pelo serviço.
     * @return O valor cobrado.
     */
    public double getValorCobrado() {
        return valorCobrado;
    }

    /**
     * Define o valor cobrado pelo serviço.
     * @param valorCobrado O novo valor cobrado.
     */
    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    /**
     * Define a hora do agendamento.
     * @param hora A nova hora do agendamento.
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Gera uma representação textual do agendamento.
     *
     * @return Uma String formatada com os principais dados do agendamento.
     */
    @Override
    public String toString() {
        return "Agendamento{" +
                "data=" + data +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", hora=" + hora +
                ", valorCobrado=" + valorCobrado +
                ", clienteId=" + clienteId +
                ", servicoId=" + servicoId +
                ", funcionarioId=" + funcionarioId +
                '}';
    }

    /**
     * Retorna o ID do serviço associado.
     * @return O ID do serviço.
     */
    public int getServicoId() {
        return servicoId;
    }

    /**
     * Define o ID do serviço associado.
     * @param servicoId O novo ID do serviço.
     */
    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    /**
     * Retorna o ID do funcionário associado.
     * @return O ID do funcionário.
     */
    public int getFuncionarioId() {
        return funcionarioId;
    }

    /**
     * Define o ID do funcionário associado.
     * @param funcionarioId O novo ID do funcionário.
     */
    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    /**
     * Retorna o ID do cliente associado.
     * @return O ID do cliente.
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * Define o ID do cliente associado.
     * @param clienteId O novo ID do cliente.
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Retorna a data do agendamento.
     * @return A data do agendamento.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Construtor parcial para criar um agendamento com data e status.
     *
     * @param data A data do agendamento.
     * @param status O status do agendamento.
     */
    public Agendamento(LocalDate data, String status) {
        this.data = data;
        this.status = status;
    }

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public Agendamento() {

    }

    /**
     * Retorna o ID do agendamento.
     * @return O ID do agendamento.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do agendamento.
     * @param id O novo ID do agendamento.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o status atual do agendamento.
     * @return O status (ex: "AGENDADO").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status do agendamento.
     * @param status O novo status (ex: "FINALIZADO").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Define a data do agendamento.
     * @param data A nova data do agendamento.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Retorna o número da estação de atendimento.
     * @return O número da estação.
     */
    public int getEstacaoNumero() {
        return estacaoNumero;
    }

    /**
     * Define o número da estação de atendimento.
     * @param estacaoNumero O novo número da estação.
     */
    public void setEstacaoNumero(int estacaoNumero) {
        this.estacaoNumero = estacaoNumero;
    }
}