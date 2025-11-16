package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Agendamento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar (adicionar, carregar, salvar) objetos
 * {@link Agendamento} em um arquivo JSON.
 *
 * <p><b>Nota:</b> Esta é uma implementação específica. A classe
 * {@link br.barbearia.repository.GerenciadorJSON}
 * (se disponível) geralmente oferece a mesma funcionalidade de
 * forma genérica e reutilizável.</p>
 *
 * @deprecated Prefira o uso da classe genérica
 * {@link br.barbearia.repository.GerenciadorJSON}.
 * @see br.barbearia.repository.GerenciadorJSON
 */
public class GerenciadorDeAgendamento {


    /** O "Tradutor" da biblioteca Jackson para converter Java <-> JSON. */
    private ObjectMapper objectMapper; // O "Tradutor"

    /** O "Endereço", o arquivo JSON no disco onde os dados são persistidos. */
    private File arquivoJson;        // O "Endereço"

    /**
     * Construtor do Gerenciador.
     * Inicializa o ObjectMapper (com formatação) e o arquivo JSON de destino.
     *
     * @param nomeDoArquivo O nome do arquivo (caminho) onde os
     * agendamentos serão salvos (ex: "Agendamentos.JSON").
     */
    public GerenciadorDeAgendamento(String nomeDoArquivo) {

        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Deixa o JSON bonito

        this.arquivoJson = new File(nomeDoArquivo);
    }


    /**
     * Adiciona um novo Agendamento à lista e salva a lista inteira no JSON.
     * <p>
     * (Nota: O nome do método é 'adicionarServico', mas ele opera
     * sobre um objeto {@link Agendamento}.)
     * </p>
     * <p>
     * Este método carrega a lista atual, calcula o próximo ID vago
     * (baseado no tamanho da lista), adiciona o novo agendamento e
     * persiste a lista atualizada no arquivo.
     * </p>
     *
     * @param servicoParaSalvar O objeto {@link Agendamento} a ser
     * salvo (apesar do nome do parâmetro).
     */
    public void adicionarServico(Agendamento servicoParaSalvar) {

        System.out.println("LOG: Recebido " + servicoParaSalvar.getServicoId() + ". Salvando...");

        List<Agendamento> listaDeServicos = carregarDoJson();

        servicoParaSalvar.setId(listaDeServicos.size() + 1);
        listaDeServicos.add(servicoParaSalvar);


        salvarNoJson(listaDeServicos);

        System.out.println("LOG: Salvo com sucesso!");
    }


    /**
     * Função de ajuda (privada) para LER o arquivo.
     * <p>
     * Carrega a lista completa de {@link Agendamento} do arquivo JSON.
     * Se o arquivo não existir, retorna uma lista vazia.
     * </p>
     *
     * @return Uma {@link List} de {@link Agendamento} (nunca nula).
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
     * <p>
     * Escreve a lista fornecida de agendamentos no arquivo JSON,
     * sobrescrevendo qualquer conteúdo anterior.
     * </p>
     *
     * @param listaParaSalvar A lista de {@link Agendamento} a ser
     * persistida.
     */
    private void salvarNoJson(List<Agendamento> listaParaSalvar) {
        try {
            objectMapper.writeValue(arquivoJson, listaParaSalvar);
        } catch (IOException e) {
            System.err.println("ERRO ao salvar JSON: " + e.getMessage());
        }
    }
}