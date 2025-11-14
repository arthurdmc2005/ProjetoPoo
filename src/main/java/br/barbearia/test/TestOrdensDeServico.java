package br.barbearia.test;

import br.barbearia.OrdensDeServiço.OrdensDeServicoRepository;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRoles;

public class TestOrdensDeServico {



    public static void main(String[] args) {

        OrdensDeServicoRepository ordensDeServicoRepository = new OrdensDeServicoRepository("BarbeariaComMaven/OrdensDeServico.JSON");
        OrdensDeServicoRoles ordensDeServicoRoles = new OrdensDeServicoRoles(ordensDeServicoRepository);
        try {
            ordensDeServicoRoles.registrarOrdensDeServico("Barba e Corte", "01973214612", "Serviço realizado com sucesso foi top", 70,1);
            ordensDeServicoRepository.salvarNoJson();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

