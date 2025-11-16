package br.barbearia.agendamento.model;

import br.barbearia.agendamento.repository.FilaEsperaRepository;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Implementação de {@link Comparator} para a classe {@link FilaEspera}.
 * <p>
 * Esta classe compara dois objetos na fila de espera com base no seu
 * {@code dataEntradafila} (o carimbo de data/hora de entrada na fila).
 * </p>
 * <p>
 * O resultado é uma ordenação cronológica (FIFO - First-In, First-Out),
 * onde o cliente que entrou primeiro na fila (com o {@link LocalDateTime}
 * mais antigo) é classificado como "menor" e vem primeiro na ordenação.
 * </p>
 *
 * @see FilaEspera
 * @see java.util.Comparator
 */
public class ComparateFilaDeEspera implements Comparator<FilaEspera> {

    /**
     * Compara dois objetos {@link FilaEspera} pelo seu
     * {@code dataEntradafila} (carimbo de data/hora de entrada).
     * <p>
     * Datas de entrada nulas são consideradas "maiores" (mais recentes) e são
     * movidas para o final da lista.
     * </p>
     *
     * @param espera1 o primeiro objeto {@link FilaEspera} a ser comparado.
     * @param espera2 o segundo objeto {@link FilaEspera} a ser comparado.
     * @return -1, 0, ou 1 se {@code espera1} for cronologicamente anterior,
     * igual, ou posterior a {@code espera2}.
     */
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