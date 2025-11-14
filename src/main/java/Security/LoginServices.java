package Security;

import java.util.List;

public class LoginServices {


    private LoginModel loginModel;
    private LoginRepository loginRepository;

    /**
     * Chamando a minha ferramenta para que se consiga trabalhar com o repositorio;
     * @param loginRepository
     */
    public LoginServices(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    /**
     * Historico armazena a lista de todos os logins que estão cadastrados;
     */
    public List<LoginModel> historico = loginRepository.buscarPelosLogins();

    /**
     * Função que realiza a autenticação do Login verificando senha e login;
     * @param loginDigitado O login que o usuario vai digitar;
     * @param senhaDigitada A senha que o usuario vai digitar;
     * @return Retorna o login autenticado;
     * @throws Exception Retorna um tratamento de exceção;
     */
    public LoginModel autenticarLogin(String loginDigitado, String senhaDigitada)throws Exception{


        if(loginDigitado==null || senhaDigitada==null){
            throw new Exception("O login/senha digitada não pode ser nulo");
        }
       for(LoginModel login : historico){
           if(login.getUsuario().equals(loginDigitado) && login.getSenhahash().equals(senhaDigitada)){
               System.out.printf("Autenticação concluida");
               return login;
           }
       }
       throw new Exception("Login ou senha incorretos");


    }
}
