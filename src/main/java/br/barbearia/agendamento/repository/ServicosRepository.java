package br.barbearia.agendamento.repository;


import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório responsável pelo gerenciamento (CRUD) dos {@link Servicos} oferecidos.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados dos serviços,
 * utilizando um {@link GerenciadorJSON} para carregar e persistir a
 * lista de serviços em um arquivo JSON.
 * </p>
 *
 * @see Servicos
 * @see br.barbearia.repository.GerenciadorJSON
 * @see br.barbearia.agendamento.service.ServicesRoles
 */
public class ServicosRepository {

    /** O gerenciador genérico para manipulação do arquivo JSON. */
    private GerenciadorJSON<Servicos> gerenciadorJSON;

    /** A lista de serviços carregada em memória. */
    private List<Servicos> listaDeServicos;

    /**
     * Construtor do repositório de serviços.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de serviços existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/Servicos.JSON").
     */
    public ServicosRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Servicos>>() {
        });

        this.listaDeServicos = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeServicos} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeServicos));
    }


    /**
     * Função PRIVADA para descobrir qual o próximo ID disponível.
     * <p>
     * Itera sobre a lista, encontra o ID mais alto em uso e retorna
     * esse valor + 1.
     * </p>
     *
     * @return O próximo ID inteiro disponível.
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

    /**
     * Adiciona um novo serviço à lista e persiste no JSON.
     * <p>
     * Se o ID do {@code novoServico} for 0, ele atribui um novo ID
     * (via {@link #proximoId()}), adiciona à lista e chama
     * {@link #salvarNoJson()}.
     * </p>
     *
     * @param novoServico O objeto {@link Servicos} a ser adicionado (com ID 0).
     * @return O objeto {@link Servicos} com seu novo ID atribuído.
     */
    public Servicos salvarServico(Servicos novoServico){
        if(novoServico.getId()==0){
            novoServico.setId(proximoId());
            listaDeServicos.add(novoServico);
            salvarNoJson();
            System.out.println("LOG: Serviço" + novoServico.getServico() + "adicionado");

        }
        return novoServico;
    }

    /**
     * Remove um serviço da lista com base no seu ID.
     *
     * @param idParaRemover O ID do serviço a ser removido.
     */
    public void removerServico(int idParaRemover){
        boolean foiRemovido = listaDeServicos.removeIf(servicos -> servicos.getId()==idParaRemover);
        if(foiRemovido){
        }
    }

    /**
     * Atualiza um serviço existente na lista.
     * <p>
     * Procura o serviço pelo ID e substitui a instância antiga
     * pela nova instância {@code servicoParaAtualizar}.
     * </p>
     *
     * @param servicoParaAtualizar O objeto {@link Servicos} com os
     * dados atualizados (deve conter o ID do serviço original).
     */
    public void atualizarServicos(Servicos servicoParaAtualizar){

        for(int i = 0; i < listaDeServicos.size(); i++){
            Servicos servicoAntigo = listaDeServicos.get(i);
            if(servicoAntigo.getId()==servicoParaAtualizar.getId()){
                listaDeServicos.set(i, servicoParaAtualizar);
                return; // Parar o loop depois de achar

            }
        }

    }

    /**
     * Busca um serviço específico pelo seu ID.
     *
     * @param idBuscado O ID do serviço a ser procurado.
     * @return O objeto {@link Servicos} correspondente, ou {@code null}
     * se não for encontrado.
     */
    public Servicos buscarPorId(int idBuscado){
        for(Servicos servicos : listaDeServicos){
            if(servicos.getId() == idBuscado){
                return servicos;
            }
        }
        return null;

    }

    /**
     * Busca um serviço específico pelo seu nome (case-insensitive).
     *
     * @param nomeDoServicoBuscado O nome do serviço (ex: "Barba").
     * @return O primeiro objeto {@link Servicos} que corresponde ao nome
     * (ignorando maiúsculas/minúsculas), ou {@code null} se
     * não for encontrado.
     */
    public Servicos buscarPorTipoDeServico(String nomeDoServicoBuscado){
        for(Servicos servico : listaDeServicos){
            if(servico.getServico().equalsIgnoreCase(nomeDoServicoBuscado)){
                return servico;
            }
        }
        return null;
    }



}