package br.barbearia.model;

public class Cliente extends Pessoa {
    private String cartao;
    private String status;

    // Construtor: inicia o status sempre como "Pendente"
    public Cliente(String nome, String cpf, String telefone, String cartao) {
        super(nome, cpf, telefone);
        this.cartao = cartao;
        this.status = "Pendente";
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Getters
    public String getCartao() {
        return cartao;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

