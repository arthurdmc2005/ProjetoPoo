package br.barbearia.Loja.model;

public class ProdutoMemento {
    private final String nomeProduto;
    private final double quantidade;
    private final String fornecedor;

    public ProdutoMemento(String nomeProduto, double quantidade, String fornecedor) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }
}

