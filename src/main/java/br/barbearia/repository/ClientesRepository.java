package br.barbearia.repository;

import br.barbearia.model.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsável pela gestão de clientes de uma barbearia.
 * Permite adicionar, remover, buscar, listar clientes e salvar/carregar
 * os dados em um arquivo JSON.
 */
public class ClientesRepository {

    /**Lista de clientes cadastrados*/
    private List<Cliente> listaDeClientes;
    
    /**Caminho JSON onde ficam armazenados os clientes*/
    private final String CAMINHO_JSON = "data/clientes.json";

    /**
     * Construtor da classe. Inicializa a lista de clientes e carrega os dados do arquivo JSON.
     */
    public ClientesRepository() {
        this.listaDeClientes = new ArrayList<>();
        carregarDados();
    }

     /**
     * Carrega os clientes do arquivo JSON. 
     * Caso o arquivo não exista, cria uma lista vazia.
     */
    void carregarDados() {
        try (Reader reader = new FileReader(CAMINHO_JSON)) {
            Type tipoLista = new TypeToken<ArrayList<Cliente>>() {}.getType();
            listaDeClientes = new Gson().fromJson(reader, tipoLista);
            if (listaDeClientes == null) {
                listaDeClientes = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo 'clientes.json' não encontrado. Um novo será criado ao salvar.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /**
     * Salva os clientes no arquivo JSON.
     */
    public void salvarDados() {
        try (FileWriter writer = new FileWriter(CAMINHO_JSON)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(listaDeClientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um novo cliente à lista e salva os dados no JSON.
     * @param novoCliente Onde fica armazenado os dados do novo cliente a ser adicionado
     */
    public void adicionarCliente(Cliente novoCliente) {
        this.listaDeClientes.add(novoCliente);
        salvarDados();
    }

    /**
     * Busca um cliente pelo CPF.
     * @param cpf O CPF do cliente que será buscado
     * @return Retorna o cliente caso o cpf seja encontrado, ou null se não for
     */
    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : this.listaDeClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        int tamanho = (this.listaDeClientes != null) ? this.listaDeClientes.size() : 0;

        return "ClientesRepository{" +
                "clientesCarregados=" + tamanho +
                ", CAMINHO_JSON='" + CAMINHO_JSON + '\'' +
                '}';
    }

    /**
     * Remove um cliente pelo CPF e salva os dados no JSON.
     * @param cpf O CPF do cliente a ser removido
     * @return true se o cliente foi removido, false caso contrário
     */
    public boolean removerClientePorCpf(String cpf) {
        Cliente clienteParaRemover = buscarClientePorCpf(cpf);
        if (clienteParaRemover != null) {
            this.listaDeClientes.remove(clienteParaRemover);
            salvarDados();
            return true;
        }
        return false;
    }

    /**
     * Lista todos os clientes cadastrados no console.
     */
    public void listarTodosOsClientes() {
        for (Cliente cliente : this.listaDeClientes) {
            System.out.println(cliente);
        } 
    }
}
