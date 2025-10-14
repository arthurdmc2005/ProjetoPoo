package br.barbearia.service;

/**
 * Classe responsável por representrar os dados de um usuário.
 */
public class AutenticacaoService {


    private String username;
    private String id;
    private String role;
    private String hashedPassoword;

    /**
     * Construtor passando todos os parametros da classe AutenticacaoService;
     * @param username Armazena o username do usuário;
     * @param id Armazena o ID especifico daquele usuário;
     * @param role Armazena a ''role'' ou o cargo daquela usuário;
     * @param hashedPassoword Armazens uma hashpassoword única para cara usuário, para questão de segurança;
     */
    public AutenticacaoService(String username, String id, String role, String hashedPassoword){
        this.username = username;
        this.id = id;
        this.role = role;
        this.hashedPassoword = hashedPassoword;
    }
    public AutenticacaoService(){
    }

    public String getHashedPassoword() {
        return hashedPassoword;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
