package br.barbearia.usuarios;

public class Funcionario extends Pessoa {
    private String cargo;

    public Funcionario(String endereco, String telefone,String cargo,String cpf,String nome){
        super(nome,cpf,endereco,telefone);
        this.cargo = cargo;
    }
    public Funcionario(){}

    //Getters
    public String getCargo(){
        return cargo;
    }
    //Setters
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
}
