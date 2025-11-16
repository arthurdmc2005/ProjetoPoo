package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Estacao;
import java.util.Arrays;
import java.util.List;

/**
 * Repositório para gerenciamento das Estações de Atendimento.
 * <p>
 * Esta classe é responsável por armazenar e gerenciar o estado das
 * estações físicas da barbearia. Conforme o Requisito 5, as estações
 * são armazenadas em um array de tamanho fixo.
 * </p>
 *
 * @see Estacao
 * @see br.barbearia.agendamento.service.AgendamentoServices
 */
public class EstacaoRepository {

    /**
     * (Questão 5) Armazena de forma estática (em um vetor de tamanho fixo)
     * as informações das 3 estações de atendimento.
     */
    private Estacao[] estacoes = {
            new Estacao(1, true),   // Estação 1 com lavagem e secador
            new Estacao(2, false),  // Estação 2 comum
            new Estacao(3, false)   // Estação 3 comum
    };

    /**
     * Retorna uma lista imutável de todas as estações de atendimento.
     *
     * @return Uma {@link List} contendo todas as {@link Estacao} do repositório.
     */
    public List<Estacao> listarTodas() {
        return Arrays.asList(estacoes);
    }

    /**
     * Busca uma estação específica pelo seu número (ID).
     *
     * @param numeroEstacao O ID da estação a ser procurada.
     * @return O objeto {@link Estacao} correspondente ao ID, ou {@code null}
     * se nenhuma estação for encontrada.
     */
    public Estacao buscarPorNumero(int numeroEstacao) {
        for (Estacao estacao : estacoes) {
            if (estacao.getId() == numeroEstacao) {
                return estacao;
            }
        }
        return null;
    }

    /**
     * Tenta marcar uma estação como "ocupada".
     * <p>
     * A operação falhará se a estação não for encontrada ou se ela
     * já estiver ocupada.
     * </p>
     *
     * @param numeroEstacao O ID da estação a ser ocupada.
     * @return {@code true} se a estação foi ocupada com sucesso,
     * {@code false} caso contrário.
     */
    public boolean ocuparEstacao(int numeroEstacao) {
        Estacao estacao = buscarPorNumero(numeroEstacao);
        if (estacao == null || estacao.isOcupada()) {
            return false;
        }
        estacao.ocupar();
        System.out.println("LOG [Repository]: Estação " + numeroEstacao + " ocupada.");
        return true;
    }

    /**
     * Tenta marcar uma estação como "livre".
     * <p>
     * A operação falhará se a estação não for encontrada ou se ela
     * já estiver livre.
     * </p>
     *
     * @param numeroEstacao O ID da estação a ser liberada.
     * @return {@code true} se a estação foi liberada com sucesso,
     * {@code false} caso contrário.
     */
    public boolean liberarEstacao(int numeroEstacao) {
        Estacao estacao = buscarPorNumero(numeroEstacao);
        if (estacao == null || !estacao.isOcupada()) {
            return false;
        }
        estacao.liberar();
        System.out.println("LOG [Repository]: Estação " + numeroEstacao + " liberada.");
        return true;
    }
}