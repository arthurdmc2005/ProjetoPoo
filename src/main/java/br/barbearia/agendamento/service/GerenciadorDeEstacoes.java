package br.barbearia.agendamento.service;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.Estacao;

public class GerenciadorDeEstacoes {

        // Vetor estático com as 3 estações fixas
        private static Estacao[] estacoes = new Estacao[3];

        static {
            // Inicializa as 3 estações
            estacoes[0] = new Estacao(1, true);   // possui lavagem e secador
            estacoes[1] = new Estacao(2, false);  // comum
            estacoes[2] = new Estacao(3, false);  // comum
        }

        public static Estacao[] listarEstacoes() {
            return estacoes;
        }

        public static Estacao buscarEstacaoLivre() {
            for (Estacao estacao : estacoes) {
                if (!estacao.isOcupada()) {
                    return estacao;
                }
            }
            return null; // nenhuma disponível
        }

        public static void liberarEstacao(int id) {
            for (Estacao estacao : estacoes) {
                if (estacao.getId() == id) {
                    estacao.liberar();
                    return;
                }
            }
        }



}

