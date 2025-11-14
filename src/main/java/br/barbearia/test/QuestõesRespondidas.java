package br.barbearia.test;

import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.Financeiro.service.RegistroDeVendasServices;
import br.barbearia.agendamento.Memento.AgendamentoCaraTaker;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.AgendamentoServices;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.UsuarioServices;

import java.time.LocalDate;

public class QuestõesRespondidas {

    public static void main(String[] args) {

        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        ServicosRepository servicosRepository = new ServicosRepository("BarbearioComMaven/Servicos.JSON");
        AgendamentoRepository agendamentoRepository = new AgendamentoRepository("BarbeariaComMaven/Agendamento.JSON");
        AgendamentoCaraTaker agendamentoCaraTaker = new AgendamentoCaraTaker();
        EstacaoRepository estacaoRepository = new EstacaoRepository();
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);
        ServicesRoles servicesRoles = new ServicesRoles(servicosRepository);
        AgendamentoServices agendamentoServices = new AgendamentoServices(agendamentoRepository, usuarioRepository, servicosRepository,estacaoRepository,agendamentoCaraTaker);
        RegistroDeVendasRepository registroDeVendasRepository = new RegistroDeVendasRepository("BarbeariaComMaven/Vendas.JSON");
        RegistroDeVendasServices registroDeVendasServices = new RegistroDeVendasServices(registroDeVendasRepository, usuarioRepository);

        //Questão 01: {
        System.out.printf("====Questão 01====");
        System.out.println("Diagrama criado!");
        // }
        //Questão 02: {
        System.out.println("====Questão 02====");
        System.out.println("Acessar a classe UsuariosServices ( Linha 48 )");
        //}
        //Questão 03:{
        System.out.println("====Questão 03====");
        System.out.println("Acessar qualquer classe do tipo Model");
        // }
        //Questão 04:{
        System.out.println("====Questão 04====");
        System.out.println("Acessar a classe Funcionario");
        // }
        //Questão 05:{
        System.out.println("====Questão 05====");
        System.out.println("Acessar a classe EstacaoRepository");
        // }
        //Questão 06:{
        System.out.println("====Questão 06====");
        try {
            //usuarioServices.cadastrarUsuario("Arthur", "01973214633", "31998777420", "Funcionario", "fodase123", "naosei123");
            //usuarioServices.atualizarUsuarioNaLista(); //terminar esse
            //usuarioServices.removerUsuario();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 07:{
        System.out.println("====Questão 07====");
        try {
           // usuarioServices.cadastrarUsuario("Jeremiaz", "01987323422", "3199877760", "Cliente", "fodase333", "naosei1234");
            //usuarioServices.atualizarUsuarioNaLista(); // terminar esse
            //usuarioServices.removerUsuarioPeloCpf();
            //usuarioServices.removerUsuario();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 08:{
        System.out.println("====Questão 08====");
        System.out.println("Precisa fazer!");
        // }
        //Questão 09:{
        System.out.println("====Questão 09====");
        System.out.println("Precisa fazer!");
        // }
        //Questão 10:{
        System.out.println("====Questão 10====");
        try{
            servicesRoles.buscarServicoPeloNome("Barba");
            registroDeVendasServices.registrarVenda("Minox", LocalDate.of(2019,10,10),2,30,1);
            //fazer o emitirextrato

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 11:{
        System.out.println("====Questão 11====");
        System.out.println("Precisar fazer!");
        // }
        //Questão 12:{
        System.out.println("====Questão 12====");
        System.out.println("Precisa fazer!");
        // }
        //Questão 13:{
        System.out.println("====Questão 13====");
        System.out.println("Acessar a classe CompareteNameCliente");
        // }
        //Questão 14:{
        System.out.println("====Questão 14====");
        System.out.println("Mostrar as JSON prontas!");
        // }
        //Questão 15:{
        System.out.println("====Questão 15====");
        System.out.println("Precisa fazer");
        // }
        //Questão 16:{
        System.out.println("====Questão 16====");
        System.out.println("Precisa fazer");
        // }
        //Questão 17:{
        System.out.println("====Questão 17====");
        System.out.println("Precisa fazer");
        // }
        //Questão 18:{
        System.out.println("====Questão 18====");
        System.out.println("Precisa fazer");
        // }



    }
}
