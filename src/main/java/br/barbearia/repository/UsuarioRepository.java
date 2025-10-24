package br.barbearia.repository;

import br.barbearia.model.Cliente;
import br.barbearia.model.Usuarios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {


    private List<Usuarios> listaDeUsuarios = new ArrayList<>();
    private int proximoId = 1;

    public void salvarUsuario(Usuarios usuarioParaSalvar) {
        if (usuarioParaSalvar.getId() == 0) {
            usuarioParaSalvar.setId(proximoId);
            listaDeUsuarios.add(usuarioParaSalvar);
            proximoId++;
            System.out.println("Registro: Usuário: " + usuarioParaSalvar.getLogin() + "salvo com o ID" + usuarioParaSalvar.getId());
        } else {
            System.out.println("Usuário Cadastrado");
        }
    }

    public Usuarios buscarPorLogin(String loginParaBuscar){

        for(Usuarios usuarioDaLista : listaDeUsuarios){
            if(usuarioDaLista.getLogin().equals(loginParaBuscar)){
                return usuarioDaLista;
            }

        }
        return null;
    }
}