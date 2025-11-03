package br.barbearia.agendamento.model;

public class Servicos {
    private String servico;
    private String descricao;
    private double precoBase;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return precoBase;
    }

    public void setValor(double precoBase) {
        this.precoBase = precoBase;
    }

    public Servicos(String servico, String descricao, double precoBase) {
        this.servico = servico;
        this.descricao = descricao;
        this.precoBase = precoBase;
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
                ", precoBase=" + precoBase +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
