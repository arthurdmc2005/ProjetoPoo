/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.barbearia.admin;

import br.barbearia.model.Funcionario;
import br.barbearia.repository.FuncionariosRepository;


public class Sistema {
    public static void main(String[] args) {
        FuncionariosRepository gestao = new FuncionariosRepository();
        
        Funcionario f1 = new Funcionario("Arthur", "12345670910","Barbeiro",2500.0);
        gestao.adicionarFuncionario(f1);
        
        gestao.listarTodosFuncionarios();
        
        gestao.atualizarFuncionario("12345678900","Gerente",3500);
        
        gestao.listarTodosFuncionarios();
        
        Funcionario buscado = gestao.buscarFuncionariosPorCpf("12345678900");
        if(buscado != null){
            System.out.println("Funcionário encontrado: " + buscado.getNome() + "Cargo: " + buscado.getCargo());
        }
        
        
    }
    
}
