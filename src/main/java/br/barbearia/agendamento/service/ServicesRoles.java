package br.barbearia.agendamento.service;

import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.ServicosRepository;


/**
 * Classe de serviço (Service Layer) responsável pelas regras de negócio
 * relacionadas aos {@link Servicos}.
 * <p>
 * Esta classe abstrai a lógica de validação para criar, remover ou buscar
 * serviços, comunicando-se com o {@link ServicosRepository} para a
 * persistência dos dados.
 * </p>
 *
 * @see Servicos
 * @see ServicosRepository
 */
public class ServicesRoles {

    /**
     * O repositório que gerencia a persistência dos dados de serviços.
     */
    private ServicosRepository servicosRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência do {@link ServicosRepository}.
     *
     * @param servicosRepository A instância do repositório
     * a ser usada por este serviço.
     */
    public ServicesRoles(ServicosRepository servicosRepository){
        this.servicosRepository = servicosRepository;
    }

    /**
     * Valida e salva um novo serviço no sistema.
     *
     * @param servico O nome do serviço (ex: "Corte").
     * @param precoBase O valor do serviço (deve ser > 0).
     * @param descricao Uma breve descrição do serviço.
     * @return O objeto {@link Servicos} recém-criado e salvo.
     * @throws Exception Se o preço for inválido, ou se o nome ou
     * descrição estiverem vazios.
     */
    public Servicos salvarNovoServico(String servico,double precoBase, String descricao)throws Exception{
        System.out.println("Valindo pedido de cadastro de serviço...");

        if(precoBase <= 0){
            throw new Exception("Preço não pode ser maior nem igual a zero");
        }
        if(servico == null || servico.trim().isEmpty()){
            throw new Exception("Adicione o nome do serviço");
        }
        if(descricao == null || descricao.trim().isEmpty()){
            throw new Exception("Coloque um descrição");
        }

        Servicos novoServico = new Servicos();
        novoServico.setServico( servico);
        novoServico.setValor(precoBase);
        novoServico.setDescricao(descricao);

        servicosRepository.salvarServico(novoServico);

        return novoServico;

    }

    /**
     * Remove um serviço do sistema com base no seu ID.
     *
     * @param idDoServico O ID do serviço a ser removido.
     * @throws Exception Se o ID for inválido (menor que 0) ou se
     * nenhum serviço for encontrado com o ID fornecido.
     */
    public void removerServico(int idDoServico) throws Exception{
        if(idDoServico<0){
            throw new Exception("Id não existe");
        }

        Servicos servicoParaDeletar = servicosRepository.buscarPorId(idDoServico);

        if(servicoParaDeletar==null){
            throw new Exception("Id de serviço não encontrado");

        }

        servicosRepository.removerServico(idDoServico);
    }

    /**
     * Busca um serviço pelo seu ID.
     *
     * @param idServico O ID do serviço a ser buscado.
     * @return O objeto {@link Servicos} encontrado.
     * @throws Exception Se o ID for inválido (<= 0) ou se o serviço
     * não for encontrado.
     */
    public Servicos buscarServicoPorId(int idServico)throws Exception{
        if(idServico<=0){
            throw new Exception("Id não encontrado");
        }

        Servicos servicoBuscado= servicosRepository.buscarPorId(idServico);

        if(servicoBuscado==null){
            throw new Exception("Id não pode ser nulo");
        }

        return servicoBuscado;


    }

    /**
     * Busca um serviço pelo seu nome (case-insensitive).
     *
     * @param nomeDoServico O nome do serviço a ser buscado.
     * @return O objeto {@link Servicos} encontrado.
     * @throws Exception Se o nome for nulo/vazio ou se o serviço
     * não for encontrado.
     */
    public Servicos buscarServicoPeloNome(String nomeDoServico)throws Exception{
        if(nomeDoServico==null || nomeDoServico.trim().isEmpty()){
            throw new Exception("Nome não pode ser nulo");
        }
        Servicos servicoBuscado = servicosRepository.buscarPorTipoDeServico(nomeDoServico);

        if(servicoBuscado == null){
            throw new Exception("Serviço com nome" + nomeDoServico + "não encontrado");
        }

        return servicoBuscado;
    }
}