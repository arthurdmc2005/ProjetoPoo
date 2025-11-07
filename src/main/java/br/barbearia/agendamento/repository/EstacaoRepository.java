package br.barbearia.agendamento.repository;

import br.barbearia.agendamento.model.Estacao;
import java.util.Arrays;
import java.util.List;

public class EstacaoRepository {

    // Vetor estático com 3 estações fixas
    private Estacao[] estacoes = {
            new Estacao(1, true),   // Estação 1 com lavagem e secador
            new Estacao(2, false),  // Estação 2 comum
            new Estacao(3, false)   // Estação 3 comum
    };

    /**
     * Retorna todas as estações.
     */
    public List<Estacao> listarTodas() {
        return Arrays.asList(estacoes);
    }

    /**
     * Busca uma estação pelo número (ID).
     */
    public Estacao buscarPorNumero(int numeroEstacao) {
        for (Estacao estacao : estacoes) {
            if (estacao.getId() == numeroEstacao) {
                return estacao;
            }
        }
        return null;
    }

    /**
     * Ocupa uma estação, se estiver livre.
     */
    public boolean ocuparEstacao(int numeroEstacao) {
        Estacao estacao = buscarPorNumero(numeroEstacao);
        if (estacao == null || estacao.isOcupada()) {
            return false;
        }
        estacao.ocupar();
        System.out.println("LOG [Repository]: Estação " + numeroEstacao + " ocupada.");
        return true;
    }

    /**
     * Libera uma estação ocupada.
     */
    public boolean liberarEstacao(int numeroEstacao) {
        Estacao estacao = buscarPorNumero(numeroEstacao);
        if (estacao == null || !estacao.isOcupada()) {
            return false;
        }
        estacao.liberar();
        System.out.println("LOG [Repository]: Estação " + numeroEstacao + " liberada.");
        return true;
    }
}
