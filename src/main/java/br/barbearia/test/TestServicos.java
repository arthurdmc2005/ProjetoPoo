package br.barbearia.test;

import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.ServicesRoles;

import java.security.Provider;

public class TestServicos {


    public static void main(String[] args) {

        ServicosRepository servicosRepository = new ServicosRepository("BarbeariaComMaven/Servicos.JSON");
        ServicesRoles servicesRoles = new ServicesRoles(servicosRepository);


        System.out.println("Testando o cadastro de serviços");

        try{
            servicesRoles.salvarNovoServico("Barba preemium",50,"barba muito foda");

        } catch (Exception e) {
            System.out.println("Falha no cadastro de serviço");
            throw new RuntimeException(e);
        }
        }
    }

