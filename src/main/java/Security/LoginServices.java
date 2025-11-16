package Security;

import java.util.List;

/**
 * Classe de serviço (Service Layer) para as regras de negócio de Login.
 * Valida os dados antes de interagir com o LoginRepository.
 */
public class LoginServices {

    private LoginRepository loginRepository;

    /**
     * Construtor que injeta a dependência do repositório.
     * @param loginRepository O repositório de dados de login.
     */
    public LoginServices(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    /**
     * Tenta autenticar um usuário.
     *
     * @param loginDigitado O login que o usuario vai digitar.
     * @param senhaDigitada A senha que o usuario vai digitar (texto simples).
     * @return O {@link LoginModel} se a autenticação for bem-sucedida.
     * @throws Exception Se o login/senha forem nulos ou se a
     * autenticação falhar.
     */
    public LoginModel autenticarLogin(String loginDigitado, String senhaDigitada) throws Exception {
        if(loginDigitado == null || loginDigitado.trim().isEmpty() ||
                senhaDigitada == null || senhaDigitada.trim().isEmpty()){
            throw new Exception("O login e a senha não podem ser nulos ou vazios.");
        }


        List<LoginModel> historico = loginRepository.buscarPelosLogins();

        for(LoginModel login : historico){
            if(login.getUsuario().equals(loginDigitado)) {
                if (login.getSenhahash().equals(senhaDigitada)) {
                    System.out.println("Autenticação concluída. Bem-vindo, " + login.getUsuario() + "!");
                    return login;
                } else {
                    throw new Exception("Senha incorreta.");
                }
            }
        }
        throw new Exception("Usuário '" + loginDigitado + "' não encontrado.");
    }

    /**
     * Cadastra um novo login no sistema.
     *
     * @param usuario O nome de usuário desejado.
     * @param senha A senha desejada (texto simples).
     * @param cpf O CPF do usuário (deve ter 11 dígitos numéricos).
     * @return O {@link LoginModel} recém-criado.
     * @throws Exception Se os campos forem inválidos ou se o usuário
     * ou CPF já existirem.
     */
    public LoginModel cadastrarLogin(String usuario, String senha, String cpf) throws Exception {
        if(usuario == null || usuario.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()){
            throw new Exception("Usuário e senha são obrigatórios.");
        }

        if(cpf == null || cpf.replaceAll("[^0-9]", "").length() != 11){
            throw new Exception("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        List<LoginModel> historico = loginRepository.buscarPelosLogins();
        for(LoginModel login : historico) {
            if(login.getUsuario().equalsIgnoreCase(usuario)) {
                throw new Exception("Este nome de usuário já está em uso.");
            }
            if(login.getCpf().equals(cpfLimpo)) {
                throw new Exception("Este CPF já está cadastrado.");
            }
        }

        LoginModel novoLogin = new LoginModel();
        novoLogin.setUsuario(usuario);
        novoLogin.setSenhahash(senha);
        novoLogin.setCpf(cpfLimpo);

        loginRepository.registrarLogin(novoLogin);

        System.out.println("Cadastro realizado com sucesso!");
        return novoLogin;
    }
}