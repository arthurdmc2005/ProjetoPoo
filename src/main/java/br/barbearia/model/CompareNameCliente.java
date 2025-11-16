package br.barbearia.model;

import br.barbearia.repository.UsuarioRepository;

import java.util.Comparator;

/**
 * Implementação de {@link Comparator} para a classe {@link Usuarios}.
 * <p>
 * Esta classe compara dois objetos {@link Usuarios} com base em seus nomes
 * ({@link Usuarios#getNome()}). A comparação é lexicográfica (alfabética)
 * e ignora maiúsculas/minúsculas (case-insensitive).
 * </p>
 * <p>
 * Nomes nulos são tratados como "maiores" do que nomes não nulos e
 * serão movidos para o final de uma lista ordenada.
 * Se um nome for prefixo de outro (ex: "Art" e "Arthur"), o nome
 * mais curto ("Art") virá primeiro.
 * </p>
 *
 * @see Usuarios
 * @see java.util.Comparator
 */
public class CompareNameCliente implements Comparator<Usuarios> {

    /**
     * Compara dois objetos {@link Usuarios} com base em seus nomes,
     * de forma case-insensitive.
     *
     * @param usuarios1 o primeiro {@link Usuarios} a ser comparado.
     * @param usuarios2 o segundo {@link Usuarios} a ser comparado.
     * @return um valor negativo se o nome de {@code usuarios1} vem antes,
     * zero se os nomes são iguais,
     * ou um valor positivo se o nome de {@code usuarios1} vem depois.
     */
    @Override
    public int compare(Usuarios usuarios1, Usuarios usuarios2){
        String nome1 = usuarios1.getNome();
        String nome2 = usuarios2.getNome();

        // --- Tratamento de Nulos ---
        if(nome1 == null && nome2 == null){
            return 0; // Ambos nulos, são iguais
        }
        if(nome1 == null){
            return 1; // Nulos são "maiores" (vão para o fim)
        }
        if(nome2 == null){
            return -1; // Nulos são "maiores" (vão para o fim)
        }

        // --- Comparação Caractere por Caractere ---
        int tamanhoMinimo = Math.min(nome1.length(),nome2.length());

        for(int i = 0; i < tamanhoMinimo; i ++){

            char char1 = nome1.charAt(i);
            char char2 = nome2.charAt(i);

            // Comparação case-insensitive
            char1 = Character.toLowerCase(char1);
            char2 = Character.toLowerCase(char2);

            if(char1 != char2){
                return char1 - char2; // Retorna a diferença alfabética
            }

        }

        // Se um nome é prefixo do outro, o mais curto vem primeiro
        return nome1.length() - nome2.length();
    }
//Estudar lambda depois.


}