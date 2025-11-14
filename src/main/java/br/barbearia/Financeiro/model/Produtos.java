package br.barbearia.Financeiro.model;

public class Produtos {

    private String nomeProduto;
    private double quantidade;
    private String fornecedor;
    private int id;
    private double valor;



    public Produtos(String nomeProduto, double quantidade, String fornecedor, int id) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.id = id;
    }
    public Produtos(String nomeProduto, double quantidade, String fornecedor, int id, double valor){
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.id = id;
        this.valor = valor;
    }


    public Produtos(){

    }

    @Override
    public String toString() {
        return "Produtos{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", fornecedor='" + fornecedor + '\'' +
                ", id=" + id +
                ", valor=" + valor +
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
