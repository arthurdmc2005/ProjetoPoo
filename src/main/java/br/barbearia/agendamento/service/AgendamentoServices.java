package br.barbearia.agendamento.service;

import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.*;
import br.barbearia.repository.UsuarioRepository;

public class AgendamentoServices {

    private AgendamentoRepository agendamentoRepository;
    private UsuarioRepository usuarioRepository;
    private

    public AgendamentoServices(AgendamentoRepository agendamentoRepository){
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento adicionarServico(Agendamento novoServico, String dataDoServico)throws Exception{
        if(novoServico.getTipoDeServico() == null || novoServico.getTipoDeServico().trim().isEmpty()){
            throw new Exception("O tipo de serviço é um campo obrigatório");
        }
        if(novoServico.getValor() == 0 || novoServico.getValor()<0){
            throw new Exception("O campo valor é um campo obrigatório");
        }
        if(novoServico.getAutorDoServico()==null || novoServico.getAutorDoServico().trim().isEmpty()){
            throw new Exception("O campo autor do serviço é obrigatório");
        }
        if(dataDoServico == null){
            throw new Exception("A data do serviço é obrigatória");
        }

        agendamentoRepository.adicionarServico(novoServico);

        return novoServico;

    }

    public Agendamento buscarPorServico(String nomeDoServico) throws Exception {

        if(nomeDoServico == null || nomeDoServico.trim().isEmpty()){
            throw new Exception("O nome do serviço buscado não pode ser vazio");
        }

        Agendamento servicoEncontrado = agendamentoRepository.buscarPorServico(nomeDoServico);

        return servicoEncontrado;
    }




}
