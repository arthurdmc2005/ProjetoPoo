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
import br.barbearia.agendamento.Memento.AgendamentoCaraTaker;
import br.barbearia.agendamento.model.Agendamento;
import br.barbearia.agendamento.model.ComparateDateAgendamento;
import br.barbearia.agendamento.model.Estacao;
import br.barbearia.agendamento.model.Servicos;
import br.barbearia.agendamento.repository.AgendamentoRepository;
import br.barbearia.agendamento.repository.EstacaoRepository;
import br.barbearia.agendamento.repository.ServicosRepository;
import br.barbearia.agendamento.service.AgendamentoServices;
import br.barbearia.agendamento.service.ServicesRoles;
import br.barbearia.model.CompareNameCliente;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import br.barbearia.service.BalanceService;
import br.barbearia.service.UsuarioServices;


import br.barbearia.Financeiro.model.NotaFiscalModel;
import br.barbearia.Financeiro.repository.NotaFiscalRepository;
import br.barbearia.Financeiro.service.NotaFiscalService;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal (main) do Sistema de Barbearia.
 * <p>
 * Funciona como um menu de console (CLI) para interagir com todas as
 * funcionalidades do sistema, como gestão de pessoas, agendamentos,
 * vendas, finanças e testes de requisitos (Questões 16, 17, etc.).
 * </p>
 * (Atualizado com NotaFiscal)
 *
 * @see br.barbearia.service.UsuarioServices
 * @see br.barbearia.agendamento.service.AgendamentoServices
 * @see br.barbearia.OrdensDeServiço.OrdensDeServicoRoles
 * @see br.barbearia.Financeiro.service.NotaFiscalService
 */
public class Barbearia {

    /** Caminho para o arquivo JSON de Usuários. */
    private static final String PATH_USUARIOS = "BarbeariaComMaven/Usuarios.JSON";
    /** Caminho para o arquivo JSON de Serviços. */
    private static final String PATH_SERVICOS = "BarbeariaComMaven/Servicos.JSON";
    /** Caminho para o arquivo JSON de Ordens de Serviço. */
    private static final String PATH_ORDENS = "BarbeariaComMaven/OrdensDeServico.JSON";
    /** Caminho para o arquivo JSON de Agendamentos. */
    private static final String PATH_AGENDAMENTOS = "BarbeariaComMaven/Agendamento.JSON";
    /** Caminho para o arquivo JSON de Vendas de Produtos. */
    private static final String PATH_VENDAS = "BarbeariaComMaven/Vendas.JSON";
    /** Caminho para o arquivo JSON de Produtos (Estoque). */
    private static final String PATH_PRODUTOS = "BarbeariaComMaven/Produtos.JSON";
    /** Caminho para o arquivo JSON de Notas Fiscais. */
    private static final String PATH_NOTAS_FISCAIS = "BarbeariaComMaven/NotasFiscais.JSON";

    /** Instância global do Scanner para leitura do console. */
    private static final Scanner scanner = new Scanner(System.in);

    /** Formatador padrão para datas (dd/MM/yyyy). */
    private static final DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /** Formatador padrão para horas (HH:mm). */
    private static final DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");

    //--- Repositórios ---
    /** Repositório para acesso aos dados de Usuários. */
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository(PATH_USUARIOS);
    /** Repositório para acesso aos dados de Serviços. */
    private static final ServicosRepository servicosRepository = new ServicosRepository(PATH_SERVICOS);
    /** Repositório para acesso aos dados de Ordens de Serviço. */
    private static final OrdensDeServicoRepository osRepository = new OrdensDeServicoRepository(PATH_ORDENS);
    /** Repositório para acesso aos dados de Agendamentos. */
    private static final AgendamentoRepository agendamentoRepository = new AgendamentoRepository(PATH_AGENDAMENTOS);
    /** Repositório para acesso aos dados das Estações de Atendimento. */
    private static final EstacaoRepository estacaoRepository = new EstacaoRepository();
    /** Repositório para acesso aos dados de Produtos (Estoque). */
    private static final ProdutosRepository produtosRepository = new ProdutosRepository(PATH_PRODUTOS);
    /** Repositório para acesso aos dados de Vendas. */
    private static final RegistroDeVendasRepository vendasRepository = new RegistroDeVendasRepository(PATH_VENDAS);
    /** Repositório para acesso aos dados de Notas Fiscais. */
    private static final NotaFiscalRepository notaFiscalRepository = new NotaFiscalRepository(PATH_NOTAS_FISCAIS);
    /** Instância do Caretaker (Zelador) do padrão Memento para Agendamentos. */
    private static final AgendamentoCaraTaker caraTaker = new AgendamentoCaraTaker();

    //--- Serviços ---
    /** Serviço para regras de negócio de Usuários. */
    private static final UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);
    /** Serviço para regras de negócio de Serviços. */
    private static final ServicesRoles servicesRoles = new ServicesRoles(servicosRepository);
    /** Serviço para regras de negócio de Produtos (Estoque). */
    private static final ProdutosService produtosService = new ProdutosService(produtosRepository);
    /** Serviço para regras de negócio de Vendas. */
    private static final RegistroDeVendasServices vendasServices = new RegistroDeVendasServices(vendasRepository, usuarioRepository);
    /** Serviço para regras de negócio de Balanço Financeiro. */
    private static final BalanceService balanceService = new BalanceService(osRepository);
    /** Serviço para regras de negócio de Notas Fiscais. */
    private static final NotaFiscalService notaFiscalService = new NotaFiscalService(notaFiscalRepository, usuarioRepository);

    //--- Serviços com Dependências ---
    /** Serviço para regras de negócio de Agendamentos. */
    private static final AgendamentoServices agendamentoServices = new AgendamentoServices(agendamentoRepository, usuarioRepository, servicosRepository, estacaoRepository, caraTaker);
    /** Serviço para regras de negócio de Ordens de Serviço (injeta o serviço de NF). */
    private static final OrdensDeServicoRoles osRoles = new OrdensDeServicoRoles(osRepository, notaFiscalService);


    /**
     * Ponto de entrada principal do aplicativo (main).
     * <p>
     * Exibe o menu principal em loop até que o usuário escolha a opção "0" para sair.
     * </p>
     * @param args Argumentos de linha de comando (não utilizados).
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


    /**
     * Imprime o menu principal no console.
     */
    private static void printMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gestão de Pessoas (Clientes/Colaboradores)");
        System.out.println("2. Agendamentos e Serviços");
        System.out.println("3. Loja e Estoque");
        System.out.println("4. Painel Administrativo (Financeiro/Testes)");
        System.out.println("----------------------");
        System.out.println("0. Salvar e Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Exibe o submenu "Gestão de Pessoas".
     * Trata exceções de sub-métodos.
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
     * Exibe o submenu "Agendamentos e Serviços".
     * Trata exceções de sub-métodos.
     */
    private static void exibirMenuAgendamentos() {
        while (true) {
            System.out.println("\n--- 2. Agendamentos e Serviços ---");
            System.out.println("1. Criar Novo Agendamento");
            System.out.println("2. Finalizar Agendamento");
            System.out.println("3. Listar Estações de Atendimento");
            System.out.println("4. Listar Agendamentos (Ordenado por Data)");
            System.out.println("5. Registrar Nova Ordem de Serviço (Gera NF automática)");
            System.out.println("6. Consultar Ordens de Serviço (por CPF do Cliente)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        criarNovoAgendamento();
                        break;
                    case "2":
                        finalizarAgendamento();
                        break;
                    case "3":
                        listarEstacoes();
                        break;
                    case "4":
                        listarAgendamentosOrdenados();
                        break;
                    case "5":
                        registrarNovaOS();
                        break;
                    case "6":
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
     * Exibe o submenu "Loja e Estoque".
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

    /**
     * Exibe o submenu "Gestão de Vendas".
     * Trata exceções de sub-métodos.
     */
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
     * Exibe o submenu "Gestão de Estoque".
     * Trata exceções de sub-métodos.
     */
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
     * Exibe o submenu "Painel Administrativo".
     * Trata exceções de sub-métodos.
     */
    private static void exibirMenuAdmin() {
        while (true) {
            System.out.println("\n--- 4. Painel Administrativo (Financeiro/Testes) ---");
            System.out.println("1. Gerar Balanço Mensal (Receita de OS)");
            System.out.println("2. Consultar Notas Fiscais (por CPF do Cliente)");
            System.out.println("--- Testes de Requisitos ---");
            System.out.println("3. Testar Contadores Estáticos (Serviços)");
            System.out.println("4. Testar Contadores Estáticos (Ordens de Serviço)");
            System.out.println("5. Ver Explicação (Vantagens/Desvantagens Contadores)");
            System.out.println("6. Testar Comparator.sort() (Questão 16)");
            System.out.println("7. Testar Find vs. BinarySearch (Questão 17)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            try {
                switch (op) {
                    case "1":
                        consultarBalancoMensal();
                        break;
                    case "2":
                        consultarNotasFiscaisPorCpf();
                        break;
                    case "3":
                        demonstrarContadoresServicos();
                        break;
                    case "4":
                        demonstrarContadoresOS();
                        break;
                    case "5":
                        explicarContadores();
                        break;
                    case "6":
                        demonstrarQuestao16();
                        break;
                    case "7":
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


    /**
     * Ação do Menu 1: Coleta dados e cadastra um novo usuário do tipo "Cliente".
     * @throws Exception Se o cadastro falhar (ex: CPF duplicado).
     */
    private static void cadastrarNovoCliente() throws Exception {
        System.out.println("--- Cadastrar Novo Cliente ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF (só números): "); String cpf = scanner.nextLine();
        System.out.print("Telefone (só números): "); String tel = scanner.nextLine();
        String tipo = "Cliente";

        Usuarios novo = usuarioServices.cadastrarUsuario(nome, cpf, tel, tipo, "123", cpf);
        System.out.println("Cliente cadastrado com sucesso! ID: " + novo.getId());
    }

    /**
     * Ação do Menu 1: Coleta CPF e remove um usuário.
     * @throws Exception Se a remoção falhar.
     */
    private static void excluirCliente() throws Exception {
        System.out.println("--- Excluir Cliente ---");
        System.out.print("CPF do cliente para excluir: "); String cpf = scanner.nextLine();
        usuarioServices.removerUsuarioPeloCpf(cpf);
        System.out.println("Cliente removido com sucesso (se existia).");
    }

    /**
     * Ação do Menu 1: Coleta dados e cadastra um novo usuário do tipo "Funcionario".
     * @throws Exception Se o cadastro falhar.
     */
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

    /**
     * Ação do Menu 1: Lista todos os usuários do tipo "Cliente", ordenados por nome.
     * Utiliza o {@link CompareNameCliente}.
     */
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

    /**
     * Ação do Menu 2: Coleta dados e cria um novo agendamento.
     * Mostra horários disponíveis antes de solicitar a hora.
     * @throws Exception Se o agendamento falhar (ex: horário ocupado).
     */
    private static void criarNovoAgendamento() throws Exception {
        System.out.println("--- Criar Novo Agendamento ---");
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), dtfData);

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

    /**
     * Ação do Menu 2: Coleta o ID de um agendamento e o marca como "Finalizado".
     * Libera a estação de atendimento associada.
     * @throws Exception Se o agendamento não for encontrado.
     */
    private static void finalizarAgendamento() throws Exception {
        System.out.println("--- Finalizar Agendamento ---");
        System.out.print("Digite o ID do agendamento a ser finalizado: ");
        int id = Integer.parseInt(scanner.nextLine());
        agendamentoServices.finalizarAgendamento(id);
        System.out.println("Agendamento ID " + id + " finalizado. Estação liberada.");
    }

    /**
     * Ação do Menu 2: Lista todas as estações de atendimento e seus status.
     */
    private static void listarEstacoes() {
        System.out.println("--- Estações de Atendimento (Array Fixo) ---");
        List<Estacao> estacoes = estacaoRepository.listarTodas();
        for (Estacao estacao : estacoes) {
            System.out.println("  - " + estacao.toString());
        }
        System.out.println("Total de estações fixas: " + estacoes.size());
    }

    /**
     * Ação do Menu 2: Lista todos os agendamentos, ordenados por data (proximidade).
     * Utiliza o {@link ComparateDateAgendamento}.
     */
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

    /**
     * Ação do Menu 2: Coleta dados e registra uma nova Ordem de Serviço.
     * A geração da Nota Fiscal é disparada automaticamente.
     * @throws Exception Se o registro da OS falhar.
     */
    private static void registrarNovaOS() throws Exception {
        System.out.println("--- Registrar Nova Ordem de Serviço ---");
        System.out.print("CPF do Cliente: "); String cpfCliente = scanner.nextLine();
        System.out.print("CPF do Funcionário: "); String cpfFunc = scanner.nextLine();
        System.out.print("ID do Serviço (ex: 1): "); int servId = Integer.parseInt(scanner.nextLine());
        System.out.print("Data (dd/MM/yyyy): "); String data = scanner.nextLine();
        System.out.print("Diagnóstico do Serviço: "); String diag = scanner.nextLine();
        System.out.print("Valor (ex: 50.0): "); double valor = Double.parseDouble(scanner.nextLine());

        osRoles.registrarOrdensDeServico(cpfCliente, cpfFunc, diag, valor, servId, data);

        System.out.println("Ordem de Serviço registrada e Nota Fiscal gerada com sucesso.");
    }

    /**
     * Ação do Menu 2: Coleta um CPF e busca todas as Ordens de Serviço associadas.
     * @throws Exception Se a busca falhar.
     */
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

    /**
     * Ação do Menu 3: Coleta dados e registra uma nova venda de produto.
     * @throws Exception Se a venda falhar (ex: usuário não encontrado).
     */
    private static void registrarVendaProduto() throws Exception {
        System.out.println("--- Registrar Venda de Produto ---");
        System.out.print("Nome do Produto: "); String nomeProd = scanner.nextLine();
        System.out.print("ID do Cliente: "); int clienteId = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor da Venda: "); double valor = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantidade Vendida: "); double qtd = Double.parseDouble(scanner.nextLine());

        vendasServices.registrarVenda(nomeProd, java.time.LocalDate.now(), clienteId, valor, qtd);
        System.out.println("Venda registrada com sucesso.");
    }

    /**
     * Ação do Menu 3: Coleta o nome de um produto e busca o primeiro registro de venda.
     */
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

    /**
     * Ação do Menu 3: Coleta dados e cadastra um novo produto no estoque.
     * @throws Exception Se a validação do produto falhar.
     */
    private static void cadastrarProdutoNoEstoque() throws Exception {
        System.out.println("--- Cadastrar Produto no Estoque ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        System.out.print("Fornecedor: "); String fornecedor = scanner.nextLine();
        System.out.print("Quantidade Inicial: "); double qtd = Double.parseDouble(scanner.nextLine());
        System.out.print("Valor de Venda (ex: 25.50): "); double valor = Double.parseDouble(scanner.nextLine());

        produtosService.cadastrarProdutoNoEstoque(nome, qtd, fornecedor, valor);
        System.out.println("Produto cadastrado no estoque.");
    }

    /**
     * Ação do Menu 3: Lista todos os produtos cadastrados no estoque.
     * @throws Exception Se não houver produtos.
     */
    private static void listarProdutos() throws Exception {
        System.out.println("--- Listar Todos os Produtos ---");
        List<Produtos> produtos = produtosService.listarProdutos();
        for (Produtos p : produtos) {
            System.out.println(" - " + p.toString());
        }
    }

    /**
     * Ação do Menu 3: Coleta um nome e busca um produto no estoque.
     * @throws Exception Se o produto não for encontrado.
     */
    private static void buscarProdutoPorNome() throws Exception {
        System.out.println("--- Buscar Produto por Nome ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        Produtos p = produtosService.buscarProdutoPorNome(nome);
        System.out.println("Produto encontrado: " + p.toString());
    }

    /**
     * Ação do Menu 3: Coleta nome e quantidade para adicionar ao estoque.
     * @throws Exception Se a validação falhar.
     */
    private static void adicionarProdutoNoEstoque() throws Exception {
        System.out.println("--- Adicionar Quantidade ao Estoque ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        System.out.print("Quantidade a Adicionar: "); double qtd = Double.parseDouble(scanner.nextLine());
        produtosService.adicionarProdutoNoEstoque(nome, qtd);
        System.out.println("Estoque atualizado.");
    }

    /**
     * Ação do Menu 3: Coleta nome e quantidade para remover do estoque.
     * @throws Exception Se a validação falhar.
     */
    private static void removerProdutoDoEstoque() throws Exception {
        System.out.println("--- Remover Quantidade do Estoque ---");
        System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
        System.out.print("Quantidade a Remover: "); double qtd = Double.parseDouble(scanner.nextLine());
        produtosService.removerProdutoDoEstoque(nome, qtd);
        System.out.println("Estoque atualizado.");
    }

    /**
     * Ação do Menu 4: Coleta mês/ano e calcula o balanço de receita das Ordens de Serviço.
     */
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

    /**
     * Ação do Menu 4: Coleta um CPF e busca todas as Notas Fiscais associadas.
     * @throws Exception Se a busca falhar (ex: CPF inválido).
     */
    private static void consultarNotasFiscaisPorCpf() throws Exception {
        System.out.println("--- Consultar Notas Fiscais por Cliente ---");
        System.out.print("Digite o CPF do cliente (só números): ");
        String cpf = scanner.nextLine();

        System.out.println("Buscando notas fiscais...");
        List<NotaFiscalModel> notas = notaFiscalService.buscarNotasPorCpf(cpf);

        if (notas.isEmpty()) {
            System.out.println("Nenhuma nota fiscal encontrada para este CPF.");
        } else {
            System.out.println("Encontradas " + notas.size() + " nota(s):");
            for(NotaFiscalModel nota : notas) {
                System.out.println(" - " + nota.toString());
            }
        }
    }

    /**
     * Ação do Menu 4: Demonstração dos contadores estáticos (Requisitos 12, 13)
     * na classe {@link Servicos}.
     * @throws Exception Se a criação/remoção do serviço de teste falhar.
     */
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

    /**
     * Ação do Menu 4: Demonstração do contador estático (Requisito 15)
     * na classe {@link OrdensDeServicoModel}.
     * @throws Exception (Propagado de registrarNovaOS, embora este método não trate).
     */
    private static void demonstrarContadoresOS() throws Exception {
        System.out.println("--- Teste Contador Estático (Ordens de Serviço) ---");
        System.out.println("Contagem ANTES de criar nova OS:");
        System.out.println("Instâncias: " + OrdensDeServicoModel.pegarContadorInstancias());

        System.out.println("\nRegistrando uma nova OS de teste (Ação 2.3)...");
        OrdensDeServicoModel osTeste = new OrdensDeServicoModel(1, "11111111111", "22222222222", 1.0, "teste", 0, "15/11/2025");

        System.out.println("\nContagem DEPOIS de criar nova OS:");
        System.out.println("Instâncias: " + OrdensDeServicoModel.pegarContadorInstancias());
    }

    /**
     * Ação do Menu 4: Imprime uma explicação sobre os modificadores de acesso
     * 'private static' vs 'protected static' (Requisitos 12, 13).
     */
    private static void explicarContadores() {
        System.out.println("--- Explicação (Vantagens/Desvantagens Contadores) ---");
        System.out.println("Estratégia 1: 'private static' com Métodos get/set (Req 12)");
        System.out.println("  - Vantagem: Encapsulamento. A classe tem controle total sobre como a variável é lida ou modificada.");
        System.out.println("  - Desvantagem: Verbosidade. Exige métodos (get/set) para uma tarefa simples.");

        System.out.println("\nEstratégia 2: 'protected static' (Req 13)");
        System.out.println("  - Vantagem: Acesso direto para classes no mesmo pacote ou subclasses.");
        System.out.println("  - Desvantagem: Quebra do Encapsulamento. Qualquer classe no pacote ou subclasse pode modificar o contador (ex: Servicos.contador = 0), causando inconsistência.");
    }

    /**
     * Ação do Menu 4: Demonstração do {@link Comparator} (Questão 16).
     * Ordena e exibe a lista de usuários de duas formas: ascendente e descendente.
     */
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

    /**
     * Método de busca (Questão 17) que usa {@link Iterator} e {@link Comparator}.
     * Realiza uma busca linear O(n).
     *
     * @param lista A lista de usuários para pesquisar.
     * @param chaveDeBusca Um objeto {@link Usuarios} com o nome a ser procurado.
     * @param comparator O {@link CompareNameCliente} para fazer a comparação.
     * @return O usuário encontrado, ou {@code null} se não for encontrado.
     */
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

    /**
     * Ação do Menu 4: Demonstração da Questão 17.
     * Compara o método {@link #findUsuarioComIterator} (busca linear)
     * com o {@link Collections#binarySearch(List, Object, Comparator)} (busca binária).
     * <p>
     * Cria e remove um usuário de teste para garantir que a busca funcione.
     * </p>
     */
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


    /**
     * Método utilitário para pausar a execução do console
     * e aguardar o usuário pressionar ENTER.
     */
    private static void pausar() {
        System.out.println("\n(Pressione ENTER para continuar...)");
        scanner.nextLine();
    }

    /**
     * Método utilitário para persistir os dados de TODOS os repositórios.
     * Chama o método {@code salvarNoJson()} de cada repositório.
     */
    private static void salvarTudo() {
        System.out.println("Salvando todos os dados nos arquivos JSON...");
        try {
            usuarioRepository.salvarNoJson();
            servicosRepository.salvarNoJson();
            osRepository.salvarNoJson();
            agendamentoRepository.salvarNoJson();
            vendasRepository.salvarNoJson();
            produtosRepository.salvarNoJson();
            notaFiscalRepository.salvarNoJson(); // ADICIONADO
            System.out.println("Dados salvos com sucesso.");
        } catch (Exception e) {
            System.err.println("!!! ERRO AO SALVAR DADOS: " + e.getMessage());
        }
    }

    public Barbearia(){
    }
}