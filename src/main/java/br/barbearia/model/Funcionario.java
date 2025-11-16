package br.barbearia.model;

/**
 * Representa a entidade Funcionário, que é um tipo especializado de {@link Pessoa}.
 * <p>
 * Esta classe herda os atributos básicos de {@link Pessoa} (nome, cpf, telefone, endereco)
 * e adiciona campos específicos do funcionário, como ID interno, cargo e salário.
 * </p>
 * <p>
 * (Implementa o Requisito 04, usando {@code super} nos construtores).
 * </p>
 *
 * @see Pessoa
 * @see br.barbearia.repository.UsuarioRepository (onde Funcionários são gerenciados como Usuários)
 * @see br.barbearia.service.UsuarioServices
 */
public class Funcionario extends Pessoa {

    /** O identificador único interno do funcionário. */
    private int id;

    /** A descrição do cargo do funcionário (ex: "Barbeiro", "Gerente"). */
    private String cargo;

    /** O salário base do funcionário. */
    private double salario;

    /**
     * Retorna uma representação textual do Funcionário.
     * Inclui ID, cargo, salário e o nome herdado.
     *
     * @return Uma String formatada com os dados do funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", nome='" + nome + '\'' +
                '}';
    }

    /**
     * (Questão 04) Construtor completo da classe Funcionario.
     * Utiliza o construtor da superclasse {@link Pessoa} para nome e cpf.
     *
     * @param nome O nome do funcionário.
     * @param cpf O CPF do funcionário.
     * @param id O ID interno do funcionário.
     * @param cargo O cargo do funcionário.
     * @param salario O salário do funcionário.
     * @param telefone O telefone do funcionário (não armazenado nesta
     * superchamada específica, mas poderia ser, ex: super(nome, cpf, telefone)).
     */
    public Funcionario(String nome, String cpf, int id, String cargo, double salario, String telefone) {
        super(nome, cpf);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
    }

    /**
     * (Questão 04) Construtor parcial da classe Funcionario.
     * Utiliza o construtor da superclasse {@link Pessoa} para nome e cpf.
     *
     * @param nome O nome do funcionário.
     * @param cpf O CPF do funcionário.
     * @param cargo O cargo do funcionário.
     * @param salario O salário do funcionário.
     */
    public Funcionario(String nome, String cpf, String cargo, double salario){
        super(nome,cpf);
        this.cargo = cargo;
        this.salario = salario;
    }

    /**
     * Construtor padrão (vazio).
     * Necessário para a desserialização de JSON por frameworks como o Jackson.
     */
    public Funcionario(){

    }

    /**
     * Retorna o cargo do funcionário.
     * @return O cargo.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do funcionário.
     * @param cargo O novo cargo.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Retorna o salário do funcionário.
     * @return O salário.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Define o salário do funcionário.
     * @param salario O novo salário.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }
}