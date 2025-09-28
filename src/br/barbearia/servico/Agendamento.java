package br.barbearia.servico;

import br.barbearia.usuarios.Cliente;
import br.barbearia.usuarios.Funcionario;

import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private LocalDateTime datahora;
    private String status;
    private double valor;
    private Cliente cliente;
    private Funcionario funcionario;

    public Agendamento(int id, LocalDateTime datahora, String status, double valor, Cliente cliente, Funcionario funcionario){
        this.id = id;
        this.datahora = datahora;
        this.status = status;
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public LocalDateTime getDataHora(){
        return datahora;
    }

    public void setDataHora(LocalDateTime datahora){
        this.datahora = datahora;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Funcionario getFuncionario(){
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }

    public void confirmar() {
        this.status = "confirmado";
    }

    public void finalizar() {
        this.status = "atendido";
    }

    public void cancelar() {
        this.status = "cancelado";
        double multa = calcularMultaCancelamento();
        System.out.println("Multa de cancelamento: R$ " + multa);
    }

    public double calcularMultaCancelamento() {

        return this.valor * 0.35;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "Cliente='" + cliente.getNome() + '\'' +
                ", Barbeiro='" + funcionario.getNome() + '\'' +
                ", Data=" + datahora +
                ", Status='" + status + '\'' +
                '}';
    }
}

