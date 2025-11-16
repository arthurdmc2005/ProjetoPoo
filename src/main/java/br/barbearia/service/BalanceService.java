package br.barbearia.service;

import br.barbearia.OrdensDeServiço.OrdensDeServicoModel;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Classe de serviço responsável por calcular balanços financeiros
 * com base nos registros do sistema, como Ordens de Serviço.
 */
public class BalanceService {

    private OrdensDeServicoRepository osRepository;

    /**
     * Define o formato da data que esperamos encontrar no Model.
     * Baseado na sua classe Barbearia.java, o formato é "dd/MM/yyyy".
     */
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor da classe de serviço.
     * Recebe o repositório de Ordens de Serviço por injeção de dependência.
     *
     * @param osRepository A instância do repositório que contém as OS.
     */
    public BalanceService(OrdensDeServicoRepository osRepository) {
        this.osRepository = osRepository;
    }

    /**
     * Calcula o faturamento total bruto (soma de 'valorGasto') de todas
     * as Ordens de Serviço registradas em um mês e ano específicos.
     *
     * @param mes O mês desejado (1 para Janeiro, 12 para Dezembro).
     * @param ano O ano desejado (ex: 2025).
     * @return O valor total (double) somado.
     */
    public double gerarBalancoMensal(int mes, int ano) {
        // 1. Busca todas as ordens usando o método que criamos no Passo 1.
        List<OrdensDeServicoModel> todasAsOrdens = osRepository.listarTodasOrdens();

        if (todasAsOrdens == null || todasAsOrdens.isEmpty()) {
            System.out.println("LOG [Balance]: Nenhuma ordem de serviço encontrada no repositório.");
            return 0.0;
        }

        double totalMensal = 0.0;

        // 2. Itera sobre cada ordem de serviço
        for (OrdensDeServicoModel ordem : todasAsOrdens) {
            String dataDaOrdemStr = ordem.getDataDoServico();

            // Ignora ordens sem data
            if (dataDaOrdemStr == null || dataDaOrdemStr.trim().isEmpty()) {
                continue;
            }

            try {
                // 3. Converte a data (String) para um objeto LocalDate
                LocalDate dataDaOrdem = LocalDate.parse(dataDaOrdemStr, FORMATADOR_DATA);

                // 4. Verifica se a data pertence ao mês e ano solicitados
                if (dataDaOrdem.getMonthValue() == mes && dataDaOrdem.getYear() == ano) {
                    // 5. Soma o valorGasto ao total
                    totalMensal += ordem.getValorGasto();
                }

            } catch (DateTimeParseException e) {
                // Captura erro se a data no JSON estiver em formato inválido
                System.err.println("AVISO [Balance]: Ignorando OS ID " + ordem.getOrdemId() +
                        ". Formato de data inválido: '" + dataDaOrdemStr + "'");
            }
        }

        // 6. Retorna o total calculado
        return totalMensal;
    }
}