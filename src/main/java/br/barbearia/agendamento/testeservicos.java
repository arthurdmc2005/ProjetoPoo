package br.barbearia.agendamento;

import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.repository.GerenciadorDeAgendamento;// Importe o gerenciador

public class testeservicos {

    public static void main(String[] args) {

        System.out.println("--- Iniciando teste ---");

        // --- PASSO 1: Criar o objeto ---
        // (Usando o construtor "conveniente" que fizemos no Modelo)
        Agendamento servico1 = new Agendamento("Corte", 55.00, "Arthur");
        Agendamento servico2 = new Agendamento("Barba", 30.00, "Joaozinho");

        // --- PASSO 2: Chamar a função que salva ---

        // "Contrata" o Gerenciador, e diz a ele o NOME do arquivo
        // (Ele vai aparecer na raiz do seu 'BarbeariaComMaven')
        GerenciadorDeAgendamento gerenciador = new GerenciadorDeAgendamento("BarbeariaComMaven/Servicos.JSON");

        // Chama o método para cada objeto
        gerenciador.adicionarServico(servico1);
        gerenciador.adicionarServico(servico2);

        System.out.println("--- Teste finalizado ---");
        // Agora, recarregue seu projeto (Reload from Disk)
        // e abra o "Servicos.JSON"
    }
}