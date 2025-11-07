package br.barbearia.agendamento.model;

public class Estacao {
    private int id;
    private boolean temLavagem;
    private boolean ocupada;

    public Estacao(int id, boolean temLavagem) {
        this.id = id;
        this.temLavagem = temLavagem;
        this.ocupada = false;
    }

    public int getId() {
        return id;
    }

    public boolean isTemLavagem() {
        return temLavagem;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar() {
        this.ocupada = true;
    }

    public void liberar() {
        this.ocupada = false;
    }

    @Override
    public String toString() {
        return "Estacao{" +
                "id=" + id +
                ", temLavagem=" + temLavagem +
                ", ocupada=" + ocupada +
                '}';
    }
}
