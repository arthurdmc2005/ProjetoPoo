package br.barbearia.agendamento.service;

import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.ServicosRepository;


public class ServicesRoles {

    private ServicosRepository servicosRepository;

    public ServicesRoles(ServicosRepository servicosRepository){
        this.servicosRepository = servicosRepository;
    }

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



