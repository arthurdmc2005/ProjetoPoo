package br.barbearia.agendamento.repository; // (Pode ser em qualquer pacote)

import br.barbearia.agendamento.model.Agendamento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeAgendamento {

    // --- As 2 Ferramentas Internas Obrigatórias ---
    private ObjectMapper objectMapper; // O "Tradutor"
    private File arquivoJson;        // O "Endereço"

    /**
     * Construtor do Gerenciador.
     * @param nomeDoArquivo Onde você quer salvar (ex: "Servicos.JSON")
     */
    public GerenciadorDeAgendamento(String nomeDoArquivo) {

        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Deixa o JSON bonito

        this.arquivoJson = new File(nomeDoArquivo);
    }


    /**
     * ESTE É O MÉTODO QUE VOCÊ QUER.
     * Ele pega seu objeto e o salva no JSON.
     */
    public void adicionarServico(Agendamento servicoParaSalvar) {

        System.out.println("LOG: Recebido " + servicoParaSalvar.getTipoDeServico() + ". Salvando...");

        List<Agendamento> listaDeServicos = carregarDoJson();


        servicoParaSalvar.setId(listaDeServicos.size() + 1);
        listaDeServicos.add(servicoParaSalvar);


        salvarNoJson(listaDeServicos);

        System.out.println("LOG: Salvo com sucesso!");
    }


    /**
     * Função de ajuda (privada) para LER o arquivo.
     */
    private List<Agendamento> carregarDoJson() {
        try {
            if (arquivoJson.exists()) {
                return objectMapper.readValue(arquivoJson, new TypeReference<List<Agendamento>>() {});
            }
        } catch (IOException e) {
            System.err.println("ERRO ao carregar JSON: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Função de ajuda (privada) para SALVAR o arquivo.
     */
    private void salvarNoJson(List<Agendamento> listaParaSalvar) {
        try {
            objectMapper.writeValue(arquivoJson, listaParaSalvar);
        } catch (IOException e) {
            System.err.println("ERRO ao salvar JSON: " + e.getMessage());
        }
    }
}