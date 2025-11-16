package br.barbearia.OrdensDeServiço;



/**
 * Representa uma Ordem de Serviço (OS) no sistema.
 * <p>
 * Esta classe (POJO) armazena os dados de um serviço que foi
 * prestado, vinculando o serviço, o cliente, o funcionário,
 * o valor e um diagnóstico.
 * </p>
 * <p>
 * Esta classe também implementa o Requisito 15 (contador de instâncias)
 * através do campo {@code contadorInstanciaOS}.
 * </p>
 *
 * @see OrdensDeServicoRepository
 * @see OrdensDeServicoRoles
 */
public class OrdensDeServicoModel {

    /** A chave estrangeira (ID) do {@link br.barbearia.agendamento.model.Servicos} prestado. */
    private int servicoId;

    /** O CPF do {@link br.barbearia.model.Usuarios} (Cliente) que recebeu o serviço. */
    private String clienteCpf;

    /** O CPF do {@link br.barbearia.model.Usuarios} (Funcionario) que realizou o serviço. */
    private String funcionarioCpf;

    /** O valor monetário cobrado pelo serviço. */
    private double valorGasto;

    /** Notas ou diagnóstico do profissional sobre o serviço realizado. */
    private String diagnosticoServico;

    /** O identificador único desta Ordem de Serviço. */
    private int ordemId;

    /** A data em que o serviço foi realizado (armazenada como String, ex: "dd/MM/yyyy"). */
    private String dataDoServico;

    /**
     * (Req 15) Contador estático para rastrear o número de instâncias
     * de {@code OrdensDeServicoModel} criadas.
     */
    private static int contadorInstanciaOS = 0;

    /**
     * Construtor completo para criar uma nova Ordem de Serviço.
     * <p>
     * Este construtor inicializa todos os campos e incrementa o
     * contador estático de instâncias ({@code contadorInstanciaOS}).
     * </p>
     *
     * @param servicoId O ID do serviço.
     * @param clienteCpf O CPF do cliente.
     * @param funcionarioCpf O CPF do funcionário.
     * @param valorGasto O valor cobrado.
     * @param diagnosticoServico As notas do profissional.
     * @param ordemId O ID desta OS (geralmente 0 para novas).
     * @param dataDoServico A data em que o serviço foi prestado.
     */
    public OrdensDeServicoModel(int servicoId, String clienteCpf, String funcionarioCpf, double valorGasto, String diagnosticoServico, int ordemId, String dataDoServico){
        this.servicoId = servicoId;
        this.clienteCpf = clienteCpf;
        this.funcionarioCpf = funcionarioCpf;
        this.valorGasto = valorGasto;
        this.diagnosticoServico = diagnosticoServico;
        this.ordemId = ordemId;
        this.dataDoServico = dataDoServico;

        contadorInstanciaOS++;
    }


    /**
     * (Req 15) Retorna o número total de instâncias desta classe
     * que foram criadas usando o construtor completo.
     *
     * @return O valor do contador estático {@code contadorInstanciaOS}.
     */
    public static int pegarContadorInstancias(){
        return contadorInstanciaOS;
    }


    /**
     * Retorna uma representação textual completa da Ordem de Serviço.
     *
     * @return Uma String formatada com todos os dados da OS.
     */
    @Override
    public String toString() {
        return "OrdensDeServicoModel{" +
                "servicoId=" + servicoId +
                ", clienteCpf='" + clienteCpf + '\'' +
                ", funcionarioCpf='" + funcionarioCpf + '\'' +
                ", valorGasto=" + valorGasto +
                ", diagnosticoServico='" + diagnosticoServico + '\'' +
                ", ordemId=" + ordemId +
                ", dataDoServico=" + dataDoServico +
                '}';
    }

    /**
     * Retorna o ID do serviço prestado.
     * @return O ID do serviço.
     */
    public int getServicoId() {
        return servicoId;
    }

    /**
     * Retorna a data (como String) em que o serviço foi prestado.
     * @return A data do serviço.
     */
    public String getDataDoServico() {
        return dataDoServico;
    }

    /**
     * Define a data (como String) em que o serviço foi prestado.
     * @param dataDoServico A nova data (ex: "dd/MM/yyyy").
     */
    public void setDataDoServico(String dataDoServico) {
        this.dataDoServico = dataDoServico;
    }

    /**
     * Retorna o ID desta Ordem de Serviço.
     * @return O ID da OS.
     */
    public int getOrdemId() {
        return ordemId;
    }

    /**
     * Define o ID desta Ordem de Serviço.
     * @param ordemId O novo ID da OS.
     */
    public void setOrdemId(int ordemId) {
        this.ordemId = ordemId;
    }

    /**
     * Define o ID do serviço prestado.
     * @param servicoId O novo ID do serviço.
     */
    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    /**
     * Retorna o CPF do cliente.
     * @return O CPF do cliente.
     */
    public String getClienteCpf() {
        return clienteCpf;
    }

    /**
     * Define o CPF do cliente.
     * @param clienteCpf O novo CPF do cliente.
     */
    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    /**
     * Retorna o CPF do funcionário.
     * @return O CPF do funcionário.
     */
    public String getFuncionarioCpf() {
        return funcionarioCpf;
    }

    /**
     * Define o CPF do funcionário.
     * @param funcionarioCpf O novo CPF do funcionário.
     */
    public void setFuncionarioCpf(String funcionarioCpf) {
        this.funcionarioCpf = funcionarioCpf;
    }

    /**
     * Retorna o valor gasto (cobrado) no serviço.
     * @return O valor gasto.
     */
    public double getValorGasto() {
        return valorGasto;
    }

    /**
     * Define o valor gasto (cobrado) no serviço.
     * @param valorGasto O novo valor.
     */
    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    /**
     * Retorna o diagnóstico ou notas do serviço.
     * @return O texto do diagnóstico.
     */
    public String getDiagnosticoServico() {
        return diagnosticoServico;
    }

    /**
     * Define o diagnóstico ou notas do serviço.
     * @param diagnosticoServico O novo texto do diagnóstico.
     */
    public void setDiagnosticoServico(String diagnosticoServico) {
        this.diagnosticoServico = diagnosticoServico;
    }

    /**
     * Construtor padrão (vazio).
     * <p>
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     * <b>Nota:</b> Este construtor *não* incrementa o contador de instâncias
     * {@code contadorInstanciaOS}.
     * </p>
     */
    public OrdensDeServicoModel(){
    }
}