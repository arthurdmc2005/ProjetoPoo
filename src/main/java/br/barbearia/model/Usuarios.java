package br.barbearia.model;

/**
 * Representa um usuário genérico no sistema.
 * <p>
 * Esta classe (POJO) armazena dados de identificação (nome, cpf, telefone),
 * dados de autenticação (login, senhaHash) e o tipo de usuário
 * (ex: "Cliente", "Funcionario", "Gerente") para autorização.
 * </p>
 *
 * @see br.barbearia.repository.UsuarioRepository
 * @see br.barbearia.service.UsuarioServices
 */
public class Usuarios {

    /** O nome completo do usuário. */
    private String nome;

    /** O identificador único do usuário no banco de dados/JSON. */
    private int id;

    /** O tipo/role do usuário (ex: "Cliente", "Funcionario"). */
    private String tipoDeUsuario;

    /** O nome de usuário para login. */
    private String login;

    /** A senha criptografada (hash) do usuário. */
    private String senhaHash;

    /** O CPF do usuário. */
    private String cpf;

    /** O número de telefone do usuário. */
    private String telefone;

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public Usuarios(){

    }

    /**
     * Retorna o tipo (role) do usuário.
     * @return O tipo de usuário (ex: "Cliente").
     */
    public String getTipo() {
        return tipoDeUsuario;
    }

    /**
     * Retorna o ID do usuário.
     * @return O ID do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do usuário.
     * @param id O novo ID do usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o login do usuário.
     * @return O login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retorna o nome do usuário.
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a senha criptografada (hash) do usuário.
     * @return A senha (hash).
     */
    public String getSenhaHash() {
        return senhaHash;
    }

    /**
     * Define o nome do usuário.
     * @param nome O novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o tipo (role) do usuário.
     * @param cargo O novo tipo de usuário (ex: "Funcionario").
     */
    public void setTipo(String cargo) {
        this.tipoDeUsuario = cargo;
    }

    /**
     * Retorna o telefone do usuário.
     * @return O telefone.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do usuário.
     * @param telefone O novo telefone.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Define o login do usuário.
     * @param login O novo login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Define a senha criptografada (hash) do usuário.
     * @param senhaHash A nova senha (hash).
     */
    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    /**
     * Retorna o CPF do usuário.
     * @return O CPF.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do usuário.
     * @param cpf O novo CPF.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna uma representação textual completa do objeto Usuário.
     *
     * @return Uma String formatada com todos os campos da classe.
     */
    @Override
    public String toString() {
        return "Usuarios{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", tipoDeUsuario='" + tipoDeUsuario + '\'' +
                ", login='" + login + '\'' +
                ", senhaHash='" + senhaHash + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    /**
     * Construtor parcial para criar um usuário com vários campos.
     * <p>
     * (Nota: Conforme a implementação, apenas nome, tipoDeUsuario,
     * cpf e telefone são atribuídos).
     * </p>
     *
     * @param nome O nome do usuário.
     * @param login O login do usuário.
     * @param senhaHash A senha criptografada.
     * @param tipoDeUsuario O tipo do usuário (ex: "Cliente").
     * @param cpf O CPF do usuário.
     * @param telefone O telefone do usuário.
     */
    public Usuarios(String nome, String login, String senhaHash, String tipoDeUsuario, String cpf,String telefone){
        this.nome = nome;
        this.tipoDeUsuario = tipoDeUsuario;
        this.cpf = cpf;
        this.telefone = telefone;


    }

    /**
     * Construtor parcial focado em dados de autenticação e autorização.
     *
     * @param id O ID do usuário.
     * @param tipoDeUsuario O tipo do usuário.
     * @param login O login do usuário.
     * @param senhaHash A senha criptografada.
     */
    public Usuarios(int id, String tipoDeUsuario, String login, String senhaHash) {
        this.id = id;
        this.tipoDeUsuario = tipoDeUsuario;
        this.login = login;
        this.senhaHash = senhaHash;
    }

    /**
     * Construtor parcial focado em dados de autenticação.
     *
     * @param login O login do usuário.
     * @param senhaHash A senha criptografada.
     */
    public Usuarios(String login, String senhaHash){

    }

    /**
     * Construtor parcial focado em dados pessoais básicos.
     *
     * @param nome O nome do usuário.
     * @param telefone O telefone do usuário.
     * @param cpf O CPF do usuário.
     */
    public Usuarios(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}