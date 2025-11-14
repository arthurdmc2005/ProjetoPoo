package br.barbearia.agendamento.Memento;

public class AgendamentoCaraTaker {
    private AgendamentoMemento ultimoEstado;

    public void salvar(AgendamentoMemento memento){
        this.ultimoEstado = memento;
    }

    public AgendamentoMemento desfazer(){
        return this.ultimoEstado;
    }
}
