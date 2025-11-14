package Security;

public class LoginModel {

    private String usuario;
    private String cpf;
    private String senhahash;
    private int id;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

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
                ", senhahash='" + senhahash + '\'' +
                '}';
    }
}
