package br.barbearia.Financeiro.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa um registro único de uma venda de produto.
 * <p>
 * Esta classe (POJO) armazena as informações essenciais de uma transação
 * de venda, incluindo o produto, cliente, data, quantidade e valor total.
 * </p>
 *
 * @see br.barbearia.Financeiro.repository.RegistroDeVendasRepository
 * @see br.barbearia.Financeiro.service.RegistroDeVendasServices
 */
public class RegistroDeVendas {

    /** O nome do produto vendido. */
    private String nomeProduto;

    /** A chave estrangeira (ID) do cliente que realizou a compra. */
    private int clienteId;

    /** A data em que a venda (saída do estoque) ocorreu. */
    private LocalDate saida;

    /** A quantidade de itens vendidos nesta transação. */
    private double quantidadeVendida;

    /** O valor total da transação (preço * quantidade). */
    private double valor;

    /**
     * Construtor para um registro de venda.
     * <p>
     * Nota: Este construtor não inicializa o campo {@code quantidadeVendida}.
     * Ele deve ser definido separadamente usando
     * {@link #setQuantidadeVendida(double)}.
     * </p>
     *
     * @param nomeProduto O nome do produto vendido.
     * @param clienteId O ID do cliente.
     * @param saida A data da venda.
     * @param valor O valor total da venda.
     */
    public RegistroDeVendas(String nomeProduto, int clienteId, LocalDate saida, double valor) {
        this.nomeProduto = nomeProduto;
        this.clienteId = clienteId;
        this.saida = saida;
        this.valor = valor;
    }

    /**
     * Retorna a quantidade de itens vendidos.
     * @return A quantidade vendida.
     */
    public double getQuantidadeVendida() {
        return quantidadeVendida;
    }

    /**
     * Define a quantidade de itens vendidos.
     * @param quantidadeVendida A nova quantidade.
     */
    public void setQuantidadeVendida(double quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public RegistroDeVendas(){

    }

    /**
     * Retorna o valor total desta transação.
     * @return O valor total.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor total desta transação.
     * @param valor O novo valor total.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna o nome do produto vendido.
     * @return O nome do produto.
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Define o nome do produto vendido.
     * @param nomeProduto O novo nome do produto.
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Retorna o ID do cliente associado à venda.
     * @return O ID do cliente.
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * Define o ID do cliente associado à venda.
     * @param clienteId O novo ID do cliente.
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Retorna a data em que a venda ocorreu.
     * @return A {@link LocalDate} da venda.
     */
    public LocalDate getSaida() {
        return saida;
    }

    /**
     * Define a data em que a venda ocorreu.
     * @param saida A nova {@link LocalDate} da venda.
     */
    public void setSaida(LocalDate saida) {
        this.saida = saida;
    }

    /**
     * Retorna uma representação textual completa do registro de venda.
     *
     * @return Uma String formatada com todos os dados do registro.
     */
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