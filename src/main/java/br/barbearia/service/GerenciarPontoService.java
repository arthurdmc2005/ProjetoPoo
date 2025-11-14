package br.barbearia.service;

import br.barbearia.model.RegistroPonto;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciarPontoRepository;
import br.barbearia.repository.UsuarioRepository;

import java.time.LocalDateTime;

public class GerenciarPontoService {


    private GerenciarPontoRepository gerenciarPontoRepository;
    private UsuarioRepository usuarioRepository;

    public GerenciarPontoService(GerenciarPontoRepository gerenciarPontoRepository, UsuarioRepository usuarioRepository){
        this.gerenciarPontoRepository = gerenciarPontoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RegistroPonto registroPonto(int funcionarioId, String tipo)throws Exception{
        System.out.println("Valindo ponto batido");
        if(tipo==null || tipo.trim().isEmpty()){
            throw new Exception("O tipo de ponto é obrigatório");
        }
        Usuarios funcionario = usuarioRepository.buscarPorId(funcionarioId);
        if(funcionario == null){
            throw new Exception("Funcionario não encontrado");
        }
        System.out.println("Monta o objeto ( log pra visualização )");

        RegistroPonto novoPonto = new RegistroPonto();
        novoPonto.setFuncionarioId(funcionarioId);
        novoPonto.setDataHora(LocalDateTime.now());
        novoPonto.setTipo(tipo.toUpperCase());

        gerenciarPontoRepository.adicionarPonto(novoPonto);

        return novoPonto;
    }




}

