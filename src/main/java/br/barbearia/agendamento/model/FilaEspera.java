package br.barbearia.agendamento.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FilaEspera {

    private int id;
    private int clienteId;
    private int servicoId;

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

    public FilaEspera(int id, int clienteId, int servicoId, LocalDate dataDesejada, LocalTime horaDesejada, LocalDateTime dataEntradafila) {
        this.id = id;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataDesejada = dataDesejada;
        this.horaDesejada = horaDesejada;
        this.dataEntradafila = dataEntradafila;
    }

    public FilaEspera(){

    }

    private LocalDate dataDesejada;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataDesejada() {
        return dataDesejada;
    }

    public void setDataDesejada(LocalDate dataDesejada) {
        this.dataDesejada = dataDesejada;
    }

    public LocalTime getHoraDesejada() {
        return horaDesejada;
    }

    public void setHoraDesejada(LocalTime horaDesejada) {
        this.horaDesejada = horaDesejada;
    }

    public LocalDateTime getDataEntradafila() {
        return dataEntradafila;
    }

    public void setDataEntradafila(LocalDateTime dataEntradafila) {
        this.dataEntradafila = dataEntradafila;
    }

    private LocalTime horaDesejada;
    private LocalDateTime dataEntradafila;

}
