package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Agendamento;
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

    private ObjectMapper objectMapper;
    private File arquivoJson;

    private List<Agendamento> listaDeAgendamentos;

    public AgendamentoRepository(String caminhoDoArquivo) {

        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.objectMapper.registerModule(new JavaTimeModule());

        this.arquivoJson = new File(caminhoDoArquivo);
        this.listaDeAgendamentos = carregarDoJson();
    }

    /**
     *  Carrega a lista do JSON para o cache.
     */
    private List<Agendamento> carregarDoJson() {
        try {
            if (!arquivoJson.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(arquivoJson, new TypeReference<List<Agendamento>>() {});
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao carregar JSON de Agendamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     *  Salva o cache (lista) de volta no JSON.
     */
    private void salvarNoJson() {
        try {
            objectMapper.writeValue(arquivoJson, listaDeAgendamentos);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar JSON de Agendamentos: " + e.getMessage());
        }
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
    public List<Agendamento> buscarPorTipoDeServico(String tipoDeServicoBuscado) {
        List<Agendamento> tipoDeAgendamentoEncontrados = new ArrayList<>();

        for (Agendamento agendamentoDaLista : listaDeAgendamentos) {
            if (agendamentoDaLista.getTipoDeServico() != null && agendamentoDaLista.getTipoDeServico().equals(tipoDeServicoBuscado)) {
                tipoDeAgendamentoEncontrados.add(agendamentoDaLista);
            }
        }
        return tipoDeAgendamentoEncontrados;
    }

    /**
     * Retorna todos os agendamentos de um autor (Funcionário).
     */
    public List<Agendamento> buscarPorAutorDoServico(String autorDoServico) {
        List<Agendamento> agendamentoFeitosPeloAutor = new ArrayList<>();

        for (Agendamento agendamentoDaLista : listaDeAgendamentos) {
            if (agendamentoDaLista.getAutorDoServico() != null && agendamentoDaLista.getAutorDoServico().equals(autorDoServico)) {
                agendamentoFeitosPeloAutor.add(agendamentoDaLista);
            }
        }
        return agendamentoFeitosPeloAutor;
    }

    // (fazer o atualizar agendamento e o remover agendamento)
}