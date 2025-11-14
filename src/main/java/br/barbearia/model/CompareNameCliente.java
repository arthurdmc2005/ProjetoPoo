package br.barbearia.model;

import br.barbearia.repository.UsuarioRepository;

import java.util.Comparator;

public class CompareNameCliente implements Comparator<Usuarios> {

    @Override
    public int compare(Usuarios usuarios1, Usuarios usuarios2){
        String nome1 = usuarios1.getNome();
        String nome2 = usuarios2.getNome();

        if(nome1 == null && nome2 == null){
        return 0;
        }
        if(nome1 == null){
            return 1;
        }
        if(nome2 == null){
            return -1;
        }

        int tamanhoMinimo = Math.min(nome1.length(),nome2.length());

        for(int i = 0; i < tamanhoMinimo; i ++){

            char char1 = nome1.charAt(i);
            char char2 = nome2.charAt(i);

            char1 = Character.toLowerCase(char1);
            char2 = Character.toLowerCase(char2);

            if(char1 != char2){
                return char1 - char2;
            }

        }
        return nome1.length() - nome2.length();
    }
//Estudar lambda depois.


}
