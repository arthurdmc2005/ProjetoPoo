package br.barbearia.test;


import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.Financeiro.repository.ProdutosRepository;
import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.Financeiro.service.ProdutosService;
import br.barbearia.Financeiro.service.RegistroDeVendasServices;
import br.barbearia.OrdensDeServiço.OrdensDeServicoModel;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRepository;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRoles;
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateDateAgendamento;
import br.barbearia.agendamento.model.Estacao;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.CompareNameCliente;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.UsuarioServices;


import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Menu principal do Sistema de Barbearia, focado nas funcionalidades.
 */
public class Barbearia {

    private static final String PATH_USUARIOS = "BarbeariaComMaven/Usuarios.JSON";
    private static final String PATH_SERVICOS = "BarbeariaComMaven/Servicos.JSON";
    private static final String PATH_ORDENS = "BarbeariaComMaven/OrdensDeServico.JSON";
    private static final String PATH_AGENDAMENTOS = "BarbeariaComMaven/Agendamento.JSON";
    private static final String PATH_VENDAS = "BarbeariaComMaven/Vendas.JSON";
    private static final String PATH_PRODUTOS = "BarbeariaComMaven/Produtos.JSON";


    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository(PATH_USUARIOS);
    private static final UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);

    private static final ServicosRepository servicosRepository = new ServicosRepository(PATH_SERVICOS);
    private static final ServicesRoles servicesRoles = new ServicesRoles(servicosRepository);

    private static final OrdensDeServicoRepository osRepository = new OrdensDeServicoRepository(PATH_ORDENS);
    private static final OrdensDeServicoRoles osRoles = new OrdensDeServicoRoles(osRepository);

    private static final AgendamentoRepository agendamentoRepository = new AgendamentoRepository(PATH_AGENDAMENTOS);
    private static final EstacaoRepository estacaoRepository = new EstacaoRepository();

    private static final ProdutosRepository produtosRepository = new ProdutosRepository(PATH_PRODUTOS);
    private static final RegistroDeVendasRepository vendasRepository = new RegistroDeVendasRepository(PATH_VENDAS);
    private static final RegistroDeVendasServices vendasServices = new RegistroDeVendasServices(vendasRepository, usuarioRepository);

    private static final ProdutosService produtosService = new ProdutosService(produtosRepository);


    /**
     * Ponto de entrada principal do aplicativo.
     */
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   BEM-VINDO AO SISTEMA DA BARBEARIA");
        System.out.println("=========================================");
        System.out.println("Carregando dados dos arquivos JSON...");

        while (true) {
            printMenuPrincipal();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    exibirMenuPessoas();
                    break;
                case "2":
                    exibirMenuAgendamentos();
                    break;
                case "3":
                    exibirMenuLojaEEstoque();
                    break;
                case "4":
                    exibirMenuAdmin();
                    break;
                case "0":
                    salvarTudo();
                    System.out.println("Até logo!");
                    return;
                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        }
    }



    private static void printMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gestão de Pessoas (Clientes/Colaboradores)");
        System.out.println("2. Agendamentos e Serviços");
        System.out.println("3. Loja e Estoque");
        System.out.println("4. Painel Administrativo (Debug/Testes)");
        System.out.println("----------------------");
        System.out.println("0. Salvar e Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Menu para gerenciar Clientes e Colaboradores.
     */
    private static void exibirMenuPessoas() {
        while (true) {
            System.out.println("\n--- 1. Gestão de Pessoas ---");
            System.out.println("1. Cadastrar Novo Cliente");
            System.out.println("2. Excluir Cliente (por CPF)");
            System.out.println("3. Cadastrar Novo Colaborador");
            System.out.println("4. Listar Clientes (Ordenado por Nome)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        cadastrarNovoCliente();
                        break;
                    case "2":
                        excluirCliente();
                        break;
                    case "3":
                        cadastrarNovoColaborador();
                        break;
                    case "4":
                        listarClientesOrdenados();
                        break;
                    case "9":
                        return;
                    default:
                        System.err.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("\n!!! ERRO: " + e.getMessage());
            }
            pausar();
        }
    }

    /**
     * Menu para gerenciar Agendamentos e Ordens de Serviço.
     */
    private static void exibirMenuAgendamentos() {
        while (true) {
            System.out.println("\n--- 2. Agendamentos e Serviços ---");
            System.out.println("1. Listar Estações de Atendimento");
            System.out.println("2. Listar Agendamentos (Ordenado por Data)");
            System.out.println("3. Registrar Nova Ordem de Serviço");
            System.out.println("4. Consultar Ordens de Serviço (por CPF do Cliente)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        listarEstacoes();
                        break;
                    case "2":
                        listarAgendamentosOrdenados();
                        break;
                    case "3":
                        registrarNovaOS();
                        break;
                    case "4":
                        consultarOsPorCliente();
                        break;
                    case "9":
                        return;
                    default:
                        System.err.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("\n!!! ERRO: " + e.getMessage());
            }
            pausar();
        }
    }

    /**
     * Menu para gerenciar Vendas da Loja.
     */
    private static void exibirMenuLojaEEstoque() {
        while (true) {
            System.out.println("\n--- 3. Loja e Estoque ---");
            System.out.println("1. Gestão de Vendas (Registrar, Extrato)");
            System.out.println("2. Gestão de Estoque (Cadastrar, Listar, Alterar Qtd)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    exibirMenuGestaoVendas();
                    break;
                case "2":
                    exibirMenuLojaEEstoque();
                    break;
                case "9":
                    return;
                default:
                    System.err.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenuGestaoVendas() {
        while (true) {
            System.out.println("\n--- 3.1 Gestão de Vendas ---");
            System.out.println("1. Registrar Venda de Produto");
            System.out.println("2. Emitir Extrato de Venda (por nome do produto)");
            System.out.println("9. Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        registrarVendaProduto();
                        break;
                    case "2":
                        emitirExtratoVenda();
                        break;
                    case "9":
                        return;
                    default:
                        System.err.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("\n!!! ERRO: " + e.getMessage());
            }
            pausar();
        }
    }

    /**
     * Menu para testar funcionalidades de Debug (Contadores, etc.).
     */
    private static void exibirMenuAdmin() {
        while (true) {
            System.out.println("\n--- 4. Painel Administrativo ---");
            System.out.println("1. Testar Contadores Estáticos (Serviços)");
            System.out.println("2. Testar Contadores Estáticos (Ordens de Serviço)");
            System.out.println("3. Ver Explicação (Vantagens/Desvantagens Contadores)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":

                        demonstrarContadoresServicos();
                        break;
                    case "2":

                        demonstrarContadoresOS();
                        break;
                    case "3":

                        explicarContadores();
                        break;
                    case "9":
                        return;
                    default:
                        System.err.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("\n!!! ERRO: " + e.getMessage());
            }
            pausar();
        }
    }





    private static void cadastrarNovoCliente() throws Exception {
        System.out.println("--- Cadastrar Novo Cliente ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF (só números): "); String cpf = scanner.nextLine();
        System.out.print("Telefone (só números): "); String tel = scanner.nextLine();
        String tipo = "Cliente";


        Usuarios novo = usuarioServices.cadastrarUsuario(nome, cpf, tel, tipo, "123", cpf); // Senha/Login padrão
        System.out.println("Cliente cadastrado com sucesso! ID: " + novo.getId());
    }


    private static void excluirCliente() throws Exception {
        System.out.println("--- Excluir Cliente ---");
        System.out.print("CPF do cliente para excluir: "); String cpf = scanner.nextLine();
        // (Questão 7)
        usuarioServices.removerUsuarioPeloCpf(cpf);
        System.out.println("Cliente removido com sucesso (se existia).");
    }


    private static void cadastrarNovoColaborador() throws Exception {
        System.out.println("--- Cadastrar Novo Colaborador ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF (só números): "); String cpf = scanner.nextLine();
        System.out.print("Telefone (só números): "); String tel = scanner.nextLine();
        System.out.print("Login: "); String login = scanner.nextLine();
        System.out.print("Senha: "); String senha = scanner.nextLine();
        String tipo = "Funcionario";


        Usuarios novo = usuarioServices.cadastrarUsuario(nome, cpf, tel, tipo, senha, login);
        System.out.println("Colaborador cadastrado com sucesso! ID: " + novo.getId());
    }

    private static void listarClientesOrdenados() {
        System.out.println("--- Clientes (Ordenados por Nome) ---");
        // (Questão 16)
        List<Usuarios> usuarios = usuarioRepository.listaDeUsuarios();
        Comparator<Usuarios> porNome = new CompareNameCliente();
        usuarios.sort(porNome);

        for (Usuarios u : usuarios) {
            if (u.getTipo() != null && u.getTipo().equalsIgnoreCase("Cliente")) {
                System.out.println("  - Nome: " + u.getNome() + ", CPF: " + u.getCpf());
            }
        }
    }


    private static void listarEstacoes() {
        System.out.println("--- Estações de Atendimento (Array Fixo) ---");

        List<Estacao> estacoes = estacaoRepository.listarTodas();
        for (Estacao estacao : estacoes) {
            System.out.println("  - " + estacao.toString()); // Usa o Req 3 (toString)
        }
        System.out.println("Total de estações fixas: " + estacoes.size());
    }


    private static void listarAgendamentosOrdenados() {
        System.out.println("--- Agendamentos (Ordenados por Data) ---");

        List<Agendamento> agendamentos = agendamentoRepository.listaDeAgendamentos();
        Comparator<Agendamento> porData = new ComparateDateAgendamento();
        agendamentos.sort(porData); // Aplicando o Comparator

        for (Agendamento a : agendamentos) {
            System.out.println("  - Data: " + a.getData() + ", ID: " + a.getId() + ", Status: " + a.getStatus());
        }
    }

    private static void registrarNovaOS() throws Exception {
        System.out.println("--- Registrar Nova Ordem de Serviço ---");
        System.out.print("CPF do Cliente: "); String cpfCliente = scanner.nextLine();
        System.out.print("CPF do Funcionário: "); String cpfFunc = scanner.nextLine();
        System.out.print("Diagnóstico do Serviço: "); String diag = scanner.nextLine();
        System.out.print("Valor (ex: 50.0): "); double valor = Double.parseDouble(scanner.nextLine());
        System.out.print("ID do Serviço (ex: 1): "); int servId = Integer.parseInt(scanner.nextLine());

        osRoles.registrarOrdensDeServico("11111111111","22222222222","Serviço realizado com sucesso",50,1,"15/11/2025");
        System.out.println("Ordem de Serviço registrada com sucesso.");
        salvarTudo();
    }

    private static void consultarOsPorCliente() throws Exception {
        System.out.println("--- Consultar Ordens de Serviço por Cliente ---");
        System.out.print("Digite o CPF do cliente (só números): ");
        String cpf = scanner.nextLine();

        System.out.println("Buscando ordens...");
        // (Questão 8 e 10)
        List<OrdensDeServicoModel> ordens = osRoles.buscarOSPeloCpf(cpf);

        if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem encontrada para este CPF.");
        }
    }

    private static void registrarVendaProduto() throws Exception {
        System.out.println("--- Registrar Venda de Produto ---");
        System.out.print("Nome do Produto: "); String nomeProd = scanner.nextLine();
        System.out.print("ID do Cliente: "); int clienteId = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor da Venda: "); double valor = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantidade Vendida: "); double qtd = Double.parseDouble(scanner.nextLine());

        // (Questão 10)
        vendasServices.registrarVenda(nomeProd, java.time.LocalDate.now(), clienteId, valor, qtd);
        System.out.println("Venda registrada com sucesso.");
    }

    // Ação 3.2: Emitir Extrato de Venda
    private static void emitirExtratoVenda() {
        System.out.println("--- Emitir Extrato de Venda ---");
        System.out.print("Nome do produto vendido: ");
        String nomeProduto = scanner.nextLine();
        // (Questão 10)
        RegistroDeVendas extrato = vendasRepository.emitirExtratoVendas(nomeProduto);
        if (extrato != null) {
            System.out.println("Extrato encontrado:");
            System.out.println(extrato.toString()); // Req 3
        } else {
            System.out.println("Nenhuma venda registrada com esse nome.");
        }
    }

    // Ação 4.1: Demonstrar Contadores de Serviços
    private static void demonstrarContadoresServicos() throws Exception {
        System.out.println("--- Teste Contadores Estáticos (Serviços) ---");
        System.out.println("Contagem ANTES de criar novo serviço:");
        // (Questão 12 e 13)
        System.out.println("(Req 12 - Private) Instâncias: " + Servicos.pegarContadorInstanciasPrivado());
        System.out.println("(Req 13 - Protected) Instâncias: " + Servicos.pegarContadorInstanciasProtegido());

        System.out.println("\nCriando um novo serviço 'Teste'...");
        // (Questão 11)
        servicesRoles.salvarNovoServico("Serviço Teste", 99.9, "Serviço de teste para contadores");

        System.out.println("\nContagem DEPOIS de criar novo serviço:");
        System.out.println("(Req 12 - Private) Instâncias: " + Servicos.pegarContadorInstanciasPrivado());
        System.out.println("(Req 13 - Protected) Instâncias: " + Servicos.pegarContadorInstanciasProtegido());
    }

    // Ação 4.2: Demonstrar Contadores de OS
    private static void demonstrarContadoresOS() throws Exception {
        System.out.println("--- Teste Contador Estático (Ordens de Serviço) ---");
        System.out.println("Contagem ANTES de criar nova OS:");
        // (Questão 15)
        System.out.println("Instâncias: " + OrdensDeServicoModel.pegarContadorInstancias());

        System.out.println("\nRegistrando uma nova OS de teste (Ação 2.3)...");
        registrarNovaOS(); // Reutiliza a função de registrar OS

        System.out.println("\nContagem DEPOIS de criar nova OS:");
        System.out.println("Instâncias: " + OrdensDeServicoModel.pegarContadorInstancias());
    }

    // Ação 4.3: Explicar Contadores
    private static void explicarContadores() {
        System.out.println("--- Explicação (Vantagens/Desvantagens Contadores) ---");
        // (Questão 14)
        System.out.println("Estratégia 1: 'private static' com Métodos get/set (Req 12)");
        System.out.println("  - Vantagem: Encapsulamento. A classe tem controle total sobre como a variável é lida ou modificada.");
        System.out.println("  - Desvantagem: Verbosidade. Exige métodos (get/set) para uma tarefa simples.");

        System.out.println("\nEstratégia 2: 'protected static' (Req 13)");
        System.out.println("  - Vantagem: Acesso direto para classes no mesmo pacote ou subclasses.");
        System.out.println("  - Desvantagem: Quebra do Encapsulamento. Qualquer classe no pacote ou subclasse pode modificar o contador (ex: Servicos.contador = 0), causando inconsistência.");
    }


    // --- MÉTODOS UTILITÁRIOS ---

    /**
     * Pausa a execução e aguarda o usuário pressionar ENTER.
     */
    private static void pausar() {
        System.out.println("\n(Pressione ENTER para continuar...)");
        scanner.nextLine();
    }

    /**
     * Salva todos os dados de todos os repositórios no JSON.
     */
    private static void salvarTudo() {
        System.out.println("Salvando todos os dados nos arquivos JSON...");
        // (Questão 17 e 18)
        try {
            usuarioRepository.salvarNoJson();
            servicosRepository.salvarNoJson();
            osRepository.salvarNoJson();
            agendamentoRepository.salvarNoJson();
            vendasRepository.salvarNoJson();
            produtosRepository.salvarNoJson();
            System.out.println("Dados salvos com sucesso.");
        } catch (Exception e) {
            System.err.println("!!! ERRO AO SALVAR DADOS: " + e.getMessage());
        }
    }
}