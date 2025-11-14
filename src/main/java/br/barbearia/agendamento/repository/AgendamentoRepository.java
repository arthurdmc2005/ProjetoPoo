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
 * @author Arthur
 */
public class AgendamentoRepository {

    private GerenciadorJSON<Agendamento> gerenciadorJSON;

    private List<Agendamento> listaDeAgendamentos;

    public AgendamentoRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Agendamento>>() {
        });

        this.listaDeAgendamentos     = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeAgendamentos));
    }

    /**
     *  Encontra o próximo ID livre.
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
     *  Adiciona um novo agendamento na lista e salva no JSON.
     */
    public void adicionarAgendamento(Agendamento agendamentoParaSalvar) {
        if (agendamentoParaSalvar.getId() == 0) {
            agendamentoParaSalvar.setId(proximoId());
            listaDeAgendamentos.add(agendamentoParaSalvar);
            salvarNoJson();
            System.out.println("LOG [Repo]: Agendamento salvo com ID " + agendamentoParaSalvar.getId());
        }
    }

    /**
     *  Encontra UM agendamento pelo seu ID.
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
     *  Retorna todos os agendamentos de uma data específica.
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
     *  Retorna todos os agendamentos de um tipo de serviço.
     */

    /**
     * Retorna todos os agendamentos de um autor (Funcionário).
     */

    public void atualizarAgendamento(Agendamento agendamentoParaAtualizar){
        for(int i = 0; i<listaDeAgendamentos.size();i++){
            Agendamento agendamentoAntigo = listaDeAgendamentos.get(i);
            if(agendamentoAntigo.getId()==agendamentoParaAtualizar.getId()){
            listaDeAgendamentos.set(i,agendamentoParaAtualizar);
            salvarNoJson();
            return;
            }
        }
    }

    public List<Agendamento> buscarPorEstacao(int estacaoNumero){
        List<Agendamento> agendamentosEncontrados = new ArrayList<>();

        for(Agendamento agendamentoDaLista : listaDeAgendamentos){
            if(agendamentoDaLista.getEstacaoNumero() == estacaoNumero){
                agendamentosEncontrados.add(agendamentoDaLista);

            }
        }
        return agendamentosEncontrados;
    }

    public void finalizarAgendamento(int idAgendamento){
        for(Agendamento agendamento : listaDeAgendamentos){
            if(agendamento.getId() == idAgendamento){
                agendamento.setStatus("Finalizado");
                salvarNoJson();
                return;
            }
        }
    }




}