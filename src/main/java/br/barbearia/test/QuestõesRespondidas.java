package br.barbearia.test;

import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.Financeiro.service.RegistroDeVendasServices;
import br.barbearia.OrdensDeServiço.OrdensDeServicoModel;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRepository;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRoles;
import br.barbearia.agendamento.Memento.AgendamentoCaraTaker;
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateDateAgendamento;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.AgendamentoServices;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.CompareNameCliente;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.UsuarioServices;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class QuestõesRespondidas {

    public static void demonstrarQuestao17(UsuarioRepository usuarioRepository) {
        System.out.println("====Questão 17====");
        System.out.println("Demonstrando Iterator com while(iterator.hasNext())");

        // 1. Instanciar o Iterator para a lista de Usuários (Clientes/Funcionários)
        List<Usuarios> listaDeUsuarios = usuarioRepository.listaDeUsuarios();
        Iterator<Usuarios> iteratorUsuarios = listaDeUsuarios.iterator();

        System.out.println("Percorrendo a lista de usuários:");

        // 2. Fazer testes com o loop while(hasNext)
        while(iteratorUsuarios.hasNext()) {
            // 3. Imprimir(iterator.next())
            Usuarios usuarioAtual = iteratorUsuarios.next();
            System.out.println(usuarioAtual.toString());
        }
        System.out.println("--- Fim da lista de usuários ---");
    }

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
        OrdensDeServicoRepository ordensDeServicoRepository = new OrdensDeServicoRepository("BarbeariaComMaven/OrdensDeServico.JSON");
        OrdensDeServicoRoles ordensDeServicoRoles = new OrdensDeServicoRoles(ordensDeServicoRepository);

        //Questão 01: {
        System.out.printf("====Questão 01====");
        System.out.println("Diagrama de Classes criado no Latex.");
        // }
        //Questão 02: {
        System.out.println("====Questão 02====");
        System.out.println("Acessar classe Funcionario, Cliente e Gerente. Todos eles são um Usuário que é definido pelo cargo, mostrar o tratamento de erro e como funciona na classe UsuariosServices;");
        //}
        //Questão 03:{
        System.out.println("====Questão 03====");
        System.out.println("Acessar qualquer classe do tipo Model;")
        ;
        // }
        //Questão 04:{
        System.out.println("====Questão 04====");
        System.out.println("Acessar a classe Funcionario e observar o construtor");
        // }
        //Questão 05:{
        System.out.println("====Questão 05====");
        System.out.println("Acessar a classe EstacaoRepositoryb para observar as estações que estão armazenadas de forma estática");
        // }
        //Questão 06:{
        System.out.println("====Questão 06====");
        try {
           usuarioServices.cadastrarUsuario("Arthur","22222222222","3899788614","Funcionario","fodase123","fodase123");
           usuarioServices.removerUsuario(1);
           usuarioServices.buscarUsuarioPorCpf("22222222222");
            System.out.println("Caso queria olhar com mais clareza, acessar a classe UsuariosServices;");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 07:{
        System.out.println("====Questão 07====");
        try {
            usuarioServices.cadastrarUsuario("Arthur","22222222222","3899788614","Cliente","fodase123","fodase123");
            usuarioServices.removerUsuario(1);
            usuarioServices.buscarUsuarioPorCpf("22222222222");
            System.out.println("Caso queria olhar com mais clareza, acessar a classe UsuariosServices;");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 08:{
        System.out.println("====Questão 08====");
        System.out.println("Mostrar as classes de ordem de serviço");
        try {
            ordensDeServicoRoles.buscarOSPeloCpf("22222222222");
            ordensDeServicoRoles.buscarOSPelaData("15/11/2025");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 09:{
        System.out.println("====Questão 09====");
        System.out.println("Mostrar o método generico salvarNoJson e mostrar o funcionamento na classe Barbearia ( main ) evidenciando que é preciso fazer a chamada no final de tudo para que tudo seja salvo.");
        // }
        //Questão 10:{
        System.out.println("====Questão 10====");
        try {
            ordensDeServicoRoles.registrarOrdensDeServico("22222222222", "11111111111", "Serviço top", 60, 1, "15/11/2025");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // }
        //Questão 11:{
        System.out.println("====Questão 11====");
        System.out.println("Acessa a o método services.pegarContadorDeInstancias para analisar as funcionalidades");
        Servicos.pegarContadorInstanciasProtegido();
        Servicos.pegarContadorInstanciasPrivado();

        // }
        //Questão 12:{
        System.out.println("====Questão 12====");
        System.out.println("Chamar a ordensDeServicoModel.contadorDeOrdensDeServico");
        OrdensDeServicoModel.pegarContadorInstancias();
        // }
        //Questão 13:{
        System.out.println("====Questão 13====");
        System.out.println("Acessar a classe CompareteNameCliente e ComparateAgendamento");

        // }
        //Questão 14:{
        System.out.println("====Questão 14====");
        System.out.println("Mostrar as JSON prontas!");
        // }
        //Questão 15:{
        System.out.println("====Questão 15====");
        System.out.println("Acessar a classe FilaEsperaRepository");
        // }
        //Questão 16:{
        System.out.println("====Questão 16====");
        System.out.println("Precisa fazer");
        // }
        //Questão 17:{
        System.out.println("====Questão 17====");
        demonstrarQuestao17(usuarioRepository);

        //Iterator<Usuarios> iteratorUsuarios = usuarioRepository.listaDeUsuarios().iterator();
        //while (IteratorUsuarios.hasNext()){
            //Usuarios usuarioAtual= iteratorUsuarios.next();
        //}
        // }
        //Questão 18:{
        System.out.println("====Questão 18====");
        System.out.println("Precisa fazer");
        // }



    }
}
