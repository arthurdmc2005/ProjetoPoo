package br.barbearia.agendamento.Memento;

/**
 * Representa o 'Caretaker' (Zelador) no padrão de projeto Memento.
 * Esta classe é responsável por armazenar o estado (Memento) de um Agendamento.
 * Neste caso, ela armazena apenas o último estado salvo para permitir uma
 * operação de "desfazer" simples.
 *
 * @see AgendamentoMemento
 * @see br.barbearia.agendamento.model.Agendamento (que atua como Originator)
 */
public class AgendamentoCaraTaker {
    /**
     * Classe que armazena os Mementos criados;
     * Armazena o último {@link AgendamentoMemento} salvo.
     */
    private AgendamentoMemento ultimoEstado;

    /**
     * Salva um novo estado (Memento) no Zelador.
     * Qualquer estado anterior é sobrescrito.
     *
     * @param memento O objeto Memento contendo o estado a ser salvo.
     */
    public void salvar(AgendamentoMemento memento){
        this.ultimoEstado = memento;
    }

    /**
     * Recupera o último estado (Memento) salvo.
     *
     * @return O último {@link AgendamentoMemento} que foi salvo, ou {@code null}
     * se nenhum estado tiver sido salvo ainda.
     */
    public AgendamentoMemento desfazer(){
        return this.ultimoEstado;
    }
}