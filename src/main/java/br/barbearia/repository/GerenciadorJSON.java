package br.barbearia.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorJSON<T> {

    private ObjectMapper objectMapper;
    private File arquivoJson;

    private TypeReference<List<T>> tipoDaLista;


    public GerenciadorJSON(String caminhoDoArquivo, TypeReference<List<T>> tipoDaLista){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.objectMapper.registerModule(new JavaTimeModule());
        this.arquivoJson = new File((caminhoDoArquivo));
        this.tipoDaLista = tipoDaLista;
    }

    public List<T> carregar(){
        try{
            if(!arquivoJson.exists()){
                return new ArrayList<>();
            }
            return objectMapper.readValue(arquivoJson,this.tipoDaLista);
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }
    public void salvar(List<T> listaParaSalvar){
        try{
        objectMapper.writeValue(arquivoJson,listaParaSalvar);
    } catch (IOException e) {
            System.out.println("Erro ao salvarn o JSON");
        }
    }

}
