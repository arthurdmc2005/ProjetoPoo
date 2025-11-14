package br.barbearia.agendamento.Memento;

public class AgendamentoCaraTaker {
    /**
     * Classe que armazena os Mementos criados;
     */
    private AgendamentoMemento ultimoEstado;

    public void salvar(AgendamentoMemento memento){
        this.ultimoEstado = memento;
    }

    public AgendamentoMemento desfazer(){
        return this.ultimoEstado;
    }
}
