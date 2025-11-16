package br.barbearia.Financeiro.model;

import java.time.LocalDate;

/**
 * Representa uma Nota Fiscal (Recibo) gerada após uma Ordem de Serviço.
 * Este é um POJO simples para armazenar os dados da nota.
 */
public class NotaFiscalModel {

    /** O identificador único da nota fiscal. */
    private int id;

    /** O ID da Ordem de Serviço que originou esta nota. */
    private int ordemDeServicoId;

    /** A data em que a nota fiscal foi emitida. */
    private LocalDate dataEmissao;

    /** O valor financeiro total da transação. */
    private double valorTotal;

    /** O nome do cliente associado à nota. */
    private String nomeCliente;

    /** O CPF do cliente associado à nota. */
    private String cpfCliente;

    /**
     * Construtor padrão (vazio) para desserialização JSON (Jackson).
     */
    public NotaFiscalModel() {
    }

    // --- Getters e Setters ---

    /**
     * Retorna o ID único da nota fiscal.
     * @return O ID da nota.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID único da nota fiscal.
     * @param id O novo ID da nota.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o ID da Ordem de Serviço vinculada a esta nota.
     * @return O ID da Ordem de Serviço.
     */
    public int getOrdemDeServicoId() {
        return ordemDeServicoId;
    }

    /**
     * Define o ID da Ordem de Serviço vinculada a esta nota.
     * @param ordemDeServicoId O ID da Ordem de Serviço.
     */
    public void setOrdemDeServicoId(int ordemDeServicoId) {
        this.ordemDeServicoId = ordemDeServicoId;
    }

    /**
     * Retorna a data em que a nota fiscal foi emitida.
     * @return A data de emissão.
     */
    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    /**
     * Define a data em que a nota fiscal foi emitida.
     * @param dataEmissao A nova data de emissão.
     */
    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * Retorna o valor total cobrado na nota fiscal.
     * @return O valor total.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total cobrado na nota fiscal.
     * @param valorTotal O novo valor total.
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Retorna o nome do cliente para quem a nota foi emitida.
     * @return O nome do cliente.
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Define o nome do cliente para quem a nota foi emitida.
     * @param nomeCliente O novo nome do cliente.
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * Retorna o CPF do cliente para quem a nota foi emitida.
     * @return O CPF do cliente.
     */
    public String getCpfCliente() {
        return cpfCliente;
    }

    /**
     * Define o CPF do cliente para quem a nota foi emitida.
     * @param cpfCliente O novo CPF do cliente.
     */
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    /**
     * Retorna uma representação textual completa do objeto NotaFiscalModel.
     * @return Uma String formatada com todos os dados da nota.
     */
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