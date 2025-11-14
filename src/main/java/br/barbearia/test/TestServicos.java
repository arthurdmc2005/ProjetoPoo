package br.barbearia.test;

import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.ServicesRoles;

import java.security.Provider;

public class TestServicos {


    public static void main(String[] args) {

        ServicosRepository servicosRepository = new ServicosRepository("BarbeariaComMaven/Servicos.JSON");
        ServicesRoles servicesRoles = new ServicesRoles(servicosRepository);


        System.out.println("Testando busca por serviço");

        try{

        } catch (Exception e) {
            System.out.printf("deu erro burro");
            throw new RuntimeException(e);
        }

        }
    }

