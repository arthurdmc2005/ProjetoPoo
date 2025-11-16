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
        List<OrdensDeServicoModel> todasAsOrdens = osRepository.listarTodasOrdens();

        if (todasAsOrdens == null || todasAsOrdens.isEmpty()) {
            System.out.println("LOG [Balance]: Nenhuma ordem de serviço encontrada no repositório.");
            return 0.0;
        }

        double totalMensal = 0.0;

        for (OrdensDeServicoModel ordem : todasAsOrdens) {
            String dataDaOrdemStr = ordem.getDataDoServico();

            if (dataDaOrdemStr == null || dataDaOrdemStr.trim().isEmpty()) {
                continue;
            }

            try {
                LocalDate dataDaOrdem = LocalDate.parse(dataDaOrdemStr, FORMATADOR_DATA);

                if (dataDaOrdem.getMonthValue() == mes && dataDaOrdem.getYear() == ano) {
                    totalMensal += ordem.getValorGasto();
                }

            } catch (DateTimeParseException e) {
                System.err.println("AVISO [Balance]: Ignorando OS ID " + ordem.getOrdemId() +
                        ". Formato de data inválido: '" + dataDaOrdemStr + "'");
            }
        }

        return totalMensal;
    }
}