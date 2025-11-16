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

/**
 * Repositório responsável pelo gerenciamento (CRUD) de {@link Usuarios}.
 * <p>
 * Esta classe encapsula a lógica de acesso aos dados dos usuários
 * (Clientes, Funcionários, Gerentes), utilizando um {@link GerenciadorJSON}
 * para carregar e persistir a lista de usuários de/para um arquivo JSON.
 * </p>
 *
 * @see Usuarios
 * @see GerenciadorJSON
 * @see br.barbearia.service.UsuarioServices
 */
public class UsuarioRepository{


    /** O gerenciador genérico para manipulação do arquivo JSON de usuários. */
    private GerenciadorJSON<Usuarios> gerenciadorJSON;

    /** A lista de usuários (clientes, funcionários, etc.), carregada em memória. */
    private List<Usuarios> listaDeUsuarios;

    /**
     * Construtor do repositório de usuários.
     * <p>
     * Inicializa o {@link GerenciadorJSON} com o caminho do arquivo
     * e carrega a lista de usuários existente para a memória.
     * </p>
     *
     * @param caminhoDoArquivo O caminho relativo ou absoluto para o
     * arquivo JSON (ex: "BarbeariaComMaven/Usuarios.JSON").
     */
    public UsuarioRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<Usuarios>>() {
        });

        this.listaDeUsuarios = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva o estado atual da {@code listaDeUsuarios} (em memória)
     * de volta para o arquivo JSON.
     */
    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeUsuarios));
    }

    /**
     * Encontra o próximo ID disponível para um novo usuário.
     * <p>
     * Itera sobre a lista, encontra o ID mais alto em uso e retorna
     * esse valor + 1.
     * </p>
     *
     * @return O próximo ID inteiro disponível.
     */
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
     * (Questão 06: Salva um Usuário)
     * Adiciona um novo usuário à lista em memória.
     * <p>
     * Se o ID do {@code usuarioParaSalvar} for 0 (indicando que é novo),
     * o método atribui um novo ID (via {@link #proximoId()}) e
     * o adiciona à lista.
     * </p>
     * <p>
     * <b>Nota:</b> Este método não chama {@link #salvarNoJson()}
     * automaticamente.
     * </p>
     *
     * @param usuarioParaSalvar O objeto {@link Usuarios} a ser adicionado.
     */
    public void adicionarUsuario(Usuarios usuarioParaSalvar) {

        if (usuarioParaSalvar.getId() == 0) {
            usuarioParaSalvar.setId(proximoId());
            listaDeUsuarios.add(usuarioParaSalvar);
            System.out.println("LOG: (CREATE) Usuário " + usuarioParaSalvar.getLogin() + " salvo.");
        }

    }


    /**
     * (Questão 06: Atualizar um Usuário)
     * Atualiza um usuário existente na lista.
     * <p>
     * Procura o usuário pelo ID e substitui a instância antiga
     * pela nova instância {@code usuarioAtualizado}.
     * </p>
     * <p>
     * <b>Nota:</b> Este método não chama {@link #salvarNoJson()}
     * automaticamente.
     * </p>
     *
     * @param usuarioAtualizado O objeto {@link Usuarios} com os
     * dados atualizados (deve conter o ID do usuário original).
     */
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
     * [READ] Busca um usuário pelo seu LOGIN.
     *
     * @param loginParaBuscar O login (String) a ser procurado.
     * @return O objeto {@link Usuarios} se encontrado, ou {@code null}
     * se não for encontrado.
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
     * <p>
     * <b>Nota:</b> Este método não chama {@link #salvarNoJson()}
     * automaticamente.
     * </p>
     *
     * @param idParaDeletar O ID do usuário a ser removido.
     */
    public void removerUsuarioPeloId(int idParaDeletar) {
        boolean foiRemovido = listaDeUsuarios.removeIf(
                usuario -> usuario.getId() == idParaDeletar
        );

        if (foiRemovido) {
            System.out.println("LOG: (DELETE) Usuário ID " + idParaDeletar + " removido.");
        }
    }

    /**
     * Remove um usuário da lista com base no seu CPF (case-insensitive).
     * <p>
     * <b>Nota:</b> Este método não chama {@link #salvarNoJson()}
     * automaticamente.
     * </p>
     *
     * @param cpfParaDeletar O CPF do usuário a ser removido.
     */
    public void removerUsuarioPeloCpf(String cpfParaDeletar){
        boolean foiRemovido = listaDeUsuarios.removeIf(
                usuarios -> usuarios.getCpf().equalsIgnoreCase(cpfParaDeletar)
        );
    }

    /**
     * Busca um usuário na lista pelo seu CPF (correspondência exata).
     *
     * @param CpfParaBuscar O CPF a ser procurado.
     * @return O objeto {@link Usuarios} se encontrado, ou {@code null}
     * se não for encontrado.
     */
    public Usuarios buscarUsuarioPorCpf(String CpfParaBuscar){
        for (Usuarios usuariosDaLista : listaDeUsuarios){
            if (usuariosDaLista.getCpf()!= null && usuariosDaLista.getCpf().equals(CpfParaBuscar)){
                return usuariosDaLista;
            }
        }
        return null;

    }

    /**
     * Busca um usuário na lista pelo seu ID.
     *
     * @param idBuscado O ID a ser procurado.
     * @return O objeto {@link Usuarios} se encontrado, ou {@code null}
     * se não for encontrado.
     */
    public Usuarios buscarPorId(int idBuscado){
        for(Usuarios usuariosDaLista : listaDeUsuarios){
            if(usuariosDaLista.getId()== idBuscado){
                return usuariosDaLista;
            }
        }
        return null;
    }

    /**
     * Retorna uma referência direta à lista de usuários em memória.
     *
     * @return A {@link List} de {@link Usuarios}.
     */
    public List<Usuarios>listaDeUsuarios(){
        return listaDeUsuarios;
    }


}