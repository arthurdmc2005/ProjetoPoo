package br.barbearia.agendamento.model;

/**
 * Representa uma estação de atendimento física dentro da barbearia.
 * <p>
 * Esta é uma classe de modelo (POJO) que armazena as propriedades de uma
 * estação, incluindo seu identificador, se possui equipamentos especiais
 * (lavagem), e seu estado atual (ocupada ou livre).
 * </p>
 *
 * @see br.barbearia.agendamento.repository.EstacaoRepository
 */
public class Estacao {

    /** O identificador numérico da estação (ex: 1, 2, 3). */
    private int id;

    /** Flag booleana que indica se a estação possui equipamentos de lavagem/secador. */
    private boolean temLavagem;

    /** Flag booleana que indica o estado atual da estação (true = em uso, false = livre). */
    private boolean ocupada;

    /**
     * Construtor da Estacao.
     * Inicializa a estação com suas propriedades fixas (ID e equipamento).
     * Uma estação sempre começa com o estado 'ocupada' como {@code false} (livre).
     *
     * @param id O identificador único da estação.
     * @param temLavagem {@code true} se a estação possui equipamento de lavagem,
     * {@code false} caso contrário.
     */
    public Estacao(int id, boolean temLavagem) {
        this.id = id;
        this.temLavagem = temLavagem;
        this.ocupada = false;
    }

    /**
     * Retorna o ID da estação.
     *
     * @return O número de identificação da estação.
     */
    public int getId() {
        return id;
    }

    /**
     * Verifica se a estação possui equipamento de lavagem.
     *
     * @return {@code true} se a estação possui lavagem, {@code false} caso contrário.
     */
    public boolean isTemLavagem() {
        return temLavagem;
    }

    /**
     * Verifica se a estação está atualmente ocupada.
     *
     * @return {@code true} se a estação está em uso, {@code false} se está livre.
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Define o estado da estação como "ocupada" (em uso).
     */
    public void ocupar() {
        this.ocupada = true;
    }

    /**
     * Define o estado da estação como "livre" (disponível).
     */
    public void liberar() {
        this.ocupada = false;
    }

    /**
     * Retorna uma representação textual da estação, incluindo seu ID,
     * equipamento e estado de ocupação.
     *
     * @return Uma String formatada com os dados da estação.
     */
    @Override
    public String toString() {
        return "Estacao{" +
                "id=" + id +
                ", temLavagem=" + temLavagem +
                ", ocupada=" + ocupada +
                '}';
    }
}