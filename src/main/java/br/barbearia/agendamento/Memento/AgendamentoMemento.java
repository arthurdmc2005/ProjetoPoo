package br.barbearia.agendamento.Memento;

/**
 * Representa o 'Memento' (Lembrança) no padrão de projeto Memento.
 * Este objeto armazena um "instantâneo" do estado interno de um objeto
 * {@link br.barbearia.agendamento.model.Agendamento} (o Originator).
 * * <p>Neste caso, ele armazena especificamente o status e o número da estação,
 * permitindo que o Agendamento reverta para este estado posteriormente.</p>
 *
 * @see AgendamentoCaraTaker
 * @see br.barbearia.agendamento.model.Agendamento
 */
public class AgendamentoMemento {

    /**
     * O estado de status (ex: "AGENDADO", "FINALIZADO") salvo no Memento.
     */
    private  String status;

    /**
     * O número da estação de atendimento salvo no Memento.
     */
    private int estacaoNumero;

    /**
     * Construtor do Memento.
     * Chamado pelo {@code Agendamento} (Originator) para criar um novo
     * instantâneo de seu estado.
     *
     * @param status O status atual do agendamento a ser salvo.
     * @param estacaoNumero O número da estação atual do agendamento a ser salvo.
     */
    public AgendamentoMemento(String status, int estacaoNumero){
        this.status = status;
        this.estacaoNumero = estacaoNumero;
    }

    /**
     * Retorna o status que foi salvo neste Memento.
     * Este método é usado pelo Originator ( Agendamento ) para restaurar o estado
     *
     * @return O status salvo.
     */
    public String getStatus(){
        return status;
    }

    /**
     * Retorna o número da estação que foi salvo neste Memento.
     * Este método é usado pelo Originator (Agendamento) para restaurar seu estado.
     *
     * @return O número da estação salvo.
     */
    public int getEstacaoNumero(){
        return estacaoNumero;
    }

}