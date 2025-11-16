package br.barbearia.service;


import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.model.CompareNameCliente;

import java.util.ArrayList;
import java.util.Comparator;

import java.rmi.server.ExportException; // Esta importação parece não ser utilizada
import java.util.List;

/**
 * Classe de serviço (Service Layer) responsável pelas regras de negócio
 * relacionadas aos {@link Usuarios}.
 * <p>
 * Esta classe abstrai a lógica de validação para criar, remover, buscar e
 * atualizar usuários, comunicando-se com o {@link UsuarioRepository}
 * para a persistência dos dados.
 * </p>
 *
 * @see Usuarios
 * @see UsuarioRepository
 */
public class UsuarioServices {

    /** O repositório que gerencia a persistência dos dados de usuários. */
    private UsuarioRepository usuarioRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência do {@link UsuarioRepository}.
     *
     * @param usuarioRepository A instância do repositório
     * a ser usada por este serviço.
     */
    public UsuarioServices(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Método de conveniência para cadastrar um novo usuário
     * passando os parâmetros individualmente.
     * <p>
     * Este método cria um novo objeto {@link Usuarios} e o repassa
     * para o método {@link #cadastrarUsuario(Usuarios)}.
     * </p>
     *
     * @param nome O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param telefone O telefone do usuário.
     * @param tipo O tipo do usuário (ex: "Cliente", "Funcionario").
     * @param senha A senha do usuário (deve ser o hash).
     * @param login O login do usuário.
     * @return O objeto {@link Usuarios} recém-criado e salvo.
     * @throws Exception Se a validação no método sobrecarregado falhar.
     */
    public Usuarios cadastrarUsuario(String nome, String cpf, String telefone,String tipo, String senha,String login)throws Exception{
        Usuarios usuarioParaCadastrar = new Usuarios();
        usuarioParaCadastrar.setNome(nome);
        usuarioParaCadastrar.setCpf(cpf);
        usuarioParaCadastrar.setTelefone(telefone);
        usuarioParaCadastrar.setLogin(login);
        usuarioParaCadastrar.setSenhaHash(senha);
        usuarioParaCadastrar.setTipo(tipo);

        return this.cadastrarUsuario(usuarioParaCadastrar);
    }

    /**
     * Valida e cadastra um novo usuário no sistema.
     * <p>
     * Realiza diversas validações (nome, CPF, telefone, tipo de usuário)
     * e verifica se o CPF já está em uso antes de enviar o objeto
     * para o repositório.
     * </p>
     *
     * @param novoUsuario O objeto {@link Usuarios} a ser validado e salvo.
     * @return O {@code novoUsuario} após ser salvo no repositório.
     * @throws Exception Se qualquer regra de validação falhar
     * (ex: nome vazio, CPF inválido, CPF duplicado, tipo inválido).
     */
    public Usuarios cadastrarUsuario(Usuarios novoUsuario)throws Exception {
        if (novoUsuario.getNome() == null || novoUsuario.getNome().trim().isEmpty()) {
            throw new Exception("Existem campos obrigátorios que não foram preenchidos");
        }
        if (novoUsuario.getCpf() == null || novoUsuario.getCpf().replaceAll("[^0-9]", "").length() != 11) {
            throw new Exception("Coloque o formato correto para CPF");
        }
        if (novoUsuario.getTelefone() == null || novoUsuario.getTelefone().trim().isEmpty()) {
            throw new Exception("Preencha o campo TELEFONE");
        }
        if (usuarioRepository.buscarUsuarioPorCpf(novoUsuario.getCpf()) != null) {
            throw new Exception("Este CPF já está cadastrado");
        }

        String tipoDoUsuario = novoUsuario.getTipo();
        if(novoUsuario.getTipo()==null || novoUsuario.getTipo().trim().isEmpty()){
            throw new Exception("Preencha o tipo de Usuário");
        }

        boolean tipoValido = tipoDoUsuario.equalsIgnoreCase("Gerente")
                || tipoDoUsuario.equalsIgnoreCase("Cliente")
                || tipoDoUsuario.equalsIgnoreCase("Funcionario");
        if(!tipoValido){
            throw new Exception("Usuário Inválido");
        }


        System.out.println("Todas as validações foram atendidas. Cliente salvo.");
        usuarioRepository.adicionarUsuario(novoUsuario);
        return novoUsuario;



    }

    /**
     * Remove um usuário do sistema com base no seu ID.
     *
     * @param idParaRemover O ID do usuário a ser removido.
     * @throws Exception Se o ID for inválido (<= 0) ou se
     * nenhum usuário for encontrado com o ID fornecido.
     */
    public void removerUsuario(int idParaRemover)throws Exception{
        if(idParaRemover<=0) {
            throw new Exception("Id inválido");
        }
        Usuarios usuarioParDeletar = usuarioRepository.buscarPorId(idParaRemover);

        if(usuarioParDeletar==null){
            throw new Exception("Usuário não encontrado");



        }

        usuarioRepository.removerUsuarioPeloId(idParaRemover);


    }

    /**
     * Busca um usuário pelo seu CPF.
     *
     * @param cpfBuscado O CPF do usuário a ser buscado.
     * @return O objeto {@link Usuarios} encontrado.
     * @throws Exception Se o CPF for nulo/vazio ou se o usuário
     * não for encontrado.
     */
    public Usuarios buscarUsuarioPorCpf(String cpfBuscado)throws Exception{

        if(cpfBuscado==null || cpfBuscado.trim().isEmpty()){
            throw new Exception("Cpf nulo não pode bosta");
        }

        Usuarios usuarioEncontrado = usuarioRepository.buscarUsuarioPorCpf(cpfBuscado);

        if(usuarioEncontrado==null){
            throw new Exception("Não encontrei esse cpf");

        }

        return usuarioEncontrado;

    }

    /**
     * (Questão 07: Remover Clientes(Usuários))
     * Remove um usuário do sistema com base no seu CPF.
     *
     * @param cpfDoUsuarioASerRemovido O CPF do usuário a ser removido.
     * @throws Exception Se o CPF for nulo, vazio, não tiver 11 dígitos,
     * ou se nenhum usuário for encontrado com o CPF fornecido.
     */
    public void removerUsuarioPeloCpf(String cpfDoUsuarioASerRemovido)throws Exception{
        if(cpfDoUsuarioASerRemovido == null || cpfDoUsuarioASerRemovido.trim().isEmpty()){
            throw new Exception("O cpf não pode ser nulo");
        }
        String cpfLimpo = cpfDoUsuarioASerRemovido.replaceAll("[^0=9]","");

        if(cpfLimpo.length()!=11){
            throw new Exception("O cpf deve ter 11 digitos");
        }
        Usuarios usuarioParaRemover = usuarioRepository.buscarUsuarioPorCpf(cpfLimpo);

        if(usuarioParaRemover == null){
            throw new Exception("Cpf não encontrado");
        }

        usuarioRepository.removerUsuarioPeloCpf(cpfLimpo);
    }


    /**
     * Atualiza os dados de um usuário no repositório.
     *
     * @param usuarioAtualizado O objeto {@link Usuarios} com os dados
     * atualizados (deve conter o ID do usuário original).
     * @throws Exception Se o nome do usuário for nulo/vazio ou se
     * o usuário (com base no ID) não for encontrado no repositório.
     */
    public void atualizarUsuarioNaLista(Usuarios usuarioAtualizado)throws Exception{
        if(usuarioAtualizado.getNome()==null || usuarioAtualizado.getNome().trim().isEmpty()){
            throw new Exception("Coloca o nome merda");
        }
        Usuarios usuarioAntigo = usuarioRepository.buscarPorId(usuarioAtualizado.getId());

        if(usuarioAntigo==null){
            throw new Exception("cade o usuario imundo");
        }

        usuarioRepository.atualizarUsuarioNaLista(usuarioAtualizado);
        //volte aqui depois;
    }
}