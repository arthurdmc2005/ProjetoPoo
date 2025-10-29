package br.barbearia.repository;

// --- IMPORTAÇÕES CORRIGIDAS ---
import br.barbearia.model.Servicos; // 1. O Modelo que vamos salvar
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por todo gerenciamento de serviços da barbearia.
 * @author Arthur
 */
public class ServicosRepository {

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
    private List<Servicos> listaDeServicos;

    /**
     * Caminho que leva até a JSON que vai armaenar as informações
     */
    public ServicosRepository(String caminhoDoArquivo) {

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
        this.listaDeServicos = carregarDoJson();
    }

    /**
     * Função PRIVADA (só o repositório usa) para LER o arquivo JSON.
     * @return A lista de serviços que estava no arquivo.
     */
    private List<Servicos> carregarDoJson() {
        try {
            if (!arquivoJson.exists()) {
                return new ArrayList<>();
            }


            return objectMapper.readValue(arquivoJson, new TypeReference<List<Servicos>>() {});

        } catch (IOException e) {
            System.err.println("ERRO: Falha ao carregar JSON de serviços: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Função PRIVADA (só o repositório usa) para SALVAR o cache no JSON.
     */
    private void salvarNoJson() {
        try {
            // --- ERRO 3 CORRIGIDO ---
            // Trocado 'listaDeUsuariosCache' pelo nome correto da variável: 'listaDeServicos'
            objectMapper.writeValue(arquivoJson, listaDeServicos);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar JSON de serviços: " + e.getMessage());
        }
    }


    /**
     * Função PRIVADA para descobrir qual o próximo ID disponível.
     */
    private int proximoId() {
        int maxId = 0;
        for (Servicos servico : listaDeServicos) {
            // (Assumindo que sua classe Servico tem 'getId()')
            if (servico.getId() > maxId) {
                maxId = servico.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * [CREATE] Adiciona um novo serviço na lista e salva no JSON.
     */
    public void adicionarServico(Servicos servicoParaSalvar) {
        if (servicoParaSalvar.getId() == 0) {
            servicoParaSalvar.setId(proximoId());
            listaDeServicos.add(servicoParaSalvar);
            salvarNoJson();
            System.out.println("LOG: Serviço salvo com sucesso.");
        }

    }

    /**
     * [READ] Busca um serviço pelo nome.
     */
    public Servicos buscarPorServico(String nomeParaBuscar) {
        for (Servicos servico : listaDeServicos) {
            // (Assumindo que sua classe Servico tem 'getNome()')
            if (servico.getTipoDeServico() != null && servico.getTipoDeServico().equals(nomeParaBuscar)) {
                return servico;
            }
        }
        return null;
    }

}