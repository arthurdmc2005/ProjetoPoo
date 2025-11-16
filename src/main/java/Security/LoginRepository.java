package Security;

import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar operações relacionadas aos logins do sistema,
 * realizando o carregamento, registro e persistência dos dados em um arquivo JSON.
 * <p>
 * A classe utiliza {@link GerenciadorJSON} para manipular a leitura e escrita
 * dos objetos do tipo {@link LoginModel}.
 * </p>
 *
 * @see LoginModel
 * @see GerenciadorJSON
 * @see LoginServices
 */
public class LoginRepository {

    /** Gerenciador responsável por salvar e carregar dados do arquivo JSON. */
    private GerenciadorJSON<LoginModel> gerenciadorJSON;

    /** Lista de logins carregados do arquivo JSON. */
    private List<LoginModel> listaDeLogins;

    /**
     * Construtor da classe LoginRepository.
     * <p>
     * Inicializa o gerenciador JSON com o caminho do arquivo e
     * carrega a lista de logins existente no arquivo para a memória.
     * </p>
     *
     * @param caminhoDoArquivo caminho do arquivo JSON onde os logins
     * serão armazenados (ex: "BarbeariaComMaven/Login.JSON").
     */
    public LoginRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<LoginModel>>() {
        });

        this.listaDeLogins = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva a lista de logins atual (em memória) no arquivo JSON.
     */
    public void salvarNoJson() {
        gerenciadorJSON.salvar((this.listaDeLogins));
    }

    /**
     * Gera um novo ID incremental para um login.
     * <p>
     * O ID é calculado encontrando o maior ID existente na lista
     * e adicionando 1.
     * </p>
     *
     * @return próximo ID disponível.
     */
    private int proximoId() {
        int maxId = 0;

        for (LoginModel loginModel : listaDeLogins) {
            if (loginModel.getId() > maxId) {
                maxId = loginModel.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Registra um novo login no sistema.
     * <p>
     * Se o login ainda não possuir ID (ou seja, ID == 0), um novo
     * ID é atribuído.
     * Em seguida, o login é adicionado à lista e salvo no arquivo JSON
     * (chamando {@link #salvarNoJson()}).
     * </p>
     *
     * @param novoLogin novo login a ser registrado.
     */
    public void registrarLogin(LoginModel novoLogin) {
        if (novoLogin.getId() == 0) {
            novoLogin.setId(proximoId());
            listaDeLogins.add(novoLogin);
        }
        salvarNoJson();
    }

    /**
     * Retorna uma cópia da lista de logins cadastrados.
     * <p>
     * Retorna um {@code new ArrayList} para evitar que modificações
     * externas afetem a lista interna do repositório.
     * </p>
     *
     * @return lista de {@link LoginModel}.
     */
    public List<LoginModel> buscarPelosLogins() {
        return new ArrayList<>(this.listaDeLogins);
    }
}