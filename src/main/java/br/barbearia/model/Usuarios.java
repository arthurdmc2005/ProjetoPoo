package br.barbearia.model;

public class Usuarios {

    private String nome;
    private int id;
    private String tipo;
    private String login;
    private String senhaHash;
    private String cpf;
    private String telefone;

    public Usuarios(){

    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String cargo) {
        this.tipo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", tipo='" + tipo + '\'' +
                ", login='" + login + '\'' +
                ", senhaHash='" + senhaHash + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public Usuarios(String nome, String login, String senhaHash, String tipo, String cpf,String telefone){
        this.nome = nome;
        this.login = login;
        this.senhaHash = senhaHash;
        this.tipo = tipo;
        this.cpf = cpf;
        this.telefone = telefone;


    }

    public Usuarios(int id, String tipo, String login, String senhaHash) {
        this.id = id;
        this.tipo = tipo;
        this.login = login;
        this.senhaHash = senhaHash;
    }

    public Usuarios(String login, String senhaHash){

    }

    public Usuarios(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}
