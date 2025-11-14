package br.barbearia.repository;


import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.model.Usuarios;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository{



    private GerenciadorJSON<Usuarios> gerenciadorJSON;

    private List<Usuarios> listaDeUsuarios;

    public UsuarioRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Usuarios>>() {
        });

        this.listaDeUsuarios = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeUsuarios));
    }

    private int proximoId() {
        int maxId = 0;

        for (Usuarios usuario : listaDeUsuarios) {
            if (usuario.getId() > maxId) {
                maxId = usuario.getId();
            }
        }
        return maxId + 1;
    }



    /**
     * [CREATE / UPDATE] Salva ou Atualiza um usuário.
     *
     */

    //Questão 06: Salva um Usuário
    public void adicionarUsuario(Usuarios usuarioParaSalvar) {

        if (usuarioParaSalvar.getId() == 0) {
            usuarioParaSalvar.setId(proximoId());
            listaDeUsuarios.add(usuarioParaSalvar);
            System.out.println("LOG: (CREATE) Usuário " + usuarioParaSalvar.getLogin() + " salvo.");
        }

    }


    //Questão 06: Atualizar um Usuário
    public void atualizarUsuarioNaLista(Usuarios usuarioAtualizado) {
        for (int i = 0; i < listaDeUsuarios.size(); i++) {

            if (listaDeUsuarios.get(i).getId() == usuarioAtualizado.getId()) {

                listaDeUsuarios.set(i, usuarioAtualizado);
                System.out.println("LOG: (UPDATE) Usuário " + usuarioAtualizado.getLogin() + " atualizado.");
                return;
            }
        }
    }


    /**
     * [READ] Busca um usuário pelo LOGIN.
     * (Exatamente o seu código perfeito de antes!)
     */
    public Usuarios buscarPorLogin(String loginParaBuscar) {
        for (Usuarios usuarioDaLista : listaDeUsuarios) {

            if(usuarioDaLista.getLogin() != null && usuarioDaLista.getLogin().equals(loginParaBuscar)){

                return usuarioDaLista;
            }
        }
        return null;
    }

    /**
     * [DELETE] Deleta um usuário pelo seu ID.
     */
    public void removerUsuarioPeloId(int idParaDeletar) {
        boolean foiRemovido = listaDeUsuarios.removeIf(
                usuario -> usuario.getId() == idParaDeletar
        );

        if (foiRemovido) {
            System.out.println("LOG: (DELETE) Usuário ID " + idParaDeletar + " removido.");
        }
    }

    public void removerUsuarioPeloCpf(String cpfParaDeletar){
        boolean foiRemovido = listaDeUsuarios.removeIf(
                usuarios -> usuarios.getCpf().equalsIgnoreCase(cpfParaDeletar)
        );
    }

    public Usuarios buscarUsuarioPorCpf(String CpfParaBuscar){
        for (Usuarios usuariosDaLista : listaDeUsuarios){
            if (usuariosDaLista.getCpf()!= null && usuariosDaLista.getCpf().equals(CpfParaBuscar)){
                return usuariosDaLista;
            }
        }
        return null;

    }

    public Usuarios buscarPorId(int idBuscado){
        for(Usuarios usuariosDaLista : listaDeUsuarios){
            if(usuariosDaLista.getId()== idBuscado){
                return usuariosDaLista;
            }
        }
        return null;
    }

    public List<Usuarios>listaDeUsuarios(){
        return listaDeUsuarios;
    }


}