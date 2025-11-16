package br.barbearia.Financeiro.service;

import br.barbearia.Financeiro.model.NotaFiscalModel;
import br.barbearia.Financeiro.repository.NotaFiscalRepository;
import br.barbearia.OrdensDeServiço.OrdensDeServicoModel;
import br.barbearia.model.Usuarios;
import br.barbearia.Financeiro.repository.NotaFiscalRepository;
import br.barbearia.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe de serviço (Service Layer) para regras de negócio de NotaFiscal.
 */
public class NotaFiscalService {

    private NotaFiscalRepository notaFiscalRepository;
    private UsuarioRepository usuarioRepository;

    /**
     * Construtor do serviço.
     * @param notaFiscalRepository O repositório de notas fiscais.
     * @param usuarioRepository O repositório de usuários (para buscar dados do cliente).
     */
    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository, UsuarioRepository usuarioRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Gera e salva uma nova Nota Fiscal com base em uma Ordem de Serviço concluída.
     *
     * @param os A Ordem de Serviço que acabou de ser registrada.
     * @throws Exception Se o cliente (baseado no CPF da OS) não for encontrado.
     */
    public void gerarNotaParaOrdem(OrdensDeServicoModel os) throws Exception {
        System.out.println("LOG [NotaFiscalService]: Gerando nota para OS ID " + os.getOrdemId() + "...");

        // 1. Buscar o cliente para pegar o nome
        Usuarios cliente = usuarioRepository.buscarUsuarioPorCpf(os.getClienteCpf());
        if (cliente == null) {
            // Se o sistema permite OS para CPF não cadastrado, podemos tratar
            // throw new Exception("Cliente com CPF " + os.getClienteCpf() + " não encontrado. Nota fiscal não gerada.");
            System.err.println("AVISO: Cliente com CPF " + os.getClienteCpf() + " não cadastrado. Nota fiscal será gerada sem nome.");
        }

        // 2. Criar o objeto NotaFiscalModel
        NotaFiscalModel novaNota = new NotaFiscalModel();
        novaNota.setOrdemDeServicoId(os.getOrdemId());
        novaNota.setDataEmissao(LocalDate.now()); // A nota é emitida agora
        novaNota.setValorTotal(os.getValorGasto());
        novaNota.setCpfCliente(os.getClienteCpf());

        if (cliente != null) {
            novaNota.setNomeCliente(cliente.getNome());
        } else {
            novaNota.setNomeCliente("Cliente Não Cadastrado");
        }

        // 3. Salvar no repositório
        notaFiscalRepository.adicionarNotaFiscal(novaNota);
        System.out.println("LOG [NotaFiscalService]: Nota Fiscal ID " + novaNota.getId() + " gerada com sucesso.");
    }

    /**
     * Busca todas as notas fiscais de um cliente pelo CPF.
     *
     * @param cpf O CPF do cliente.
     * @return Uma lista de notas fiscais.
     * @throws Exception Se o CPF for nulo ou inválido.
     */
    public List<NotaFiscalModel> buscarNotasPorCpf(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new Exception("CPF não pode ser nulo ou vazio.");
        }

        if (cpf.replaceAll("[^0-9]", "").length() != 11) {
            throw new Exception("Formato de CPF inválido. Deve ter 11 dígitos.");
        }

        return notaFiscalRepository.buscarPorCpfCliente(cpf);
    }
}