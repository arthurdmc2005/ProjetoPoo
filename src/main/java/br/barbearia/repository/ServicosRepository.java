package br.barbearia.repository;


import br.barbearia.agendamento.Servico;
import br.barbearia.model.Funcionario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe responsável por todo gerenciamento de serviços da barbearia.
 * @author Arthur
 */

public class ServicosRepository {
    private String servico;
    private String data;
    private String produto;
    private double preco;
    private String desc;

    /**
     *  Lista que armazena os serviços da barbearia;
     */
    private List<Servico> listaDeServicos;

    /**
     * Caminho que leva até a JSON que vai armaenar as informações
     */
    private final String CAMINHO_JSON = "data/servicos.json";
    
    public ServicosRepository(){
        this.listaDeServicos = new ArrayList<>();
        carregarDados();
    }

    /**
     * Carrega os funcionarios do arquivo JSON.
     * Caso o arquivo não exista, cria uma lista vazia.
     */
    void carregarDados() {
        try (Reader reader = new FileReader(CAMINHO_JSON)) {
            Type tipoLista = new TypeToken<ArrayList<Funcionario>>() {}.getType();
            listaDeServicos = new Gson().fromJson(reader, tipoLista);
            if (listaDeServicos == null) {
                listaDeServicos = new ArrayList<>();
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
            gson.toJson(listaDeServicos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
