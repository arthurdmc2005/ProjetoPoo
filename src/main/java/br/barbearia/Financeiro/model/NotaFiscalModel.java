package br.barbearia.Financeiro.model;

import java.time.LocalDate;

/**
 * Representa uma Nota Fiscal (Recibo) gerada após uma Ordem de Serviço.
 * Este é um POJO simples para armazenar os dados da nota.
 */
public class NotaFiscalModel {

    private int id;
    private int ordemDeServicoId; // ID da OS que gerou esta nota
    private LocalDate dataEmissao;
    private double valorTotal;
    private String nomeCliente;
    private String cpfCliente;

    /**
     * Construtor padrão (vazio) para desserialização JSON (Jackson).
     */
    public NotaFiscalModel() {
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdemDeServicoId() {
        return ordemDeServicoId;
    }

    public void setOrdemDeServicoId(int ordemDeServicoId) {
        this.ordemDeServicoId = ordemDeServicoId;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    @Override
    public String toString() {
        return "NotaFiscalModel{" +
                "id=" + id +
                ", ordemDeServicoId=" + ordemDeServicoId +
                ", dataEmissao=" + dataEmissao +
                ", valorTotal=" + valorTotal +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", cpfCliente='" + cpfCliente + '\'' +
                '}';
    }
}