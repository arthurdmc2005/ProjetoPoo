package br.barbearia.Loja.model;

public class Produtos {

    private String nomeProduto;
    private double quantidade;
    private String fornecedor;
    private int id;



    public Produtos(String nomeProduto, double quantidade, String fornecedor, int id) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.id = id;
    }


    public Produtos(){

    }

    @Override
    public String toString() {
        return "EstoqueModel{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", fornecedor='" + fornecedor + '\'' +
                ", id=" + id +
                '}';
    }
    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
