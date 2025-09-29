package br.barbearia.admin;

import br.barbearia.usuarios.Funcionario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class GestaoFuncionarios {

    private List<Funcionario> listaDeFuncionarios;
    private final String CAMINHO_JSON = "data/funcionarios.json";

   
    public GestaoFuncionarios() {
        this.listaDeFuncionarios = new ArrayList<>();
        carregarDados();
    }

  void carregarDados() {
        try (Reader reader = new FileReader(CAMINHO_JSON)) {
            Type tipoLista = new TypeToken<ArrayList<Funcionario>>() {}.getType();
            listaDeFuncionarios = new Gson().fromJson(reader, tipoLista);
            if (listaDeFuncionarios == null) {
                listaDeFuncionarios = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo 'funcionarios.json' não encontrado. Um novo será criado ao salvar.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
     
    private void salvarDados() {
        File diretorio = new File("data");
        if (!diretorio.exists()){
            diretorio.mkdirs();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(CAMINHO_JSON)) {
            gson.toJson(listaDeFuncionarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Funcionario buscarFuncionariosPorCpf(String cpf) {
        for (Funcionario funcionario : listaDeFuncionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }

    public void adicionarFuncionario(Funcionario novoFuncionario) {
        if (buscarFuncionariosPorCpf(novoFuncionario.getCpf()) == null) {
            this.listaDeFuncionarios.add(novoFuncionario);
            salvarDados(); 
            System.out.println("Funcionário adicionado com sucesso!");
        } else {
            System.out.println("Erro: Já existe um funcionário cadastrado com o CPF " + novoFuncionario.getCpf());
        }
    }
    
    public void atualizarFuncionario(String cpf, String novoCargo, double novoSalario) {
  
        Funcionario funcionarioParaAtualizar = buscarFuncionariosPorCpf(cpf);
        
     
        if (funcionarioParaAtualizar != null) {
       
            funcionarioParaAtualizar.setCargo(novoCargo);
            funcionarioParaAtualizar.setSalario(novoSalario);
          
            
            salvarDados();
            System.out.println("Dados do funcionário " + funcionarioParaAtualizar.getNome() + " atualizados.");
        } else {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }
    

    public void listarTodosFuncionarios() {
        System.out.println("--- Lista de Funcionários ---");
        for(Funcionario f : listaDeFuncionarios) {
            System.out.println("Nome: " + f.getNome() + " | CPF: " + f.getCpf() + " | Cargo: " + f.getCargo());
        }
        System.out.println("---------------------------");
    }
}