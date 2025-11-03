package br.barbearia.agendamento.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private LocalDate data;
    private String tipoDeServico;
    private String autorDoServico;
    private int id;
    private String status;
    private LocalTime hora;
    private double valorCobrado;





    private int clienteId;

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

    public Agendamento(LocalDate data, String tipoDeServico, String autorDoServico,String status) {
        this.data = data;
        this.tipoDeServico = tipoDeServico;
        this.autorDoServico = autorDoServico;
        this.status = status;
    }

    public Agendamento(String tipoDeServico, String autorDoServico){
        this.tipoDeServico = tipoDeServico;
        this.autorDoServico = autorDoServico;

    }
    public Agendamento(){

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

    @Override
    public String toString() {
        return "Agendamento{" +
                "data=" + data +
                ", tipoDeServico='" + tipoDeServico + '\'' +
                ", autorDoServico='" + autorDoServico + '\'' +
                ", id=" + id;

    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTipoDeServico() {
        return tipoDeServico;
    }

    public void setTipoDeServico(String tipoDeServico) {
        this.tipoDeServico = tipoDeServico;
    }

    public String getAutorDoServico() {
        return autorDoServico;
    }

    public void setAutorDoServico(String autorDoServico) {
        this.autorDoServico = autorDoServico;
    }
}
