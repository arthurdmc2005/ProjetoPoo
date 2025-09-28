package br.barbearia.usuarios;

public class Cliente extends Pessoa {
    private String cartao;
    private String status;

    // Construtor: inicia o status sempre como "Pendente"
    public Cliente(String nome, String cpf,String telefone, String endereco, String cartao) {
        super(nome, cpf, telefone, endereco);
        this.cartao = cartao;
        this.status = "Pendente";
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

