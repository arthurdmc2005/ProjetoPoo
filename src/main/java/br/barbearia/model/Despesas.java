package br.barbearia.model;

import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * Classe que armazena as despesas com sua descrição, valor e data.
 * <p>
 * Representa uma única entrada de despesa no sistema financeiro.
 * Este é um POJO (Plain Old Java Object) para manipulação de dados.
 * </p>
 */
public class Despesas {

    /** A descrição textual da despesa (ex: "Aluguel", "Compra de produtos"). */
    private String descricao;

    /** O valor monetário da despesa, usando BigDecimal para alta precisão. */
    private BigDecimal valor;

    /** A data em que a despesa foi registrada ou incorrida. */
    private LocalDate data;

    /**
     * Retorna uma representação textual da despesa.
     *
     * @return Uma String formatada com descrição, valor e data.
     */
    @Override
    public String toString() {
        return "Despesas{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }

    /**
     * Construtor completo para criar uma instância de Despesa.
     *
     * @param descricao descreve a despesa no geral.
     * @param valor armazena o valor da despesa (ex: new BigDecimal("150.75")).
     * @param data armazena a data em que a despesa foi registrada.
     */
    public Despesas(String descricao, BigDecimal valor, LocalDate data){
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public Despesas(){
    }

    /**
     * Retorna a descrição da despesa.
     * @return A descrição textual da despesa.
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * Retorna o valor monetário da despesa.
     * @return O {@link BigDecimal} com o valor da despesa.
     */
    public BigDecimal getValor(){
        return valor;
    }

    /**
     * Retorna a data da despesa.
     * @return A {@link LocalDate} da despesa.
     */
    public LocalDate getData(){
        return data;
    }

    /**
     * Define a descrição da despesa.
     * <p><b>Nota:</b> Esta implementação está vazia e não aceita parâmetros.
     * Para funcionar corretamente, um setter de descrição deveria ter
     * a assinatura {@code public void setDescricao(String descricao)}
     * e o corpo {@code this.descricao = descricao;}.
     * </p>
     * @deprecated Este método está incompleto e não funciona como um setter.
     */
    public void setDescricao(){
    }

    /**
     * Define o valor da despesa.
     * <p><b>Nota:</b> Esta implementação está vazia e não aceita parâmetros.
     * Para funcionar corretamente, um setter de valor deveria ter
     * a assinatura {@code public void setValor(BigDecimal valor)}
     * e o corpo {@code this.valor = valor;}.
     * </p>
     * @deprecated Este método está incompleto e não funciona como um setter.
     */
    public void setValor(){
    }

    /**
     * Define a data da despesa.
     * <p><b>Nota:</b> Esta implementação está vazia e não aceita parâmetros.
     * Para funcionar corretamente, um setter de data deveria ter
     * a assinatura {@code public void setData(LocalDate data)}
     * e o corpo {@code this.data = data;}.
     * </p>
     * @deprecated Este método está incompleto e não funciona como um setter.
     */
    public void setData(){
    }
}