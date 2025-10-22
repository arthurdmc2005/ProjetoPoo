package br.barbearia.service;

public class CadastroService {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public CadastroService(String nome, String cpf, String email, String telefone){

    }
    public CadastroService(){

    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
