package br.barbearia.repository;

import br.barbearia.model.RegistroPonto;
import br.barbearia.model.Usuarios;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciarPontoRepository {

    private GerenciadorJSON<RegistroPonto> gerenciadorJSON;

    private List<RegistroPonto> listaDeRegistroDePonto;

    public GerenciarPontoRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<RegistroPonto>>() {
        });

        this.listaDeRegistroDePonto = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeRegistroDePonto));
    }

    private int proximoId() {
        int maxId = 0;

        for (RegistroPonto ponto : listaDeRegistroDePonto) {
            if (ponto.getId() > maxId) {
                maxId = ponto.getId();
            }
        }
        return maxId + 1;
    }

    public void adicionarPonto(RegistroPonto novoPonto){
        if(novoPonto.getId()==0){
            novoPonto.setId(proximoId());
            listaDeRegistroDePonto.add(novoPonto);
        }else{
            return;
        }
        salvarNoJson();
    }

    public List<RegistroPonto> buscarPorFuncionario(int funcionarioId){

        List<RegistroPonto> pontosEncontrados = new ArrayList<>();

        for(RegistroPonto pontoBuscado : listaDeRegistroDePonto){
            if(pontoBuscado.getId()!= 0 && pontoBuscado.getFuncionarioId()==funcionarioId){

                pontosEncontrados.add(pontoBuscado);

                return pontosEncontrados;

            }
        }
        return null;
    }

}
