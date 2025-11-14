package Security;

import java.util.List;

public class LoginServices {

    private LoginModel loginModel;
    private LoginRepository loginRepository;

    public LoginServices(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public List<LoginModel> historico = loginRepository.buscarPelosLogins();

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
