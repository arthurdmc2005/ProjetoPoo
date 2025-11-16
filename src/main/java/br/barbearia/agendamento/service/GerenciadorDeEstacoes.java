package br.barbearia.agendamento.service;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Estacao;

/**
 * Classe utilitária (Service/Helper) para gerenciamento de estações.
 * <p>
 * Esta classe utiliza métodos e campos {@code static} para fornecer
 * acesso global ao estado das estações de atendimento. Ela inicializa
 * um array fixo de estações e permite listá-las, buscar estações livres
 * e liberar estações específicas.
 * </p>
 * <p>
 * <b>Nota:</b> Esta classe possui funcionalidade semelhante à
 * {@link br.barbearia.agendamento.repository.EstacaoRepository}.
 * </p>
 *
 * @see br.barbearia.agendamento.model.Estacao
 * @see br.barbearia.agendamento.repository.EstacaoRepository
 */
public class GerenciadorDeEstacoes {

    /** Vetor estático (tamanho fixo) que armazena as 3 estações. */
    private static Estacao[] estacoes = new Estacao[3];

    /**
     * Bloco inicializador estático.
     * É executado uma vez quando a classe é carregada pela JVM.
     * Popula o array {@code estacoes} com as 3 estações padrão.
     */
    static {
        estacoes[0] = new Estacao(1, true);   //Estação que tem lavagem
        estacoes[1] = new Estacao(2, false);
        estacoes[2] = new Estacao(3, false);
    }

    /**
     * Retorna o array completo de estações de atendimento.
     *
     * @return Um array de {@link Estacao} contendo todas as estações.
     */
    public static Estacao[] listarEstacoes() {
        return estacoes;
    }

    /**
     * Busca a primeira estação que não está marcada como "ocupada".
     *
     * @return A primeira {@link Estacao} livre encontrada, ou {@code null}
     * se todas as estações estiverem ocupadas.
     */
    public static Estacao buscarEstacaoLivre() {
        for (Estacao estacao : estacoes) {
            if (!estacao.isOcupada()) {
                return estacao;
            }
        }
        return null;
    }

    /**
     * Libera uma estação, marcando-a como não ocupada.
     *
     * @param id O ID da estação a ser liberada.
     */
    public static void liberarEstacao(int id) {
        for (Estacao estacao : estacoes) {
            if (estacao.getId() == id) {
                estacao.liberar();
                return;
            }
        }
    }

    /**
     * Construtor padrão
     */
    public GerenciadorDeEstacoes(){

    }
}