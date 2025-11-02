package br.barbearia.agendamento.model;

import java.time.LocalDate;

public class Agendamento {

    private LocalDate data;
    private String tipoDeServico;
    private double valor;
    private String autorDoServico;
    private int id;





    private int clienteId;

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

    public Agendamento(LocalDate data, String tipoDeServico, double valor, String autorDoServico) {
        this.data = data;
        this.tipoDeServico = tipoDeServico;
        this.valor = valor;
        this.autorDoServico = autorDoServico;
    }

    public Agendamento(String tipoDeServico, double valor, String autorDoServico){
        this.tipoDeServico = tipoDeServico;
        this.valor = valor;
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

    @Override
    public String toString() {
        return "Agendamento{" +
                "data=" + data +
                ", tipoDeServico='" + tipoDeServico + '\'' +
                ", valor=" + valor +
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getAutorDoServico() {
        return autorDoServico;
    }

    public void setAutorDoServico(String autorDoServico) {
        this.autorDoServico = autorDoServico;
    }
}
