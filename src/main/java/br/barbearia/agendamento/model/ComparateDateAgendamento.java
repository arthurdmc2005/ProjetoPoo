package br.barbearia.agendamento.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Implementação de {@link Comparator} para a classe {@link Agendamento}.
 * <p>
 * Esta classe compara dois agendamentos com base na proximidade de suas datas
 * em relação à data atual (hoje). Agendamentos com datas mais próximas de hoje
 * (seja no passado ou no futuro) são classificados como "menores" e vêm primeiro
 * na ordenação.
 * </p>
 * <p>
 * A lógica calcula a diferença absoluta em dias entre "hoje" e a data de cada
 * agendamento e compara essas diferenças.
 * </p>
 *
 * @see Agendamento
 * @see java.util.Comparator
 * @see java.time.temporal.ChronoUnit
 */
public class ComparateDateAgendamento implements Comparator<Agendamento> {

    /**
     * Compara dois objetos {@link Agendamento} pela proximidade de suas datas
     * com a data atual.
     *
     * @param agendamento1 o primeiro {@link Agendamento} a ser comparado.
     * @param agendamento2 o segundo {@link Agendamento} a ser comparado.
     * @return -1, 0 ou 1 se o {@code agendamento1} é mais próximo,
     * igual em proximidade ou mais distante de "hoje" do que o
     * {@code agendamento2}, respectivamente.
     * Trata datas nulas, considerando-as "maiores" (mais distantes)
     * do que datas não nulas.
     */
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


        long diasParaData1 = ChronoUnit.DAYS.between(hoje, data1);
        long diasParaData2 = ChronoUnit.DAYS.between(hoje, data2);


        long distancia1 = Math.abs(diasParaData1);
        long distancia2 = Math.abs(diasParaData2);

        return Long.compare(distancia1, distancia2);
    }
}