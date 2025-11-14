package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateFilaDeEspera;
import br.barbearia.agendamento.model.FilaEspera;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilaEsperaRepository {

    private GerenciadorJSON<FilaEspera> gerenciadorJSON;

    private List<FilaEspera> listaDeEspera;

    public FilaEsperaRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<FilaEspera>>() {
        });

        this.listaDeEspera    = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeEspera));
    }

    /**
     *  Encontra o próximo ID livre.
     */
    private int proximoId() {
        int maxId = 0;


        for (FilaEspera filaEspera : listaDeEspera) {
            if (filaEspera.getId() > maxId) {
                maxId = filaEspera.getId();
            }
        }
        return maxId + 1;
    }

    public void adicionarNaFila(FilaEspera espera){
        if(espera.getId()==0){
            espera.setId(proximoId());
            listaDeEspera.add(espera);
            System.out.printf("Cliente adicionado a fila");
        }
    }

    public void removerDaFila(int idParaRemover){
        boolean foiRemovido = listaDeEspera.removeIf(espera -> espera.getId()==idParaRemover);{
            if(foiRemovido){
                System.out.printf("removido");
            }
        }

    }
//Fila FIFO ( First - In / First - Out )
    public FilaEspera buscarProximoDaFila(LocalDate data, LocalTime hora){
        List<FilaEspera> esperandoHorario = new ArrayList<>();

        for(FilaEspera espera : listaDeEspera){
            if(espera.getHoraDesejada() != null && espera.getHoraDesejada() != null){

                if(espera.getDataDesejada().equals(data) &&
                espera.getHoraDesejada().equals(hora));

                esperandoHorario.add(espera);
            }
            if(esperandoHorario.isEmpty()){
                return null;
            }
            Comparator<FilaEspera> decider = new ComparateFilaDeEspera();

            esperandoHorario.sort(decider);


        }
        return  esperandoHorario.get(0);
        }
    }
