package br.barbearia.admin;

import br.barbearia.usuarios.Funcionario;
import com.google.gson.Gson;


import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class GestaoFuncionarios{
    
    private List<Funcionario> listaDeFuncionarios;
    private final String CAMINHO_JSON = "data/funcionarios.json";
    Funcionario funcionario;
    
    public GestaoFuncionarios(){
    }
    public Funcionario buscarFuncionariosPorCpf(String cpf){
        for(Funcionario funcionario : listaDeFuncionarios){
            if(funcionario.getCpf().equals(cpf)){
                return funcionario;
            }else{
        
        }
        
         }
        return null;
    }
    
    public void adicionarFuncionario(String cpf){
        for(Funcionario funcionarioCpf : this.listaDeFuncionarios){
            if(this.funcionario.getCpf().equals(cpf)){
                System.out.println("Esse cpf já está cadastrado.");
            }else{
                this.listaDeFuncionarios.add(funcionarioCpf);
           
                
            }
        }
    }
}
