package br.barbearia.agendamento.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class ComparateDateAgendamento implements Comparator<Agendamento> {

    @Override
    public int compare(Agendamento agendamento1, Agendamento agendamento2){
        LocalDate hoje = LocalDate.now();

        LocalDate data1 = agendamento1.getData();
        LocalDate data2 = agendamento2.getData();

        if(data1 == null && data2 == null){
            return 0;
        }
        if(data1 == null){
            return 1;
        }
        if(data2 == null){
            return -1;
        }
            //ChronoUnit é uma calculadara que eu usei para ver quantos dias existem de diferença entre o dia de hoje e a data do agendamento
        long diasParaData1 = ChronoUnit.DAYS.between(hoje,data1);
        long diasParaData2 = ChronoUnit.DAYS.between(hoje,data2);

        long distancia1 = Math.abs(diasParaData1);
        long distancia2 = Math.abs(diasParaData2);

        return Long.compare(distancia1, distancia2);

    }


}
