package br.barbearia.model;



/**
 * Classe principal abstrata que armazena as informações ( nomes );
 * <p>
 * Serve como classe base para {@link Cliente} e {@link Funcionario},
 * fornecendo atributos e métodos comuns a todas as pessoas no sistema.
 * </p>
 * @author Arthur
 */
public abstract class Pessoa {

    /** O nome da pessoa. */
    protected String nome;

    /** O CPF (Cadastro de Pessoas Físicas) da pessoa. */
    protected String cpf;

    /** O número de telefone da pessoa. */
    protected String telefone;

    /** O endereço residencial da pessoa. */
    protected String endereco;

    /**
     * Construtor que passa as informações de pessoa (nome, cpf, telefone).
     *
     * @param nome armazena o nome;
     * @param cpf armazena o cpf;
     * @param telefone armazena o telefone;
     */
    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    /**
     * Retorna uma representação textual da Pessoa, incluindo nome, cpf, telefone e endereço.
     *
     * @return Uma String formatada com os dados da pessoa.
     */
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    /**
     * Construtor vazio.
     * <p>Necessário para a desserialização de JSON por frameworks como o Jackson,
     * que precisam de um construtor "no-arg" (sem argumentos).
     * </p>
     */
    public Pessoa() {}

    /**
     * Construtor parcial que armazena nome e cpf.
     * * @param nome O nome da pessoa.
     * @param cpf O CPF da pessoa.
     */
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }


    /**
     * Retorna o nome da pessoa.
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o CPF da pessoa.
     * @return O CPF.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna o telefone da pessoa.
     * @return O telefone.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Retorna o endereço da pessoa.
     * @return O endereço.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o nome da pessoa.
     * <p>
     * Remove caracteres que não sejam letras (incluindo acentuadas) ou espaços
     * da string de entrada.
     * </p>
     * @param nome O novo nome.
     */
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome.replaceAll("[^\\p{L}\\s]", "");
        } else {
            this.nome = null;
        }
    }

    /**
     * Define o CPF da pessoa.
     * <p>
     * Remove todos os caracteres não numéricos (ex: pontos e hifens)
     * da string de entrada.
     * </p>
     * @param cpf O novo CPF (pode estar formatado).
     */
    public void setCpf(String cpf) {
        if (cpf != null) {
            this.cpf = cpf.replaceAll("[^0-9]", "");
        } else {
            this.cpf = null;
        }
    }

    /**
     * Define o telefone da pessoa.
     * <p>
     * Remove todos os caracteres não numéricos (ex: parênteses, hifens, espaços)
     * da string de entrada.
     * </p>
     * @param telefone O novo telefone (pode estar formatado).
     */
    public void setTelefone(String telefone) {
        if (telefone != null) {
            this.telefone = telefone.replaceAll("[^0-9]", "");
        } else {
            this.telefone = null;
        }
    }

    /**
     * Define o endereço da pessoa.
     * <p>
     * Remove caracteres especiais da string de entrada, permitindo apenas
     * letras (incluindo acentuadas), números, espaços, vírgulas, pontos e hifens.
     * </p>
     * @param endereco O novo endereço.
     */
    public void setEndereco(String endereco) {
        if (endereco != null) {
            this.endereco = endereco.replaceAll("[^\\p{L}0-9\\s,.-]", "");
        } else {
            this.endereco = null;
        }
    }

    /**
     * Retorna uma versão anonimizada do CPF (ex: "123..-01").
     * <p>
     * Se o CPF for nulo ou não tiver 11 dígitos, imprime um erro no console
     * e pode lançar uma {@link StringIndexOutOfBoundsException} (devido ao substring).
     * </p>
     * @return O CPF anonimizado.
     */
    public String getCpfAnonimizado(){
        if(cpf == null || cpf.length() != 11)
            System.out.println("Formato de cpf incorreto");
        return cpf.substring(0, 3) + "..-" + cpf.substring(9, 11);
    }

    /**
     * Tenta retornar uma versão anonimizada do telefone.
     * <p>
     * Se o telefone for nulo ou não tiver 11 dígitos, imprime um erro
     * no console e retorna a string "Não encontrado".
     * A lógica de anonimização em si não está implementada.
     * </p>
     * @return A string "Não encontrado" se o formato for inválido.
     */
    public String getTelefoneAnonimizado(){
        if(telefone == null || telefone.length() != 11)
            System.out.println("Formato incorreto(31999999999)");
        return "Não encontrado";
    }
}