package br.barbearia.agendamento.repository;


import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicosRepository {

    private GerenciadorJSON<Servicos> gerenciadorJSON;

    private List<Servicos> listaDeServicos;

    public ServicosRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Servicos>>() {
        });

        this.listaDeServicos = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeServicos));
    }


    /**
     * Função PRIVADA para descobrir qual o próximo ID disponível.
     */
    private int proximoId() {
        int maxId = 0;
        for (Servicos servico : listaDeServicos) {
            if (servico.getId() > maxId) {
                maxId = servico.getId();
            }
        }
        return maxId + 1;
    }

    public Servicos salvarServico(Servicos novoServico){
        if(novoServico.getId()==0){
            novoServico.setId(proximoId());
            listaDeServicos.add(novoServico);
            salvarNoJson();
            System.out.println("LOG: Serviço" + novoServico.getServico() + "adicionado");

        }
        return novoServico;
    }

    public void removerServico(int idParaRemover){
        boolean foiRemovido = listaDeServicos.removeIf(servicos -> servicos.getId()==idParaRemover);
        if(foiRemovido){
            salvarNoJson();
        }
    }

    public void atualizarServicos(Servicos servicoParaAtualizar){

        for(int i = 0; i < listaDeServicos.size(); i++){
            Servicos servicoAntigo = listaDeServicos.get(i);
            if(servicoAntigo.getId()==servicoParaAtualizar.getId()){
                listaDeServicos.set(i, servicoParaAtualizar);
                salvarNoJson();
                return; // Parar o loop depois de achar

            }
        }

    }

    public Servicos buscarPorId(int idBuscado){
        for(Servicos servicos : listaDeServicos){
            if(servicos.getId() == idBuscado){
                return servicos;
            }
        }
        return null;

    }

    public Servicos buscarPorTipoDeServico(String nomeDoServicoBuscado){
        for(Servicos servico : listaDeServicos){
            if(servico.getServico().equalsIgnoreCase(nomeDoServicoBuscado)){
                return servico;
            }
        }
        return null;
    }



}
