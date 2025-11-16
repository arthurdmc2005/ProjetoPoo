package br.barbearia.OrdensDeServiço;

import br.barbearia.model.RegistroPonto;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório para gerenciamento (CRUD) de {@link OrdensDeServicoModel}.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados das Ordens de Serviço,
 * utilizando um {@link GerenciadorJSON} para carregar e persistir a
 * lista de OS de/para um arquivo JSON.
 * </p>
 *
 * @see OrdensDeServicoModel
 * @see br.barbearia.repository.GerenciadorJSON
 * @see OrdensDeServicoRoles
 */
public class OrdensDeServicoRepository {

    /** O gerenciador genérico para manipulação do arquivo JSON de Ordens de Serviço. */
    private GerenciadorJSON<OrdensDeServicoModel> gerenciadorJSON;

    /** A lista de Ordens de Serviço, carregada em memória. */
    private List<OrdensDeServicoModel> ordensDeServicoLista;

    /**
     * Construtor do repositório de Ordens de Serviço.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de OS existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/OrdensDeServico.JSON").
     */
    public OrdensDeServicoRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<OrdensDeServicoModel>>() {
        });

        this.ordensDeServicoLista = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code ordensDeServicoLista} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.ordensDeServicoLista));
    }

    /**
     * Encontra o próximo ID disponível para uma nova Ordem de Serviço.
     * <p>
     * Itera sobre a lista, encontra o ID de OS ({@code ordemId})
     * mais alto em uso e retorna esse valor + 1.
     * </p>
     *
     * @return O próximo ID inteiro disponível.
     */
    private int proximoId() {
        int maxId = 0;

        for (OrdensDeServicoModel os : ordensDeServicoLista) {
            if (os.getOrdemId() > maxId) {
                maxId = os.getOrdemId();
            }
        }
        return maxId + 1;
    }

    /**
     * Adiciona uma nova Ordem de Serviço à lista em memória.
     * <p>
     * Se o ID da OS ({@code ordemId}) for 0, o método atribui
     * um novo ID (via {@link #proximoId()}) e a adiciona à lista.
     * </p>
     * <p>
     * <b>Nota:</b> Este método não chama {@link #salvarNoJson()}
     * automaticamente.
     * </p>
     *
     * @param os O objeto {@link OrdensDeServicoModel} a ser registrado.
     */
    public void registrarOrdemDeServico(OrdensDeServicoModel os){
        if(os.getOrdemId()==0){
            os.setOrdemId(proximoId());
            ordensDeServicoLista.add(os);
        }
    }

    /**
     * Busca todas as Ordens de Serviço associadas a um CPF de cliente.
     * <p>
     * A busca é case-insensitive.
     * </p>
     *
     * @param cpfBuscado O CPF do cliente a ser procurado.
     * @return Uma {@link List} de {@link OrdensDeServicoModel} contendo
     * todas as ordens encontradas for o CPF. Retorna uma
     * lista vazia se nenhuma for encontrada.
     */
    public List<OrdensDeServicoModel> buscarOSPeloCpf(String cpfBuscado){

        List<OrdensDeServicoModel> ordensEncontradas = new ArrayList<>();

        for(OrdensDeServicoModel ordem : ordensDeServicoLista){
            if(ordem.getClienteCpf() != null && ordem.getClienteCpf().equalsIgnoreCase(cpfBuscado)){
                ordensEncontradas.add(ordem);
            }
        }
        return ordensEncontradas;
    }

    /**
     * Busca todas as Ordens de Serviço registradas em uma data específica.
     * <p>
     * A busca requer uma correspondência exata da String de data.
     * </p>
     *
     * @param dataBuscada A data (como String, ex: "dd/MM/yyyy") a ser procurada.
     * @return Uma {@link List} de {@link OrdensDeServicoModel} contendo
     * todas as ordens encontradas para a data. Retorna uma
     * lista vazia se nenhuma for encontrada.
     */
    public List<OrdensDeServicoModel> buscarOSPelaData(String dataBuscada){

        List<OrdensDeServicoModel> ordensEnconstrada = new ArrayList<>();

        for(OrdensDeServicoModel ordem : ordensDeServicoLista){
            if(ordem.getDataDoServico().equals(dataBuscada)){
                ordensEnconstrada.add(ordem);
            }
        }
        return ordensEnconstrada;
    }

    /**
     * Retorna o número total de Ordens de Serviço registradas.
     *
     * @return O tamanho da lista {@code ordensDeServicoLista},
     * ou 0 se a lista for nula.
     */
    public int contadorDeOrdensDeServico(){
        if(ordensDeServicoLista == null){
            return 0;
        }
        return ordensDeServicoLista.size();
    }

}