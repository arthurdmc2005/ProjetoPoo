package br.barbearia.agendamento.model;

/**
 * Representa um Serviço oferecido pela barbearia.
 * <p>
 * Esta classe (POJO) armazena os dados de um serviço, como nome, descrição,
 * preço e ID.
 * </p>
 * <p>
 * Esta classe também implementa os Requisitos 11, 12 e 13, referentes
 * à contagem de instâncias estáticas usando diferentes modificadores de acesso
 * ({@code private} e {@code protected}).
 * </p>
 *
 * @see br.barbearia.agendamento.repository.ServicosRepository
 * @see br.barbearia.agendamento.service.ServicesRoles
 */
public class Servicos {

    /** O nome do serviço (ex: "Corte", "Barba"). */
    private String servico;

    /** Uma breve descrição do que o serviço inclui. */
    private String descricao;

    /** O valor base do serviço. */
    private double precoBase;

    /** O identificador único do serviço. */
    private int id;

    // --- Início: Requisitos 11, 12, 13 (Contadores Estáticos) ---

    /**
     * (Req 12) Contador estático privado.
     * Armazena o número total de instâncias de {@code Servicos} criadas.
     * Sendo {@code private}, só pode ser acessado por métodos desta classe.
     */
    private static int contadorInstanciasPrivado = 0;

    /**
     * (Req 13) Contador estático protegido.
     * Armazena o número total de instâncias de {@code Servicos} criadas.
     * Sendo {@code protected}, pode ser acessado por classes no mesmo pacote
     * ou por subclasses.
     */
    protected static int contadorInstanciasProtegido = 0;

    /**
     * (Req 12) Retorna o valor do contador privado de instâncias.
     *
     * @return O número total de instâncias criadas.
     */
    public static int pegarContadorInstanciasPrivado(){
        return contadorInstanciasPrivado;
    }

    /**
     * (Req 12) Define o valor do contador privado de instâncias.
     *
     * @param valor O novo valor para o contador.
     */
    public static void setarContadorInstanciasPrivado(int valor){
        contadorInstanciasPrivado = valor;
    }

    /**
     * (Req 13) Retorna o valor do contador protegido de instâncias.
     *
     * @return O número total de instâncias criadas.
     */
    public static int pegarContadorInstanciasProtegido(){
        return contadorInstanciasProtegido;
    }

    // --- Fim: Requisitos 11, 12, 13 ---


    /**
     * Retorna o ID do serviço.
     * @return O ID do serviço.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do serviço.
     * @param id O novo ID do serviço.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o valor (preço base) do serviço.
     * @return O preço base.
     */
    public double getValor() {
        return precoBase;
    }

    /**
     * Define o valor (preço base) do serviço.
     * @param precoBase O novo preço base.
     */
    public void setValor(double precoBase) {
        this.precoBase = precoBase;
    }

    /**
     * Construtor completo da classe Servicos.
     * Incrementa os contadores estáticos de instâncias (Req 11).
     *
     * @param servico O nome do serviço.
     * @param descricao A descrição do serviço.
     * @param precoBase O preço base do serviço.
     * @param id O ID do serviço.
     */
    public Servicos(String servico, String descricao, double precoBase, int id) {
        this.servico = servico;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.id = id;
        contadorInstanciasPrivado++;
        contadorInstanciasProtegido++;
    }

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON (Jackson).
     * Incrementa os contadores estáticos de instâncias (Req 11).
     */
    public Servicos(){
        contadorInstanciasProtegido++;
        contadorInstanciasPrivado++;
    }

    /**
     * Retorna o nome do serviço.
     * @return O nome do serviço.
     */
    public String getServico() {
        return servico;
    }

    /**
     * Define o nome do serviço.
     * @param servico O novo nome do serviço.
     */
    public void setServico(String servico) {
        this.servico = servico;
    }

    /**
     * Retorna uma representação textual do serviço (nome, descrição, preço).
     * @return Uma String formatada com os dados do serviço.
     */
    @Override
    public String toString() {
        return "Servicos{" +
                "servico='" + servico + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoBase=" + precoBase +
                '}';
    }

    /**
     * Retorna a descrição do serviço.
     * @return A descrição do serviço.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do serviço.
     * @param descricao A nova descrição do serviço.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}