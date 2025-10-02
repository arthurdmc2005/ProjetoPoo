package br.barbearia.admin;


import br.barbearia.servico.Servico;

import java.util.List;

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
