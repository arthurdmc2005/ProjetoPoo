package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Estacao;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por todo gerenciamento (CRUD) de Agendamentos.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados de agendamentos,
 * utilizando um {@link GerenciadorJSON} para persistir e carregar
 * a lista de agendamentos de um arquivo JSON.
 * </p>
 * @author Arthur
 */
public class AgendamentoRepository {

    /**
     * O gerenciador genérico para manipulação do arquivo JSON.
     */
    private GerenciadorJSON<Agendamento> gerenciadorJSON;

    /**
     * A lista de agendamentos carregada em memória.
     */
    private List<Agendamento> listaDeAgendamentos;

    /**
     * Construtor do repositório de agendamentos.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de agendamentos existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/Agendamento.JSON").
     */
    public AgendamentoRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Agendamento>>() {
        });

        this.listaDeAgendamentos     = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeAgendamentos} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeAgendamentos));
    }

    /**
     * Encontra o próximo ID livre.
     * <p>
     * Itera sobre a lista de agendamentos, encontra o maior ID existente
     * e retorna esse valor incrementado em 1.
     * </p>
     *
     * @return O próximo ID disponível para um novo agendamento.
     */
    private int proximoId() {
        int maxId = 0;


        for (Agendamento agendamento : listaDeAgendamentos) {
            if (agendamento.getId() > maxId) {
                maxId = agendamento.getId();
            }
        }
        return maxId + 1;
    }


    /**
     * Adiciona um novo agendamento na lista e salva no JSON.
     * <p>
     * Se o agendamento tiver ID 0 (indicando que é novo), atribui um
     * novo ID usando {@link #proximoId()} e o adiciona à lista em memória.
     * </p>
     *
     * @param agendamentoParaSalvar O objeto {@link Agendamento} a ser adicionado.
     */
    public void adicionarAgendamento(Agendamento agendamentoParaSalvar) {
        if (agendamentoParaSalvar.getId() == 0) {
            agendamentoParaSalvar.setId(proximoId());
            listaDeAgendamentos.add(agendamentoParaSalvar);
            System.out.println("LOG [Repo]: Agendamento salvo com ID " + agendamentoParaSalvar.getId());
        }
    }

    /**
     * Encontra UM agendamento pelo seu ID.
     *
     * @param idBuscado O ID do agendamento a ser procurado.
     * @return O objeto {@link Agendamento} se encontrado, ou {@code null}
     * se nenhum agendamento com esse ID existir.
     */
    public Agendamento buscarPorId(int idBuscado) {
        for (Agendamento agendamentoDaLista : listaDeAgendamentos) {
            if (agendamentoDaLista.getId() == idBuscado) {
                return agendamentoDaLista;
            }
        }
        return null;
    }

    /**
     * Retorna todos os agendamentos de uma data específica.
     *
     * @param dataBuscada A {@link LocalDate} pela qual filtrar.
     * @return Uma {@link List} de {@link Agendamento} contendo todos os
     * agendamentos marcados para a data especificada. Retorna uma
     * lista vazia se nenhum for encontrado.
     */
    public List<Agendamento> buscarPorData(LocalDate dataBuscada) {
        List<Agendamento> agendamentoEncontrados = new ArrayList<>();

        for (Agendamento agendamentoDaLista : listaDeAgendamentos) {
            if (agendamentoDaLista.getData() != null && agendamentoDaLista.getData().equals(dataBuscada)) {
                agendamentoEncontrados.add(agendamentoDaLista);
            }
        }
        return agendamentoEncontrados;
    }

    /**
     * Retorna todos os agendamentos de um tipo de serviço.
     * (Método não implementado)
     */

    /**
     * Retorna todos os agendamentos de um autor (Funcionário).
     * (Método não implementado)
     */

    /**
     * Atualiza um agendamento existente na lista.
     * <p>
     * Procura o agendamento pelo ID e substitui a instância antiga
     * pela nova instância {@code agendamentoParaAtualizar}.
     * </p>
     *
     * @param agendamentoParaAtualizar O objeto {@link Agendamento} com os
     * dados atualizados (deve conter o ID do agendamento original).
     */
    public void atualizarAgendamento(Agendamento agendamentoParaAtualizar){
        for(int i = 0; i<listaDeAgendamentos.size();i++){
            Agendamento agendamentoAntigo = listaDeAgendamentos.get(i);
            if(agendamentoAntigo.getId()==agendamentoParaAtualizar.getId()){
                listaDeAgendamentos.set(i,agendamentoParaAtualizar);
                return;
            }
        }
    }

    /**
     * Busca todos os agendamentos associados a um número de estação.
     *
     * @param estacaoNumero O ID da estação.
     * @return Uma {@link List} de {@link Agendamento} vinculados àquela
     * estação. Retorna uma lista vazia se nenhum for encontrado.
     */
    public List<Agendamento> buscarPorEstacao(int estacaoNumero){
        List<Agendamento> agendamentosEncontrados = new ArrayList<>();

        for(Agendamento agendamentoDaLista : listaDeAgendamentos){
            if(agendamentoDaLista.getEstacaoNumero() == estacaoNumero){
                agendamentosEncontrados.add(agendamentoDaLista);

            }
        }
        return agendamentosEncontrados;
    }

    /**
     * Altera o status de um agendamento para "Finalizado".
     *
     * @param idAgendamento O ID do agendamento a ser finalizado.
     */
    public void finalizarAgendamento(int idAgendamento){
        for(Agendamento agendamento : listaDeAgendamentos){
            if(agendamento.getId() == idAgendamento){
                agendamento.setStatus("Finalizado");
                return;
            }
        }
    }

    /**
     * Retorna uma referência direta à lista de agendamentos em memória.
     *
     * @return A {@link List} de {@link Agendamento}.
     */
    public List<Agendamento> listaDeAgendamentos(){
        return listaDeAgendamentos;
    }
}