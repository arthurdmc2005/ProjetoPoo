package br.barbearia.agendamento.Memento;

public class AgendamentoMemento {

    private  String status;
    private int estacaoNumero;

    public AgendamentoMemento(String status, int estacaoNumero){
        this.status = status;
        this.estacaoNumero = estacaoNumero;
    }

    public String getStatus(){
        return status;
    }

    public int getEstacaoNumero(){
        return estacaoNumero;
    }

}
