package br.barbearia.agendamento.repository;


import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Servicos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicosRepository {

    private ObjectMapper objectMapper;

    private File arquivoJson;

    List<Servicos> listaDeServicos;

    public ServicosRepository(String caminhoDoArquivo) {

        /**
         * Cria a instância;
         */
        this.objectMapper = new ObjectMapper();

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
     * @return A listaDeAgendamentosa de serviços que estava no arquivo.
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
            if (servico.getId() > maxId) {
                maxId = servico.getId();
            }
        }
        return maxId + 1;
    }

    public Servicos salvarServico(Servicos novoServico){
        if(novoServico.getId()==0){
            novoServico.setId(proximoId());
            listaDeServicos.add(novoServico);
            salvarNoJson();
            System.out.println("LOG: Serviço" + novoServico.getServico() + "adicionado");

        }
        return novoServico;
    }
}
