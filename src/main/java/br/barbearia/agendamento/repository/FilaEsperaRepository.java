package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateFilaDeEspera;
import br.barbearia.agendamento.model.FilaEspera;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Repositório para gerenciamento da Fila de Espera por horários.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados da fila de espera,
 * utilizando um {@link GerenciadorJSON} para persistir e carregar
 * a lista de clientes em espera de um arquivo JSON.
 * </p>
 *
 * @see FilaEspera
 * @see br.barbearia.agendamento.service.FilaEsperaService
 * @see GerenciadorJSON
 */
public class FilaEsperaRepository {

    /**
     * O gerenciador genérico para manipulação do arquivo JSON.
     */
    private GerenciadorJSON<FilaEspera> gerenciadorJSON;

    /**
     * A lista de entradas da fila de espera carregada em memória.
     */
    private List<FilaEspera> listaDeEspera;

    /**
     * Construtor do repositório da fila de espera.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de espera existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/FilaEspera.JSON").
     */
    public FilaEsperaRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<FilaEspera>>() {
        });

        this.listaDeEspera    = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeEspera} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeEspera));
    }

    /**
     * Encontra o próximo ID livre.
     * <p>
     * Itera sobre a lista de espera, encontra o maior ID existente
     * e retorna esse valor incrementado em 1.
     * </p>
     *
     * @return O próximo ID disponível para uma nova entrada na fila.
     */
    private int proximoId() {
        int maxId = 0;


        for (FilaEspera filaEspera : listaDeEspera) {
            if (filaEspera.getId() > maxId) {
                maxId = filaEspera.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Adiciona um novo cliente (entrada) na fila de espera.
     * <p>
     * Se a entrada na fila tiver ID 0 (indicando que é nova), atribui um
     * novo ID usando {@link #proximoId()} e a adiciona à lista em memória.
     * </p>
     *
     * @param espera O objeto {@link FilaEspera} a ser adicionado.
     */
    public void adicionarNaFila(FilaEspera espera){
        if(espera.getId()==0){
            espera.setId(proximoId());
            listaDeEspera.add(espera);
            System.out.printf("Cliente adicionado a fila");
        }
    }

    /**
     * Remove uma entrada da fila de espera pelo seu ID.
     *
     * @param idParaRemover O ID da entrada na fila a ser removida.
     */
    public void removerDaFila(int idParaRemover){
        boolean foiRemovido = listaDeEspera.removeIf(espera -> espera.getId()==idParaRemover);{
            if(foiRemovido){
                System.out.printf("removido");
            }
        }

    }

    /**
     * Busca o próximo cliente na fila para um horário específico,
     * seguindo a lógica FIFO (First-In, First-Out).
     *
     * @param data A data do horário que vagou.
     * @param hora A hora do horário que vagou.
     * @return O objeto {@link FilaEspera} do cliente com maior prioridade
     * (mais antigo na fila) para aquele horário, ou {@code null} se
     * ninguém estiver esperando por aquele horário.
     */
    public FilaEspera buscarProximoDaFila(LocalDate data, LocalTime hora){
        List<FilaEspera> esperandoHorario = new ArrayList<>();

        for(FilaEspera espera : listaDeEspera){
            if(espera.getHoraDesejada() != null && espera.getHoraDesejada() != null){

                if(espera.getDataDesejada().equals(data) &&
                        espera.getHoraDesejada().equals(hora))

                    esperandoHorario.add(espera);
            }
            if(esperandoHorario.isEmpty()){
                return null;
            }


            Comparator<FilaEspera> decider = new ComparateFilaDeEspera();
            esperandoHorario.sort(decider);

        }
        return  esperandoHorario.get(0);
    }
}