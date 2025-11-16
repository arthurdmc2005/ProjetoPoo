package br.barbearia.service;

import br.barbearia.model.RegistroPonto;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciarPontoRepository;
import br.barbearia.repository.UsuarioRepository;

import java.time.LocalDateTime;

/**
 * Classe de serviço (Service Layer) responsável pelas regras de negócio
 * relacionadas ao {@link RegistroPonto} dos funcionários.
 * <p>
 * Esta classe abstrai a lógica de validação para registrar uma nova
 * "batida de ponto", verificando o tipo de registro e a existência
 * do funcionário antes de salvar no repositório.
 * </p>
 *
 * @see RegistroPonto
 * @see GerenciarPontoRepository
 * @see UsuarioRepository
 */
public class GerenciarPontoService {

    /** Repositório para acesso aos dados de registros de ponto. */
    private GerenciarPontoRepository gerenciarPontoRepository;

    /** Repositório para acesso aos dados de usuários (para validar o funcionário). */
    private UsuarioRepository usuarioRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência dos repositórios necessários.
     *
     * @param gerenciarPontoRepository A instância do repositório
     * de ponto a ser usada.
     * @param usuarioRepository A instância do repositório de usuários
     * a ser usada.
     */
    public GerenciarPontoService(GerenciarPontoRepository gerenciarPontoRepository, UsuarioRepository usuarioRepository){
        this.gerenciarPontoRepository = gerenciarPontoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Valida e registra uma nova batida de ponto para um funcionário.
     * <p>
     * Validações executadas:
     * 1. Verifica se o {@code tipo} (ex: "ENTRADA") não é nulo ou vazio.
     * 2. Verifica se o {@code funcionarioId} corresponde a um {@link Usuarios}
     * existente.
     * </p>
     * <p>
     * Se as validações passarem, cria um novo objeto {@link RegistroPonto}
     * com a data/hora atual e o envia para o repositório.
     * </p>
     *
     * @param funcionarioId O ID do {@link Usuarios} (Funcionário)
     * que está batendo o ponto.
     * @param tipo O tipo de registro (ex: "ENTRADA", "SAIDA").
     * @return O objeto {@link RegistroPonto} recém-criado e salvo.
     * @throws Exception Se o tipo for inválido ou se o funcionário
     * não for encontrado.
     */
    public RegistroPonto registroPonto(int funcionarioId, String tipo)throws Exception{
        System.out.println("Valindo ponto batido");
        if(tipo==null || tipo.trim().isEmpty()){
            throw new Exception("O tipo de ponto é obrigatório");
        }

        Usuarios funcionario = usuarioRepository.buscarPorId(funcionarioId);
        if(funcionario == null){
            throw new Exception("Funcionario não encontrado");
        }
        System.out.println("Monta o objeto ( log pra visualização )");

        RegistroPonto novoPonto = new RegistroPonto();
        novoPonto.setFuncionarioId(funcionarioId);
        novoPonto.setDataHora(LocalDateTime.now());
        novoPonto.setTipo(tipo.toUpperCase());

        gerenciarPontoRepository.adicionarPonto(novoPonto);

        return novoPonto;
    }
}