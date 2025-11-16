package br.barbearia.model;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

/**
 * Representa um único registro de ponto (batida de ponto) de um funcionário.
 * <p>
 * Esta classe (POJO) armazena a identidade do funcionário, o carimbo de
 * data/hora exato e o tipo de registro (ex: "ENTRADA", "SAIDA").
 * </p>
 *
 * @see br.barbearia.repository.GerenciarPontoRepository
 * @see br.barbearia.service.GerenciarPontoService
 */
public class RegistroPonto {

    /** O identificador único deste registro de ponto. */
    private int id;

    /** A chave estrangeira (ID) do funcionário que bateu o ponto. */
    private int funcionarioId;

    /** O carimbo exato (data e hora) em que o ponto foi registrado. */
    private LocalDateTime dataHora;

    /** O tipo de registro (ex: "ENTRADA", "SAIDA", "INICIO_ALMOCO"). */
    private String tipo;

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public RegistroPonto(){

    }

    /**
     * Retorna o ID deste registro de ponto.
     * @return O ID do registro.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID deste registro de ponto.
     * @param id O novo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o ID do funcionário associado a este registro.
     * @return O ID do funcionário.
     */
    public int getFuncionarioId() {
        return funcionarioId;
    }

    /**
     * Define o ID do funcionário associado a este registro.
     * @param funcionarioId O novo ID do funcionário.
     */
    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    /**
     * Retorna uma representação textual completa do registro de ponto.
     *
     * @return Uma String formatada com todos os dados do registro.
     */
    @Override
    public String toString() {
        return "RegistroPonto{" +
                "id=" + id +
                ", funcionarioId=" + funcionarioId +
                ", dataHora=" + dataHora +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    /**
     * Retorna o carimbo exato (data e hora) em que o ponto foi registrado.
     * @return O {@link LocalDateTime} do registro.
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Define o carimbo exato (data e hora) do registro.
     * @param dataHora O novo {@link LocalDateTime} do registro.
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Retorna o tipo deste registro de ponto.
     * @return O tipo (ex: "ENTRADA").
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo deste registro de ponto.
     * @param tipo O novo tipo (ex: "SAIDA").
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Construtor completo para criar uma instância de RegistroPonto.
     *
     * @param id O ID único do registro.
     * @param funcionarioId O ID do funcionário.
     * @param dataHora O carimbo de data e hora do registro.
     * @param tipo O tipo de registro (ex: "ENTRADA").
     */
    public RegistroPonto(int id, int funcionarioId, LocalDateTime dataHora, String tipo){
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.dataHora = dataHora;
        this.tipo = tipo;
    }
}