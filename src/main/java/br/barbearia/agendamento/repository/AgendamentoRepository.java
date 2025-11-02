package br.barbearia.agendamento.repository;


import br.barbearia.agendamento.model.Agendamento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por todo gerenciamento de serviços da barbearia.
 * @author Arthur
 */
public class AgendamentoRepository {

    /**
     * Tradução do JSON
     */
    private ObjectMapper objectMapper;

    /**
     * Endereço do arquivo no disco.
     */
    private File arquivoJson;

    /**
     * Lista que armazena os serviços da barbearia;
     * (Corrigido para usar o modelo 'Servico' singular)
     */
    private List<Agendamento> listaDeAgendamentos;

    /**
     * Caminho que leva até a JSON que vai armaenar as informações
     */
    public AgendamentoRepository(String caminhoDoArquivo) {

        /**
         * Cria a instância;
         */
        this.objectMapper = new ObjectMapper();

        // Adicionando a linha que faltava para o JSON ficar bonito
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        /**
         * Cria o objeto "Endereço" que aponta para o nosso arquivo.
         */
        this.arquivoJson = new File(caminhoDoArquivo);

        /**
         * Chama nossa função (que está logo abaixo) para LER o arquivo
         */
        this.listaDeAgendamentos = carregarDoJson();
    }

    /**
     * Função PRIVADA (só o repositório usa) para LER o arquivo JSON.
     * @return A listaDeAgendamentosa de serviços que estava no arquivo.
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
     * Função PRIVADA (só o repositório usa) para SALVAR o cache no JSON.
     */
    private void salvarNoJson() {
        try {
            // --- ERRO 3 CORRIGIDO ---
            // Trocado 'listaDeAgendamentosaDeUsuariosCache' pelo nome correto da variável: 'listaDeAgendamentos'
            objectMapper.writeValue(arquivoJson, listaDeAgendamentos);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar JSON de serviços: " + e.getMessage());
        }
    }


    /**
     * Função PRIVADA para descobrir qual o próximo ID disponível.
     */
    private int proximoId() {
        int maxId = 0;
        for (Agendamento servico : listaDeAgendamentos) {
            // (Assumindo que sua classe Servico tem 'getId()')
            if (servico.getId() > maxId) {
                maxId = servico.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * [CREATE] Adiciona um novo serviço na listaDeAgendamentos e salva no JSON.
     */
    public void adicionarServico(Agendamento agendamentoParaSalvar) {
        if (agendamentoParaSalvar.getId() == 0) {
            agendamentoParaSalvar.setId(proximoId());
            listaDeAgendamentos.add(agendamentoParaSalvar);
            salvarNoJson();
            System.out.println("LOG: Serviço salvo com sucesso.");
        }

    }

    /**
     * [READ] Busca um serviço pelo nome.
     */
    public Agendamento buscarPorServico(String nomeParaBuscar) {
        for (Agendamento agendamento : listaDeAgendamentos) {
            // (Assumindo que sua classe Servico tem 'getNome()')
            if (agendamento.getTipoDeServico() != null && agendamento.getTipoDeServico().equals(nomeParaBuscar)) {
                return agendamento;
            }
        }
        return null;
    }

    /**
     *
     * @param dataBuscada Armazena a data que o usuário quer buscar.
     * @return retorna todos os serviços que foram realizados nessa data.
     */
    public List<Agendamento> buscarPorData(LocalDate dataBuscada){
        List<Agendamento> agendamentoEncontrados = new ArrayList<>();

        for(Agendamento servicoDaLista : listaDeAgendamentos){
            if(servicoDaLista.getData()!=null && servicoDaLista.getData().equals(dataBuscada)){
                agendamentoEncontrados.add(servicoDaLista);
            }
        }
        return agendamentoEncontrados;
    }

    /**
     *
     * @param tipoDeServicoBuscado Váriavel que armazena o tipo de serviço que o usuário está procurando.
     * @return retorna uma lista com todos os serviços que foram feitos. Ex: todos serviços de Corte, de barba ou de corte e barba.
     */
    public List<Agendamento> buscarPorTipoDeServico(String tipoDeServicoBuscado){
        List<Agendamento> tipoDeAgendamentoEncontrados = new ArrayList<>();

        for(Agendamento servicoDaLista : listaDeAgendamentos){
            if(servicoDaLista.getTipoDeServico()!=null && servicoDaLista.getTipoDeServico().equals(tipoDeServicoBuscado)){
                tipoDeAgendamentoEncontrados.add(servicoDaLista);
            }
        }
        return tipoDeAgendamentoEncontrados;
    }

    /**
     *
     * @param autorDoServico Váriavel que armazena o autor do Serviço que estou procurando;
     * @return retorna uma lista com todos os serviços feitos pelo autor;
     */
    public List<Agendamento> buscarPorautorDoServico(String autorDoServico){
        List<Agendamento> agendamentoFeitosPeloAutor = new ArrayList<>();

        for(Agendamento servicoDaLista : listaDeAgendamentos){
            if(servicoDaLista.getAutorDoServico()!=null && servicoDaLista.getAutorDoServico().equals(autorDoServico)){
                agendamentoFeitosPeloAutor.add(servicoDaLista);

            }
        }
        return agendamentoFeitosPeloAutor;
    }

    public Agendamento buscarPorId(int idBuscado){
        for(Agendamento agendamentoDaLista : listaDeAgendamentos){
            if(agendamentoDaLista.getId()== idBuscado){
                return agendamentoDaLista;
            }
        }
        return null;
    }

}