package br.barbearia.test;


import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.Financeiro.repository.ProdutosRepository;
import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.Financeiro.service.ProdutosService;
import br.barbearia.Financeiro.service.RegistroDeVendasServices;
import br.barbearia.OrdensDeServiço.OrdensDeServicoModel;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRepository;
import br.barbearia.OrdensDeServiço.OrdensDeServicoRoles;
import br.barbearia.agendamento.Memento.AgendamentoCaraTaker; // Import adicionado
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateDateAgendamento;
import br.barbearia.agendamento.model.Estacao;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.AgendamentoServices; // Import adicionado
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.CompareNameCliente;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.BalanceService;
import br.barbearia.service.UsuarioServices;


import java.time.LocalDate; // Import adicionado
import java.time.LocalTime; // Import adicionado
import java.time.format.DateTimeFormatter; // Import adicionado
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Menu principal do Sistema de Barbearia, focado nas funcionalidades.
 * (Corrigido para incluir a criação de agendamentos)
 */
public class Barbearia {

    private static final String PATH_USUARIOS = "BarbeariaComMaven/Usuarios.JSON";
    private static final String PATH_SERVICOS = "BarbeariaComMaven/Servicos.JSON";
    private static final String PATH_ORDENS = "BarbeariaComMaven/OrdensDeServico.JSON";
    private static final String PATH_AGENDAMENTOS = "BarbeariaComMaven/Agendamento.JSON";
    private static final String PATH_VENDAS = "BarbeariaComMaven/Vendas.JSON";
    private static final String PATH_PRODUTOS = "BarbeariaComMaven/Produtos.JSON";

    private static final Scanner scanner = new Scanner(System.in);

    // Formatadores de data/hora
    private static final DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");

    // Repositórios
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository(PATH_USUARIOS);
    private static final ServicosRepository servicosRepository = new ServicosRepository(PATH_SERVICOS);
    private static final OrdensDeServicoRepository osRepository = new OrdensDeServicoRepository(PATH_ORDENS);
    private static final AgendamentoRepository agendamentoRepository = new AgendamentoRepository(PATH_AGENDAMENTOS);
    private static final EstacaoRepository estacaoRepository = new EstacaoRepository();
    private static final ProdutosRepository produtosRepository = new ProdutosRepository(PATH_PRODUTOS);
    private static final RegistroDeVendasRepository vendasRepository = new RegistroDeVendasRepository(PATH_VENDAS);

    // Memento
    private static final AgendamentoCaraTaker caraTaker = new AgendamentoCaraTaker(); // ADICIONADO

    // Serviços
    private static final UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);
    private static final ServicesRoles servicesRoles = new ServicesRoles(servicosRepository);
    private static final OrdensDeServicoRoles osRoles = new OrdensDeServicoRoles(osRepository);
    private static final ProdutosService produtosService = new ProdutosService(produtosRepository);
    private static final RegistroDeVendasServices vendasServices = new RegistroDeVendasServices(vendasRepository, usuarioRepository);
    private static final BalanceService balanceService = new BalanceService(osRepository);

    // SERVIÇO DE AGENDAMENTO (ESSENCIAL) - ADICIONADO
    private static final AgendamentoServices agendamentoServices = new AgendamentoServices(agendamentoRepository, usuarioRepository, servicosRepository, estacaoRepository, caraTaker);


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
                    exibirMenuAgendamentos(); // Corrigido
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
     * (MENU CORRIGIDO)
     */
    private static void exibirMenuAgendamentos() {
        while (true) {
            System.out.println("\n--- 2. Agendamentos e Serviços ---");
            System.out.println("1. Criar Novo Agendamento"); // ADICIONADO
            System.out.println("2. Finalizar Agendamento"); // ADICIONADO
            System.out.println("3. Listar Estações de Atendimento"); // Re-numerado
            System.out.println("4. Listar Agendamentos (Ordenado por Data)"); // Re-numerado
            System.out.println("5. Registrar Nova Ordem de Serviço"); // Re-numerado
            System.out.println("6. Consultar Ordens de Serviço (por CPF do Cliente)"); // Re-numerado
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        criarNovoAgendamento(); // ADICIONADO
                        break;
                    case "2":
                        finalizarAgendamento(); // ADICIONADO
                        break;
                    case "3":
                        listarEstacoes(); // Re-numerado
                        break;
                    case "4":
                        listarAgendamentosOrdenados(); // Re-numerado
                        break;
                    case "5":
                        registrarNovaOS(); // Re-numerado
                        break;
                    case "6":
                        consultarOsPorCliente(); // Re-numerado
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
            System.out.println("2. Gestão de Estoque");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    exibirMenuGestaoVendas();
                    break;
                case "2":
                    exibirMenuGestaoEstoque();
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

    private static void exibirMenuGestaoEstoque() {
        while (true) {
            System.out.println("\n--- 3.2 Gestão de Estoque ---");
            System.out.println("1. Cadastrar Produto no Estoque");
            System.out.println("2. Listar Todos os Produtos");
            System.out.println("3. Buscar Produto por Nome");
            System.out.println("4. Adicionar Quantidade ao Estoque");
            System.out.println("5. Remover Quantidade do Estoque");
            System.out.println("9. Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1": cadastrarProdutoNoEstoque(); break;
                    case "2": listarProdutos(); break;
                    case "3": buscarProdutoPorNome(); break;
                    case "4": adicionarProdutoNoEstoque(); break;
                    case "5": removerProdutoDoEstoque(); break;
                    case "9": return;
                    default: System.err.println("Opção inválida.");
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
            System.out.println("1. Gerar Balanço Mensal (Receita de OS)");
            System.out.println("2. Testar Contadores Estáticos (Serviços)");
            System.out.println("3. Testar Contadores Estáticos (Ordens de Serviço)");
            System.out.println("4. Ver Explicação (Vantagens/Desvantagens Contadores)");
            System.out.println("5. Testar Comparator.sort() (Questão 16)");
            System.out.println("6. Testar Find vs. BinarySearch (Questão 17)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        consultarBalancoMensal();
                        break;
                    case "2":
                        demonstrarContadoresServicos();
                        break;
                    case "3":
                        demonstrarContadoresOS();
                        break;
                    case "4":
                        explicarContadores();
                        break;
                    case "5":
                        demonstrarQuestao16();
                        break;
                    case "6":
                        demonstrarQuestao17();
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


    // --- Ações de Pessoas (Menu 1) ---

    private static void cadastrarNovoCliente() throws Exception {
        System.out.println("--- Cadastrar Novo Cliente ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF (só números): "); String cpf = scanner.nextLine();
        System.out.print("Telefone (só números): "); String tel = scanner.nextLine();
        String tipo = "Cliente";

        Usuarios novo = usuarioServices.cadastrarUsuario(nome, cpf, tel, tipo, "123", cpf);
        System.out.println("Cliente cadastrado com sucesso! ID: " + novo.getId());
    }


    private static void excluirCliente() throws Exception {
        System.out.println("--- Excluir Cliente ---");
        System.out.print("CPF do cliente para excluir: "); String cpf = scanner.nextLine();
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
        List<Usuarios> usuarios = usuarioRepository.listaDeUsuarios();
        Comparator<Usuarios> porNome = new CompareNameCliente();
        usuarios.sort(porNome);

        for (Usuarios u : usuarios) {
            if (u.getTipo() != null && u.getTipo().equalsIgnoreCase("Cliente")) {
                System.out.println("  - Nome: " + u.getNome() + ", CPF: " + u.getCpf());
            }
        }
    }

    // --- Ações de Agendamento (Menu 2) ---

    // MÉTODO ADICIONADO
    private static void criarNovoAgendamento() throws Exception {
        System.out.println("--- Criar Novo Agendamento ---");
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), dtfData);

        // Listar horários disponíveis
        List<LocalTime> horarios = agendamentoServices.buscarHorariosDisponiveis(data);
        System.out.println("Horários disponíveis em " + data.format(dtfData) + ":");
        if (horarios.isEmpty()) {
            System.out.println("Nenhum horário disponível para esta data.");
            return;
        }
        for (LocalTime h : horarios) {
            System.out.println(" - " + h.format(dtfHora));
        }

        System.out.print("\nEscolha a Hora (HH:mm): ");
        LocalTime hora = LocalTime.parse(scanner.nextLine(), dtfHora);

        System.out.print("ID do Cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID do Serviço: ");
        int servicoId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID do Funcionário: ");
        int funcId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID da Estação (1, 2 ou 3): ");
        int estacaoId = Integer.parseInt(scanner.nextLine());

        Agendamento novo = agendamentoServices.salvarNovoAgendamento(data, hora, clienteId, servicoId, funcId, estacaoId);
        System.out.println("Agendamento salvo com sucesso! ID: " + novo.getId());
    }

    // MÉTODO ADICIONADO
    private static void finalizarAgendamento() throws Exception {
        System.out.println("--- Finalizar Agendamento ---");
        System.out.print("Digite o ID do agendamento a ser finalizado: ");
        int id = Integer.parseInt(scanner.nextLine());
        agendamentoServices.finalizarAgendamento(id);
        System.out.println("Agendamento ID " + id + " finalizado. Estação liberada.");
    }


    private static void listarEstacoes() {
        System.out.println("--- Estações de Atendimento (Array Fixo) ---");
        List<Estacao> estacoes = estacaoRepository.listarTodas();
        for (Estacao estacao : estacoes) {
            System.out.println("  - " + estacao.toString());
        }
        System.out.println("Total de estações fixas: " + estacoes.size());
    }


    private static void listarAgendamentosOrdenados() {
        System.out.println("--- Agendamentos (Ordenados por Data) ---");
        List<Agendamento> agendamentos = agendamentoRepository.listaDeAgendamentos();
        Comparator<Agendamento> porData = new ComparateDateAgendamento();
        agendamentos.sort(porData);

        for (Agendamento a : agendamentos) {
            System.out.println("  - Data: " + (a.getData() != null ? a.getData().format(dtfData) : "N/A") +
                    ", Hora: " + (a.getHora() != null ? a.getHora().format(dtfHora) : "N/A") +
                    ", ID: " + a.getId() +
                    ", Status: " + a.getStatus());
        }
    }

    private static void registrarNovaOS() throws Exception {
        System.out.println("--- Registrar Nova Ordem de Serviço ---");
        System.out.print("CPF do Cliente: "); String cpfCliente = scanner.nextLine();
        System.out.print("CPF do Funcionário: "); String cpfFunc = scanner.nextLine();
        System.out.print("ID do Serviço (ex: 1): "); int servId = Integer.parseInt(scanner.nextLine());
        System.out.print("Data (dd/MM/yyyy): "); String data = scanner.nextLine();
        System.out.print("Diagnóstico do Serviço: "); String diag = scanner.nextLine();
        System.out.print("Valor (ex: 50.0): "); double valor = Double.parseDouble(scanner.nextLine());

        osRoles.registrarOrdensDeServico(cpfCliente, cpfFunc, diag, valor, servId, data);
        System.out.println("Ordem de Serviço registrada com sucesso.");
    }

    private static void consultarOsPorCliente() throws Exception {
        System.out.println("--- Consultar Ordens de Serviço por Cliente ---");
        System.out.print("Digite o CPF do cliente (só números): ");
        String cpf = scanner.nextLine();

        System.out.println("Buscando ordens...");
        List<OrdensDeServicoModel> ordens = osRoles.buscarOSPeloCpf(cpf);

        if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem encontrada para este CPF.");
        } else {
            for(OrdensDeServicoModel os : ordens) {
                System.out.println(" - " + os.toString());
            }
        }
    }

    // --- Ações de Loja (Menu 3) ---

    private static void registrarVendaProduto() throws Exception {
        System.out.println("--- Registrar Venda de Produto ---");
        System.out.print("Nome do Produto: "); String nomeProd = scanner.nextLine();
        System.out.print("ID do Cliente: "); int clienteId = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor da Venda: "); double valor = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantidade Vendida: "); double qtd = Double.parseDouble(scanner.nextLine());

        vendasServices.registrarVenda(nomeProd, java.time.LocalDate.now(), clienteId, valor, qtd);
        System.out.println("Venda registrada com sucesso.");
    }

    private static void emitirExtratoVenda() {
        System.out.println("--- Emitir Extrato de Venda ---");
        System.out.print("Nome do produto vendido: ");
        String nomeProduto = scanner.nextLine();
        RegistroDeVendas extrato = vendasRepository.emitirExtratoVendas(nomeProduto);
        if (extrato != null) {
            System.out.println("Extrato encontrado:");
            System.out.println(extrato.toString());
        } else {
            System.out.println("Nenhuma venda registrada com esse nome.");
        }
    }

    private static void cadastrarProdutoNoEstoque() throws Exception {
        System.out.println("--- Cadastrar Produto no Estoque ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        System.out.print("Fornecedor: "); String fornecedor = scanner.nextLine();
        System.out.print("Quantidade Inicial: "); double qtd = Double.parseDouble(scanner.nextLine());
        System.out.print("Valor de Venda (ex: 25.50): "); double valor = Double.parseDouble(scanner.nextLine());

        produtosService.cadastrarProdutoNoEstoque(nome, qtd, fornecedor, valor);
        System.out.println("Produto cadastrado no estoque.");
    }

    private static void listarProdutos() throws Exception {
        System.out.println("--- Listar Todos os Produtos ---");
        List<Produtos> produtos = produtosService.listarProdutos();
        for (Produtos p : produtos) {
            System.out.println(" - " + p.toString());
        }
    }

    private static void buscarProdutoPorNome() throws Exception {
        System.out.println("--- Buscar Produto por Nome ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        Produtos p = produtosService.buscarProdutoPorNome(nome);
        System.out.println("Produto encontrado: " + p.toString());
    }

    private static void adicionarProdutoNoEstoque() throws Exception {
        System.out.println("--- Adicionar Quantidade ao Estoque ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        System.out.print("Quantidade a Adicionar: "); double qtd = Double.parseDouble(scanner.nextLine());
        produtosService.adicionarProdutoNoEstoque(nome, qtd);
        System.out.println("Estoque atualizado.");
    }

    private static void removerProdutoDoEstoque() throws Exception {
        System.out.println("--- Remover Quantidade do Estoque ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        System.out.print("Quantidade a Remover: "); double qtd = Double.parseDouble(scanner.nextLine());
        produtosService.removerProdutoDoEstoque(nome, qtd);
        System.out.println("Estoque atualizado.");
    }

    // --- Ações de Admin (Menu 4) ---

    private static void consultarBalancoMensal() {
        System.out.println("--- Consultar Balanço Mensal (Receita de OS) ---");
        System.out.print("Digite o Mês (ex: 11): ");
        int mes = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o Ano (ex: 2025): ");
        int ano = Integer.parseInt(scanner.nextLine());

        double balanco = balanceService.gerarBalancoMensal(mes, ano);

        System.out.println("----------------------------------------");
        System.out.printf("O faturamento total em Ordens de Serviço para %02d/%d foi: R$ %.2f\n", mes, ano, balanco);
        System.out.println("----------------------------------------");
    }

    private static void demonstrarContadoresServicos() throws Exception {
        System.out.println("--- Teste Contadores Estáticos (Serviços) ---");
        System.out.println("Contagem ANTES de criar novo serviço:");
        System.out.println("(Req 12 - Private) Instâncias: " + Servicos.pegarContadorInstanciasPrivado());
        System.out.println("(Req 13 - Protected) Instâncias: " + Servicos.pegarContadorInstanciasProtegido());

        System.out.println("\nCriando um novo serviço 'Teste'...");
        servicesRoles.salvarNovoServico("Serviço Teste", 99.9, "Serviço de teste para contadores");

        System.out.println("\nContagem DEPOIS de criar novo serviço:");
        System.out.println("(Req 12 - Private) Instâncias: " + Servicos.pegarContadorInstanciasPrivado());
        System.out.println("(Req 13 - Protected) Instâncias: " + Servicos.pegarContadorInstanciasProtegido());

        try {
            Servicos s = servicosRepository.buscarPorTipoDeServico("Serviço Teste");
            if (s != null) {
                servicosRepository.removerServico(s.getId());
                System.out.println("(Serviço de teste removido.)");
            }
        } catch(Exception e) {}
    }

    private static void demonstrarContadoresOS() throws Exception {
        System.out.println("--- Teste Contador Estático (Ordens de Serviço) ---");
        System.out.println("Contagem ANTES de criar nova OS:");
        System.out.println("Instâncias: " + OrdensDeServicoModel.pegarContadorInstancias());

        System.out.println("\nRegistrando uma nova OS de teste (Ação 2.3)...");
        OrdensDeServicoModel osTeste = new OrdensDeServicoModel(1, "11111111111", "22222222222", 1.0, "teste", 0, "15/11/2025");

        System.out.println("\nContagem DEPOIS de criar nova OS:");
        System.out.println("Instâncias: " + OrdensDeServicoModel.pegarContadorInstancias());
    }

    private static void explicarContadores() {
        System.out.println("--- Explicação (Vantagens/Desvantagens Contadores) ---");
        System.out.println("Estratégia 1: 'private static' com Métodos get/set (Req 12)");
        System.out.println("  - Vantagem: Encapsulamento. A classe tem controle total sobre como a variável é lida ou modificada.");
        System.out.println("  - Desvantagem: Verbosidade. Exige métodos (get/set) para uma tarefa simples.");

        System.out.println("\nEstratégia 2: 'protected static' (Req 13)");
        System.out.println("  - Vantagem: Acesso direto para classes no mesmo pacote ou subclasses.");
        System.out.println("  - Desvantagem: Quebra do Encapsulamento. Qualquer classe no pacote ou subclasse pode modificar o contador (ex: Servicos.contador = 0), causando inconsistência.");
    }

    // --- MÉTODOS ADICIONADOS (Q16 E Q17) ---

    private static void demonstrarQuestao16() {
        System.out.println("==== Questão 16: Testando Comparator.sort() ====");

        List<Usuarios> listaParaOrdenar = new ArrayList<>(usuarioRepository.listaDeUsuarios());

        if (listaParaOrdenar.isEmpty()) {
            System.out.println("Lista de usuários vazia. Adicione usuários para testar.");
            return;
        }

        System.out.println("\n--- 1. Lista Original (Desordenada) ---");
        for (Usuarios u : listaParaOrdenar) {
            System.out.println("  - " + u.getNome() + " (ID: " + u.getId() + ")");
        }

        System.out.println("\n--- 2. Ordenando por Nome (Ascendente) ---");
        Comparator<Usuarios> porNomeAsc = new CompareNameCliente();
        listaParaOrdenar.sort(porNomeAsc);

        for (Usuarios u : listaParaOrdenar) {
            System.out.println("  - " + u.getNome());
        }

        System.out.println("\n--- 3. Ordenando por Nome (Descendente) ---");
        Comparator<Usuarios> porNomeDesc = porNomeAsc.reversed();
        listaParaOrdenar.sort(porNomeDesc);

        for (Usuarios u : listaParaOrdenar) {
            System.out.println("  - " + u.getNome());
        }
    }

    public static Usuarios findUsuarioComIterator(List<Usuarios> lista, Usuarios chaveDeBusca, Comparator<Usuarios> comparator) {
        Iterator<Usuarios> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Usuarios usuarioAtual = iterator.next();
            if (comparator.compare(usuarioAtual, chaveDeBusca) == 0) {
                return usuarioAtual;
            }
        }
        return null;
    }

    private static void demonstrarQuestao17() {
        System.out.println("\n==== Questão 17: Testando Find (Iterator) vs. BinarySearch ====");

        Comparator<Usuarios> porNome = new CompareNameCliente();

        Usuarios chaveBusca = new Usuarios();
        chaveBusca.setNome("UsuarioDeTesteQ17");
        chaveBusca.setCpf("12345678917");
        chaveBusca.setTelefone("99999");
        chaveBusca.setTipo("Cliente");
        chaveBusca.setLogin("q17");
        chaveBusca.setSenhaHash("123");

        try {
            usuarioServices.cadastrarUsuario(chaveBusca.getNome(), chaveBusca.getCpf(), chaveBusca.getTelefone(), chaveBusca.getTipo(), chaveBusca.getSenhaHash(), chaveBusca.getLogin());
        } catch (Exception e) {
            // Ignora se já existir
        }

        System.out.println("Buscando pelo usuário: '" + chaveBusca.getNome() + "'\n");

        System.out.println("--- 1. Teste 'findUsuarioComIterator' (Busca Linear O(n)) ---");
        long inicioFind = System.nanoTime();
        Usuarios encontradoFind = findUsuarioComIterator(usuarioRepository.listaDeUsuarios(), chaveBusca, porNome);
        long fimFind = System.nanoTime();

        if (encontradoFind != null) {
            System.out.println("Encontrado: " + encontradoFind.getNome() + " (ID: " + encontradoFind.getId() + ")");
        } else {
            System.out.println("Usuário não encontrado pelo 'findUsuarioComIterator'.");
        }
        System.out.println("Tempo (nanos): " + (fimFind - inicioFind));


        System.out.println("\n--- 2. Teste 'Collections.binarySearch' (Busca Binária O(log n)) ---");
        List<Usuarios> listaOrdenada = new ArrayList<>(usuarioRepository.listaDeUsuarios());
        System.out.println("...Ordenando a lista primeiro (requisito do binarySearch)...");
        listaOrdenada.sort(porNome);

        long inicioBinary = System.nanoTime();
        int indice = Collections.binarySearch(listaOrdenada, chaveBusca, porNome);
        long fimBinary = System.nanoTime();

        if (indice >= 0) {
            System.out.println("Encontrado: " + listaOrdenada.get(indice).getNome() + " (ID: " + listaOrdenada.get(indice).getId() + ")");
        } else {
            System.out.println("Usuário não encontrado pelo 'binarySearch'. (Índice: " + indice + ")");
        }
        System.out.println("Tempo (nanos): " + (fimBinary - inicioBinary));

        System.out.println("\n--- Comparação ---");
        System.out.println("O 'find' (Iterator) funciona em listas desordenadas, mas é mais lento (Linear O(n)).");
        System.out.println("O 'binarySearch' é muito mais rápido (Logarítmico O(log n)), mas EXIGE que a lista esteja ordenada.");

        try {
            usuarioServices.removerUsuarioPeloCpf(chaveBusca.getCpf());
            System.out.println("\n(Usuário de teste '"+ chaveBusca.getNome() +"' removido.)");
        } catch (Exception e) {
            System.err.println("Erro ao limpar usuário de teste: " + e.getMessage());
        }
    }


    // --- MÉTODOS UTILITÁRIOS ---

    private static void pausar() {
        System.out.println("\n(Pressione ENTER para continuar...)");
        scanner.nextLine();
    }

    private static void salvarTudo() {
        System.out.println("Salvando todos os dados nos arquivos JSON...");
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