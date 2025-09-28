package br.barbearia.usuarios;


public class Funcionario extends Pessoa {
    private long id;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cpf, long id, String cargo, double salario) {
        super(nome, cpf);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
    }

   
    public String getCargo() {
        return cargo;
    }
    
    public double getSalario() {
        return salario;
    }

   
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}