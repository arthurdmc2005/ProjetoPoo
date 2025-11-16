package br.barbearia.model;

/**
 * Representa a entidade Cliente, que é um tipo especializado de {@link Pessoa}.
 * <p>
 * Esta classe herda os atributos básicos de {@link Pessoa} (nome, cpf, telefone, endereco)
 * e adiciona campos específicos do cliente, como informações de cartão
 * e status (ex: "Pendente").
 * </p>
 *
 * @see Pessoa
 * @see br.barbearia.repository.UsuarioRepository (onde Clientes são gerenciados como Usuários)
 * @see br.barbearia.service.UsuarioServices
 */
public class Cliente extends Pessoa {

    /** Armazena informações do cartão (possivelmente para pagamento). */
    private String cartao;

    /** Define o status atual do cliente no sistema (ex: "Pendente", "Ativo"). */
    private String status;

    /**
     * Construtor da classe Cliente.
     * <p>
     * Utiliza o construtor da superclasse {@link Pessoa} para nome, cpf e telefone.
     * Define o status padrão do cliente como "Pendente" na criação.
     * </p>
     *
     * @param nome O nome do cliente.
     * @param cpf O CPF do cliente.
     * @param telefone O telefone do cliente.
     * @param cartao As informações de cartão do cliente.
     */
    public Cliente(String nome, String cpf, String telefone, String cartao) {
        super(nome, cpf, telefone);
        this.cartao = cartao;
        this.status = "Pendente";
    }

    /**
     * Retorna uma representação textual do Cliente.
     * Inclui os campos herdados de {@link Pessoa} e o status.
     *
     * @return Uma String formatada com os dados do cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // --- Getters e Setters ---

    /**
     * Retorna as informações do cartão do cliente.
     * @return As informações do cartão.
     */
    public String getCartao() {
        return cartao;
    }

    /**
     * Retorna o status atual do cliente.
     * @return O status (ex: "Pendente").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define as informações do cartão do cliente.
     * @param cartao As novas informações de cartão.
     */
    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    /**
     * Define o status do cliente.
     * @param status O novo status (ex: "Ativo").
     */
    public void setStatus(String status) {
        this.status = status;
    }
}