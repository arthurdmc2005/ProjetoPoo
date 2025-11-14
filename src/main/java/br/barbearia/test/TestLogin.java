package br.barbearia.test;

import Security.LoginRepository;
import Security.LoginServices;

public class TestLogin {

    public static void main(String[] args) {
        LoginRepository loginRepository = new LoginRepository("BarbeariaComMaven/Login.JSON");
        LoginServices loginServices = new LoginServices(loginRepository);


    }


}
