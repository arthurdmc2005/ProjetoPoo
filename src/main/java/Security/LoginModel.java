package Security;

/**
 * Modelo (POJO) que representa os dados de um Login.
 * Armazena o nome de usuário, CPF e a senha (hash).
 */
public class LoginModel {
    /**
     * O nome de usuário para login.
     */
    private String usuario;
    /**
     * O CPF associado ao login (pode ser usado para recuperação).
     */
    private String cpf;
    /**
     * A senha salva (neste caso, texto simples, mas nomeada como hash).
     */
    private String senhahash;
    /**
     * O ID único do registro de login.
     */
    private int id;

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON pelo Jackson.
     */
    public LoginModel() {
    }

    // --- Getters e Setters ---

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Construtor completo do modelo de login.
     *
     * @param usuario O nome de usuário.
     * @param cpf O CPF do usuário.
     * @param senhahash A senha.
     * @param id O ID do registro.
     */
    public LoginModel(String usuario, String cpf, String senhahash, int id) {
        this.usuario = usuario;
        this.cpf = cpf;
        this.senhahash = senhahash;
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenhahash() {
        return senhahash;
    }

    public void setSenhahash(String senhahash) {
        this.senhahash = senhahash;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "usuario='" + usuario + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senhahash='" + "[PROTEGIDO]" + '\'' + // Não exibir a senha no log
                '}';
    }
}