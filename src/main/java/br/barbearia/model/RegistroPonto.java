package br.barbearia.model;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

public class RegistroPonto {

    private int id;
    private int funcionarioId;
    private LocalDateTime dataHora;
    private String tipo;

    public RegistroPonto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Override
    public String toString() {
        return "RegistroPonto{" +
                "id=" + id +
                ", funcionarioId=" + funcionarioId +
                ", dataHora=" + dataHora +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public RegistroPonto(int id, int funcionarioId, LocalDateTime dataHora, String tipo){
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.dataHora = dataHora;
        this.tipo = tipo;
    }


}
