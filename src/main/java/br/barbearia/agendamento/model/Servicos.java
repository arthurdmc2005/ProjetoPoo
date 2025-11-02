package br.barbearia.agendamento.model;

public class Servicos {
    private String servico;
    private String descricao;
    private double valor;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Servicos(String servico, String descricao, double valor) {
        this.servico = servico;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servicos(){

    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "Servicos{" +
                "servico='" + servico + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
