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

    /**
     * Retorna o nome de usuário.
     * @return O nome de usuário para login.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define o nome de usuário.
     * @param usuario O novo nome de usuário.
     */
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

    /**
     * Define o ID único deste registro de login.
     * @param id O novo ID.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Retorna o ID único deste registro de login.
     * @return O ID do registro.
     */
    public int getId(){
        return id;
    }

    /**
     * Retorna o CPF associado a este login.
     * @return O CPF do usuário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF associado a este login.
     * @param cpf O novo CPF do usuário.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna a senha (hash) associada a este login.
     * @return A senha (hash).
     */
    public String getSenhahash() {
        return senhahash;
    }

    /**
     * Define a senha (hash) para este login.
     * @param senhahash A nova senha (hash).
     */
    public void setSenhahash(String senhahash) {
        this.senhahash = senhahash;
    }

    /**
     * Retorna uma representação textual do objeto LoginModel.
     * <p>
     * Importante: A senha (senhahash) é omitida da saída
     * por razões de segurança, exibindo "[PROTEGIDO]".
     * </p>
     * @return Uma String formatada com os dados do login (sem senha).
     */
    @Override
    public String toString() {
        return "LoginModel{" +
                "usuario='" + usuario + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senhahash='" + "[PROTEGIDO]" + '\'' + // Não exibir a senha no log
                '}';
    }
}