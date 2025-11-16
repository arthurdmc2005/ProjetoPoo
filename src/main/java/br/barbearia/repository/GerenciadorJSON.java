package br.barbearia.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Um gerenciador genérico de persistência de dados para arquivos JSON.
 * <p>
 * Esta classe usa Generics ({@code <T>}) para ser capaz de salvar (serializar) e
 * carregar (desserializar) listas de qualquer tipo de objeto
 * (ex: List&lt;Usuarios&gt;, List&lt;Produtos&gt;) de/para um arquivo JSON
 * específico.
 * </p>
 * <p>
 * Utiliza a biblioteca Jackson (ObjectMapper) e um {@link TypeReference}
 * para contornar a "type erasure" do Java, permitindo a correta
 * desserialização de listas genéricas.
 * </p>
 *
 * @param <T> O tipo genérico do objeto a ser persistido (ex: Usuarios, Produtos).
 * @see com.fasterxml.jackson.databind.ObjectMapper
 * @see com.fasterxml.jackson.core.type.TypeReference
 */
public class GerenciadorJSON<T> {

    /** O "tradutor" principal da biblioteca Jackson, usado para serializar e desserializar. */
    private ObjectMapper objectMapper;

    /** O arquivo físico no disco (ex: "Usuarios.JSON") onde os dados são armazenados. */
    private File arquivoJson;

    /**
     * Uma referência ao tipo completo da lista genérica (ex: new TypeReference&lt;List&lt;Usuarios&gt;&gt;(){}).
     * Essencial para que o Jackson saiba como desserializar a lista do JSON.
     */
    private TypeReference<List<T>> tipoDaLista;


    /**
     * Construtor do GerenciadorJSON.
     * <p>
     * Configura o ObjectMapper (com indentação e suporte a JavaTime) e
     * armazena as referências ao arquivo de destino e ao tipo da lista.
     * </p>
     *
     * @param caminhoDoArquivo O caminho para o arquivo JSON (ex: "BarbeariaComMaven/Usuarios.JSON").
     * @param tipoDaLista O {@link TypeReference} que descreve o tipo da lista
     * (ex: {@code new TypeReference<List<Usuarios>>(){}}).
     */
    public GerenciadorJSON(String caminhoDoArquivo, TypeReference<List<T>> tipoDaLista){
        this.objectMapper = new ObjectMapper();
        // Configura para "pretty print" (JSON formatado com indentação)
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Adiciona suporte para tipos do Java 8 Time (LocalDate, LocalDateTime)
        this.objectMapper.registerModule(new JavaTimeModule());
        this.arquivoJson = new File((caminhoDoArquivo));
        this.tipoDaLista = tipoDaLista;
    }

    /**
     * Carrega (desserializa) a lista de objetos do arquivo JSON.
     * <p>
     * Se o arquivo não existir ou se ocorrer um erro de leitura (JSON mal formatado),
     * uma nova {@link ArrayList} vazia é retornada com segurança.
     * </p>
     *
     * @return A {@link List<T>} contendo os dados carregados do arquivo, ou uma
     * lista vazia em caso de falha ou se o arquivo não existir.
     */
    public List<T> carregar(){
        try{
            if(!arquivoJson.exists()){
                return new ArrayList<>(); // Retorna lista vazia se o arquivo não existe
            }
            // Lê o arquivo e usa o tipoDaLista para converter o JSON de volta para a Lista<T>
            return objectMapper.readValue(arquivoJson,this.tipoDaLista);
        } catch (Exception e) {
            // Em caso de erro (ex: JSON corrompido), retorna uma lista vazia
            return new ArrayList<>();
        }

    }

    /**
     * Salva (serializa) a lista fornecida de objetos no arquivo JSON.
     * <p>
     * O conteúdo anterior do arquivo é sobrescrito.
     * Se um erro de I/O ocorrer, uma mensagem é impressa no console,
     * mas a exceção não é propagada.
     * </p>
     *
     * @param listaParaSalvar A {@link List<T>} completa a ser salva no arquivo.
     */
    public void salvar(List<T> listaParaSalvar){
        try{
            // Escreve a lista completa no arquivo, formatando o JSON (devido ao INDENT_OUTPUT)
            objectMapper.writeValue(arquivoJson,listaParaSalvar);
        } catch (IOException e) {
            System.out.println("Erro ao salvar no JSON");
        }
    }

}