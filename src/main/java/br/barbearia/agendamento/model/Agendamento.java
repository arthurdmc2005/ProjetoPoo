package br.barbearia.agendamento.model;

import br.barbearia.agendamento.Memento.AgendamentoMemento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private LocalDate data;
    private int id;
    private String status;
    private LocalTime hora;
    private double valorCobrado;
    private int estacaoNumero;


    private int clienteId;

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

    //métodos do Originator ( Agendamento )
    public AgendamentoMemento salvarEstado(){
        return new AgendamentoMemento(this.status,this.estacaoNumero);
    }

    public void restaurarEstado(AgendamentoMemento memento){
        this.status = memento.getStatus();
        this.estacaoNumero = memento.getEstacaoNumero();
    }

    public LocalTime getHora() {
        return hora;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    private int servicoId;

    private int funcionarioId;

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

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getData() {
        return data;
    }

    public Agendamento(LocalDate data, String status) {
        this.data = data;
        this.status = status;
    }

    public Agendamento() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getEstacaoNumero() {
        return estacaoNumero;
    }

    public void setEstacaoNumero(int estacaoNumero) {
        this.estacaoNumero = estacaoNumero;
    }
}