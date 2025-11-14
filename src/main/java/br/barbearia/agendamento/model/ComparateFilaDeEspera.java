package br.barbearia.agendamento.model;

import br.barbearia.agendamento.repository.FilaEsperaRepository;

import java.time.LocalDateTime;
import java.util.Comparator;

public class ComparateFilaDeEspera implements Comparator<FilaEspera> {

    @Override
    public int compare(FilaEspera espera1, FilaEspera espera2){

        LocalDateTime dataEntrada1 = espera1.getDataEntradafila();
        LocalDateTime dataEntrada2 = espera2.getDataEntradafila();

        if(dataEntrada1==null && dataEntrada2 == null){
            return 0;
        }
        if(dataEntrada1 == null){
            return 1;
        }
        if(dataEntrada2 == null){
            return -1;
        }
        if(dataEntrada1.isBefore(dataEntrada2)){
            return -1;
        } else if (dataEntrada1.isAfter(dataEntrada2)) {
            return 1;
        }
        else{
            return 0;
        }

    }
}
