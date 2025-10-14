package br.barbearia.model;

import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * Classe que armazena as despesas com sua descrição, valor e data.
 */
public class Despesas {
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    /**
     *
     * @param descricao descreve a despesa no geral.
     * @param valor armazena o valor da despesa.
     * @param data armaze a data em que a despesa foi registrada.
     */
    public Despesas(String descricao, BigDecimal valor, LocalDate data){
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
    public Despesas(){
    }

    public String getDescricao(){
        return descricao;
    }
    public BigDecimal getValor(){
        return valor;
    }
    public LocalDate getData(){
        return data;
    }
   public void setDescricao(){
   }
   public void setValor(){
   }
   public void setData(){
   }




}
