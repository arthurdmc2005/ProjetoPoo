package br.barbearia.OrdensDeServiço;



public class OrdensDeServicoModel {

    private int servicoId;
    private String clienteCpf;
    private String funcionarioCpf;
    private double valorGasto;
    private String diagnosticoServico;
    private int ordemId;
    private String dataDoServico;

    private static int contadorInstanciaOS = 0;

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

    public static int pegarContadorInstancias(){
        return contadorInstanciaOS;
    }


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

    public int getServicoId() {
        return servicoId;
    }

    public String getDataDoServico() {
        return dataDoServico;
    }

    public void setDataDoServico(String dataDoServico) {
        this.dataDoServico = dataDoServico;
    }

    public int getOrdemId() {
        return ordemId;
    }

    public void setOrdemId(int ordemId) {
        this.ordemId = ordemId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getFuncionarioCpf() {
        return funcionarioCpf;
    }

    public void setFuncionarioCpf(String funcionarioCpf) {
        this.funcionarioCpf = funcionarioCpf;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public String getDiagnosticoServico() {
        return diagnosticoServico;
    }

    public void setDiagnosticoServico(String diagnosticoServico) {
        this.diagnosticoServico = diagnosticoServico;
    }

    public OrdensDeServicoModel(){
    }
}
