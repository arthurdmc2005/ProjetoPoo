package br.barbearia.repository;

import br.barbearia.model.RegistroPonto;
import br.barbearia.model.Usuarios;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório responsável pelo gerenciamento (CRUD) dos {@link RegistroPonto}.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados dos registros de ponto,
 * utilizando um {@link GerenciadorJSON} para carregar e persistir a
 * lista de registros de/para um arquivo JSON.
 * </p>
 *
 * @see RegistroPonto
 * @see GerenciadorJSON
 * @see br.barbearia.service.GerenciarPontoService
 */
public class GerenciarPontoRepository {

    /** O gerenciador genérico para manipulação do arquivo JSON. */
    private GerenciadorJSON<RegistroPonto> gerenciadorJSON;

    /** A lista de registros de ponto, carregada em memória. */
    private List<RegistroPonto> listaDeRegistroDePonto;

    /**
     * Construtor do repositório de registros de ponto.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de registros existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/RegistroPontos.JSON").
     */
    public GerenciarPontoRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<RegistroPonto>>() {
        });

        this.listaDeRegistroDePonto = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeRegistroDePonto} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeRegistroDePonto));
    }

    /**
     * Encontra o próximo ID disponível para um novo registro de ponto.
     * <p>
     * Itera sobre a lista, encontra o ID mais alto em uso e retorna
     * esse valor + 1.
     * </p>
     *
     * @return O próximo ID inteiro disponível.
     */
    private int proximoId() {
        int maxId = 0;

        for (RegistroPonto ponto : listaDeRegistroDePonto) {
            if (ponto.getId() > maxId) {
                maxId = ponto.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Adiciona um novo registro de ponto à lista em memória.
     * <p>
     * Se o ID do {@code novoPonto} for 0 (indicando que é novo),
     * o método atribui um novo ID (via {@link #proximoId()}) e
     * o adiciona à lista. Se o ID for diferente de 0, o método
     * retorna sem fazer nada.
     * </p>
     * <p>
     * <b>Nota:</b> Este método não chama {@link #salvarNoJson()}
     * automaticamente.
     * </p>
     *
     * @param novoPonto O objeto {@link RegistroPonto} a ser adicionado.
     */
    public void adicionarPonto(RegistroPonto novoPonto){
        if(novoPonto.getId()==0){
            novoPonto.setId(proximoId());
            listaDeRegistroDePonto.add(novoPonto);
        }else{
        }
    }


    /**
     * Busca os registros de ponto de um funcionário específico.
     * <p>
     * <b>Nota de Implementação:</b> Este método retorna a lista
     * assim que encontra o *primeiro* registro correspondente.
     * Portanto, a lista retornada conterá no máximo um elemento.
     * </p>
     *
     * @param funcionarioId O ID do funcionário cujos pontos são buscados.
     * @return Uma {@link List} contendo o primeiro
     * {@link RegistroPonto} encontrado, ou {@code null} se nenhum
     * registro for encontrado para esse ID.
     */
    public List<RegistroPonto> buscarPorFuncionario(int funcionarioId){

        List<RegistroPonto> pontosEncontrados = new ArrayList<>();

        for(RegistroPonto pontoBuscado : listaDeRegistroDePonto){
            if(pontoBuscado.getId()!= 0 && pontoBuscado.getFuncionarioId()==funcionarioId){

                pontosEncontrados.add(pontoBuscado);

                return pontosEncontrados;

            }
        }
        return null;
    }

}