package br.barbearia.Loja.repository;

import java.math.BigDecimal;

public class ProdutosRepository {
    private String produto;
    private String descricao;
    private BigDecimal valor;


    public String getProduto(){
        return produto;
    }
    public String getDescricao(){
        return descricao;
    }
    public BigDecimal getValor(){
        return valor;
    }
    public void setDescricao(){
    }
    public void setValor(){
    }
    public void setProduto(){

    }
}

