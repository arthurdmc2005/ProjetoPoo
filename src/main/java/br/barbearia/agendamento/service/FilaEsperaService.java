package br.barbearia.agendamento.service;

import br.barbearia.agendamento.model.FilaEspera;
import br.barbearia.agendamento.repository.FilaEsperaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FilaEsperaService {

    private FilaEsperaRepository filaEsperaRepository;

    public FilaEsperaService(FilaEsperaRepository filaEsperaRepository){
        this.filaEsperaRepository = filaEsperaRepository;
    }

    public void adicionarCliente(FilaEspera espera){
        if(espera == null){
            return;
        }
        filaEsperaRepository.adicionarNaFila(espera);

    }

    public void removerCliente(int idParaRemover){
        filaEsperaRepository.removerDaFila(idParaRemover);
    }

    public FilaEspera chamarProximoDaFila(LocalDate data, LocalTime hora){
        FilaEspera proximo = filaEsperaRepository.buscarProximoDaFila(data,hora);
        if(proximo != null){
            this.removerCliente(proximo.getId());
        }
        return proximo;
    }

}
