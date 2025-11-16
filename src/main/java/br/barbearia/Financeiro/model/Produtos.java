package br.barbearia.Financeiro.model;

/**
 * Representa um Produto no estoque da barbearia.
 * <p>
 * Esta classe (POJO) armazena os dados de um item de estoque,
 * como seu nome, quantidade, fornecedor, ID e valor de venda.
 * </p>
 *
 * @see br.barbearia.Financeiro.repository.ProdutosRepository
 * @see br.barbearia.Financeiro.service.ProdutosService
 */
public class Produtos {

    /** O nome de exibição do produto (ex: "Minoxidil"). */
    private String nomeProduto;

    /** A quantidade atual deste produto em estoque. */
    private double quantidade;

    /** O nome do fornecedor do produto. */
    private String fornecedor;

    /** O identificador único do produto no sistema. */
    private int id;

    /** O preço de venda unitário do produto. */
    private double valor;


    /**
     * Construtor para criar um produto sem um valor de venda.
     *
     * @param nomeProduto O nome do produto.
     * @param quantidade A quantidade em estoque.
     * @param fornecedor O fornecedor.
     * @param id O ID do produto.
     */
    public Produtos(String nomeProduto, double quantidade, String fornecedor, int id) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.id = id;
    }

    /**
     * Construtor completo para criar um produto, incluindo seu valor de venda.
     *
     * @param nomeProduto O nome do produto.
     * @param quantidade A quantidade em estoque.
     * @param fornecedor O fornecedor.
     * @param id O ID do produto.
     * @param valor O preço de venda do produto.
     */
    public Produtos(String nomeProduto, double quantidade, String fornecedor, int id, double valor){
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.id = id;
        this.valor = valor;
    }


    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public Produtos(){

    }

    /**
     * Retorna uma representação textual do produto, incluindo todos os seus campos.
     *
     * @return Uma String formatada com os dados do produto.
     */
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

    /**
     * Retorna o nome do fornecedor.
     * @return O nome do fornecedor.
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * Define o nome do fornecedor.
     * @param fornecedor O novo nome do fornecedor.
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * Retorna o ID do produto.
     * @return O ID do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     * @param id O novo ID do produto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o valor (preço de venda) do produto.
     * @return O preço de venda.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor (preço de venda) do produto.
     * @param valor O novo preço de venda.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna a quantidade em estoque do produto.
     * @return A quantidade em estoque.
     */
    public double getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade em estoque do produto.
     * @param quantidade A nova quantidade em estoque.
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o nome do produto.
     * @return O nome do produto.
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Define o nome do produto.
     * @param nomeProduto O novo nome do produto.
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}