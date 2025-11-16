package br.barbearia.agendamento.service;

import br.barbearia.agendamento.model.FilaEspera;
import br.barbearia.agendamento.repository.FilaEsperaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Classe de serviço (Service Layer) responsável pela lógica de negócio
 * da Fila de Espera.
 * <p>
 * Esta classe abstrai as operações de manipulação da fila, como adicionar,
 * remover e chamar o próximo cliente, comunicando-se com o
 * {@link FilaEsperaRepository} para persistir as alterações.
 * </p>
 *
 * @see FilaEspera
 * @see FilaEsperaRepository
 */
public class FilaEsperaService {

    /**
     * O repositório que gerencia a persistência dos dados da fila de espera.
     */
    private FilaEsperaRepository filaEsperaRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência do {@link FilaEsperaRepository}.
     *
     * @param filaEsperaRepository A instância do repositório
     * a ser usada por este serviço.
     */
    public FilaEsperaService(FilaEsperaRepository filaEsperaRepository){
        this.filaEsperaRepository = filaEsperaRepository;
    }

    /**
     * Adiciona um novo cliente (entrada) na fila de espera.
     * <p>
     * Se a entrada for nula, o método retorna sem fazer nada.
     * </p>
     *
     * @param espera O objeto {@link FilaEspera} representando o
     * cliente a ser adicionado.
     */
    public void adicionarCliente(FilaEspera espera){
        if(espera == null){
            return;
        }
        filaEsperaRepository.adicionarNaFila(espera);

    }

    /**
     * Remove um cliente (entrada) da fila de espera com base no seu ID.
     *
     * @param idParaRemover O ID da entrada na fila a ser removida.
     */
    public void removerCliente(int idParaRemover){
        filaEsperaRepository.removerDaFila(idParaRemover);
    }

    /**
     * Busca o próximo cliente prioritário para um horário vago e o
     * remove da fila.
     * <p>
     * Este método consulta o repositório pelo próximo cliente na fila
     * (seguindo a lógica FIFO) para o horário especificado. Se um cliente
     * for encontrado, ele é removido da fila e retornado.
     * </p>
     *
     * @param data A data do horário que vagou.
     * @param hora A hora do horário que vagou.
     * @return O objeto {@link FilaEspera} do cliente que foi chamado e
     * removido da fila, ou {@code null} se ninguém estava
     * esperando por este horário.
     */
    public FilaEspera chamarProximoDaFila(LocalDate data, LocalTime hora){
        FilaEspera proximo = filaEsperaRepository.buscarProximoDaFila(data,hora);
        if(proximo != null){
            this.removerCliente(proximo.getId());
        }
        return proximo;
    }

}