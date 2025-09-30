package br.barbearia.admin;

import br.barbearia.usuarios.Funcionario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe responsável pela gestão de funcionarios de uma barbearia.
 * Permite adicionar, remover, buscar, listar barbeiros e salvar/carregar
 * os dados em um arquivo JSON.
 */
public class GestaoFuncionarios {

    /**Lista que armazena todos os funcionarios*/
    private List<Funcionario> listaDeFuncionarios;
    
    private final String CAMINHO_JSON = "data/funcionarios.json";

   
    public GestaoFuncionarios() {
        this.listaDeFuncionarios = new ArrayList<>();
        carregarDados();
    }

    /**
     * Carrega os funcionarios do arquivo JSON. 
     * Caso o arquivo não exista, cria uma lista vazia.
     */
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

   
     /**
     * Salva os funcionarios no arquivo JSON.
     */
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

    /**
     * Pesquisa os funcionarios por cpf e armazena em funcionario ( etiqueta ) 
     * @param cpf cpf do funcionario que está sendo procurado
     * @return  se o cpf pesquisado for igual a um cpf existente na listaDeFuncionarios retorna esse funcionario, se não retorna nada.
     */
    public Funcionario buscarFuncionariosPorCpf(String cpf) {
        for (Funcionario funcionario : listaDeFuncionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }
    
/**
     * Adiciona um novo funcionario à lista e salva os dados no JSON.
     * @param novoFuncionario Onde fica armazenado os dados do novo funcionario.
     */
    public void adicionarFuncionario(Funcionario novoFuncionario) {
        if (buscarFuncionariosPorCpf(novoFuncionario.getCpf()) == null) {
            this.listaDeFuncionarios.add(novoFuncionario);
            salvarDados(); // Salva a lista atualizada no arquivo JSON
            System.out.println("Funcionário adicionado com sucesso!");
        } else {
            System.out.println("Erro: Já existe um funcionário cadastrado com o CPF " + novoFuncionario.getCpf());
        }
    }
    
    /**Se o cpf que foi passado estiver registrado no JSON eu atualizo cargo e salario e depois salvo salvo os dados no JSON
     * 
     * @param cpf cpf do funcionario que vai ser atualizado.
     * @param novoCargo armazena o novo cargo que o funcionario vai assumir.
     * @param novoSalario armazena o novo salario do funcionario.
     */
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
    
/**
 * Lista os funcionarios já registrados no JSON
 */
    public void listarTodosFuncionarios() {
        System.out.println("--- Lista de Funcionários ---");
        for(Funcionario f : listaDeFuncionarios) {
            System.out.println("Nome: " + f.getNome() + " | CPF: " + f.getCpf() + " | Cargo: " + f.getCargo());
        }
        System.out.println("---------------------------");
    }
}