package br.barbearia.model;

public class Funcionario extends Pessoa {
    private int id;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cpf, int id, String cargo, double salario, String telefone) {
        super(nome, cpf);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
    }
    
    public Funcionario(String nome, String cpf, String cargo, double salario){
        super(nome,cpf);
        this.cargo = cargo;
        this.salario = salario;
    }
    
    public Funcionario(){
        
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
