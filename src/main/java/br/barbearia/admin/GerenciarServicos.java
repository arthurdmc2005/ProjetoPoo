
package br.barbearia.admin;

import br.barbearia.usuarios.Funcionario;


import br.barbearia.usuarios.Funcionario;
import br.barbearia.servico.Servico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
/**
 *Classe responsável por todo gerenciamento de serviços da barbearia.
 * @author Arthur
 */
public class GerenciarServicos {
    private String servico;
    private String data;
    private String produto;
    private double preco;
    private String desc;
    
    private List<Servico> listaDeServicos;
    
    private final String CAMINHO_JSON = data/servicos.json;
    
    
}
