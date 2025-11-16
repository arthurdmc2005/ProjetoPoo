package br.barbearia.test;

import br.barbearia.OrdensDeServiço.OrdensDeServicoRepository;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRoles;

import java.time.LocalDate;

public class TestOrdensDeServico {



    public static void main(String[] args) {

        OrdensDeServicoRepository ordensDeServicoRepository = new OrdensDeServicoRepository("BarbeariaComMaven/OrdensDeServico.JSON");
        OrdensDeServicoRoles ordensDeServicoRoles = new OrdensDeServicoRoles(ordensDeServicoRepository);
        try {
            int total = ordensDeServicoRoles.contadorDeOrdensDeServico();
            System.out.println("Quantidade de instâncias criadas do tipo Ordens de Serviços: " + total);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

