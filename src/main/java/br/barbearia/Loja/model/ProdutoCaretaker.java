package br.barbearia.Loja.model;

public class ProdutoCaretaker {
    private ProdutoMemento estadoAnterior;

    public void salvar(ProdutoMemento memento) {
        this.estadoAnterior = memento;
    }

    public ProdutoMemento restaurar() {
        return this.estadoAnterior;
    }
}

