package br.barbearia.Financeiro.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroDeVendas {

    private String nomeProduto;
    private int clienteId;
    private LocalDate saida;
    private double quantidadeVendida;
    private double valor;

    public RegistroDeVendas(String nomeProduto, int clienteId, LocalDate saida, double valor) {
        this.nomeProduto = nomeProduto;
        this.clienteId = clienteId;
        this.saida = saida;
        this.valor = valor;
    }

    public double getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(double quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public RegistroDeVendas(){

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getSaida() {
        return saida;
    }

    public void setSaida(LocalDate saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "RegistroDeVendas{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", clienteId=" + clienteId +
                ", saida=" + saida +
                ", quantidadeVendida=" + quantidadeVendida +
                ", valor=" + valor +
                '}';
    }
}
