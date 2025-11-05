package br.barbearia.repository;


import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.model.Usuarios;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

/**
 * Repositório de Usuários que salva e lê de um arquivo JSON.
 */
public class UsuarioRepository {


    /**
     * Ferramenta que ''escreve'' o fichário;
     */
    private ObjectMapper objectMapper;

    /**
     * O "Endereço" do arquivo no disco.
     */
    private File arquivoJson;


    /**
     * Lista que armazenamos os Usuários e que faremos a leitura deles ( mais rápido do que o JSON )
     */
    private List<Usuarios> listaDeUsuariosCache;


    /**
     * O CONSTRUTOR.
     * O que faz: Este bloco de código é executado UMA VEZ, no momento em que
     * você cria o repositório (ex: new UsuarioRepository(...)).
     * É o momento de "preparar a casa".
     *
     * @param caminhoDoArquivo O nome do arquivo que queremos usar (ex: "Usuarios.JSON")
     */
    public UsuarioRepository(String caminhoDoArquivo) {

        /**
         * Cria a instância do Jackson.
         */
        this.objectMapper = new ObjectMapper();

        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);


        /**
         * Cria o objeto "Endereço" que aponta para o nosso arquivo.
         */
        this.arquivoJson = new File(caminhoDoArquivo);

        /**
         * Chama nossa função (que está logo abaixo) para LER o arquivo
         */
        this.listaDeUsuariosCache = carregarDoJson();
    }

    /**
     * Função PRIVADA (só o repositório usa) para LER o arquivo JSON.
     * @return A lista de usuários que estava no arquivo.
     */
    private List<Usuarios> carregarDoJson() {

        try {


            if (!arquivoJson.exists()) {
                return new ArrayList<>();
            }


            return objectMapper.readValue(arquivoJson, new TypeReference<List<Usuarios>>() {});

        }

        catch (IOException e) {
            System.err.println("ERRO: Falha ao carregar o arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Função PRIVADA (só o repositório usa) para SALVAR o cache no JSON.
     */
    private void salvarNoJson() {
        try {
            objectMapper.writeValue(arquivoJson, listaDeUsuariosCache);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar o arquivo JSON: " + e.getMessage());
        }
    }

    /**
     * Função PRIVADA para descobrir qual o próximo ID disponível.
     * (Não podemos mais começar do 1, temos que olhar o que já foi salvo)
     */
    private int proximoId() {
        int maxId = 0;

        for (Usuarios usuario : listaDeUsuariosCache) {
            if (usuario.getId() > maxId) {
                maxId = usuario.getId();
            }
        }
        return maxId + 1;
    }



    /**
     * [CREATE / UPDATE] Salva ou Atualiza um usuário.
     * Esta é a lógica "inteligente" que você aprendeu.
     */
    public void adicionarUsuario(Usuarios usuarioParaSalvar) {

        if (usuarioParaSalvar.getId() == 0) {
            usuarioParaSalvar.setId(proximoId());
            listaDeUsuariosCache.add(usuarioParaSalvar);
            System.out.println("LOG: (CREATE) Usuário " + usuarioParaSalvar.getLogin() + " salvo.");

        } else {
            return;
        }

        salvarNoJson();
    }


    public void atualizarUsuarioNaLista(Usuarios usuarioAtualizado) {
        for (int i = 0; i < listaDeUsuariosCache.size(); i++) {

            if (listaDeUsuariosCache.get(i).getId() == usuarioAtualizado.getId()) {

                listaDeUsuariosCache.set(i, usuarioAtualizado);
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
        for (Usuarios usuarioDaLista : listaDeUsuariosCache) {

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
        boolean foiRemovido = listaDeUsuariosCache.removeIf(
                usuario -> usuario.getId() == idParaDeletar
        );

        if (foiRemovido) {
            System.out.println("LOG: (DELETE) Usuário ID " + idParaDeletar + " removido.");
            salvarNoJson();
        }
    }

    public Usuarios buscarUsuarioPorCpf(String CpfParaBuscar){
        for (Usuarios usuariosDaLista : listaDeUsuariosCache){
            if (usuariosDaLista.getCpf()!= null && usuariosDaLista.getCpf().equals(CpfParaBuscar)){
                return usuariosDaLista;
            }
        }
        return null;

    }

    public Usuarios buscarPorId(int idBuscado){
        for(Usuarios usuariosDaLista : listaDeUsuariosCache){
            if(usuariosDaLista.getId()== idBuscado){
                return usuariosDaLista;
            }
        }
        return null;
    }
}