package br.barbearia.OrdensDeServiço;

public class OrdensDeServicoModel {

    private int servicoId;
    private String clienteCpf;
    private String funcionarioCpf;
    private String produtoUtilizado;
    private double valorGasto;
    private String diagnosticoServico;
    private int ordemId;

    public OrdensDeServicoModel(int servicoId, String clienteCpf, String funcionarioCpf, String produtoUtilizado, double valorGasto, String diagnosticoServico, int ordemId) {
        this.servicoId = servicoId;
        this.clienteCpf = clienteCpf;
        this.funcionarioCpf = funcionarioCpf;
        this.produtoUtilizado = produtoUtilizado;
        this.valorGasto = valorGasto;
        this.diagnosticoServico = diagnosticoServico;
        this.ordemId = ordemId;
    }

    @Override
    public String toString() {
        return "OrdensDeServicoModel{" +
                "servicoId=" + servicoId +
                ", clienteCpf='" + clienteCpf + '\'' +
                ", funcionarioCpf='" + funcionarioCpf + '\'' +
                ", produtoUtilizado='" + produtoUtilizado + '\'' +
                ", valorGasto=" + valorGasto +
                ", diagnosticoServico='" + diagnosticoServico + '\'' +
                ", ordemId=" + ordemId +
                '}';
    }

    public int getServicoId() {
        return servicoId;
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

    public String getProdutoUtilizado() {
        return produtoUtilizado;
    }

    public void setProdutoUtilizado(String produtoUtilizado) {
        this.produtoUtilizado = produtoUtilizado;
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
