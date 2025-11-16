package br.barbearia.Financeiro.service;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.model.Usuarios;
import br.barbearia.repository.UsuarioRepository;
import java.time.LocalDate;

/**
 * Classe de serviço (Service Layer) responsável pelas regras de negócio
 * relacionadas ao {@link RegistroDeVendas}.
 * <p>
 * Esta classe abstrai a lógica de validação para registrar uma nova venda,
 * verificando a existência do usuário e do produto antes de passar
 * os dados para o {@link RegistroDeVendasRepository}.
 * </p>
 *
 * @see RegistroDeVendas
 * @see RegistroDeVendasRepository
 * @see UsuarioRepository
 */
public class RegistroDeVendasServices {

    /** Repositório para acesso aos dados de registros de vendas. */
    private RegistroDeVendasRepository registroDeVendasRepository;

    /** Repositório para acesso aos dados de usuários (para validar o cliente). */
    private UsuarioRepository usuarioRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência dos repositórios necessários.
     *
     * @param registroDeVendasRepository A instância do repositório
     * de vendas a ser usada.
     * @param usuarioRepository A instância do repositório de usuários
     * a ser usada.
     */
    public RegistroDeVendasServices(RegistroDeVendasRepository registroDeVendasRepository, UsuarioRepository usuarioRepository){
        this.registroDeVendasRepository = registroDeVendasRepository;
        this.usuarioRepository = usuarioRepository;
    }


    /**
     * Valida e registra uma nova venda de produto.
     * <p>
     * Validações executadas:
     * 1. Verifica se o {@code usuarioId} (cliente) existe.
     * 2. Verifica se o produto com {@code nomeProduto} existe no estoque
     * disponível (consultado do repositório de vendas).
     * </p>
     * <p>
     * Se as validações passarem, cria um novo objeto {@link RegistroDeVendas}
     * e o envia para o {@link RegistroDeVendasRepository} para ser
     * processado e salvo (incluindo o débito do estoque).
     * </p>
     *
     * @param nomeProduto O nome do produto vendido (case-insensitive).
     * @param dataSaida A data em que a venda ocorreu.
     * @param usuarioId O ID do {@link Usuarios} (cliente) que comprou.
     * @param valor O valor total da transação.
     * @param quantidadeVendida A quantidade de itens vendidos.
     * @return O objeto {@link RegistroDeVendas} recém-criado.
     * @throws Exception Se o usuário ou o produto não forem encontrados.
     */
    public RegistroDeVendas registrarVenda(String nomeProduto, LocalDate dataSaida, int usuarioId, double valor, double quantidadeVendida) throws Exception {

        // 1. Validar Usuário
        Usuarios usuario = usuarioRepository.buscarPorId(usuarioId);
        if (usuario == null) {
            throw new Exception("Usuário com ID " + usuarioId + " não encontrado.");
        }

        // 2. Validar Produto
        Produtos produtoEncontrado = null;
        for (Produtos produto : registroDeVendasRepository.produtosDisponiveis) {
            if (produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                produtoEncontrado = produto;
                break;
            }
        }

        if (produtoEncontrado == null) {
            throw new Exception("Produto '" + nomeProduto + "' não encontrado.");
        }

        // 3. Criar o objeto de Venda
        RegistroDeVendas novaVenda = new RegistroDeVendas();
        novaVenda.setNomeProduto(produtoEncontrado.getNomeProduto());
        novaVenda.setSaida(dataSaida);
        novaVenda.setClienteId(usuario.getId());
        novaVenda.setValor(valor);
        novaVenda.setQuantidadeVendida(quantidadeVendida);

        // 4. Enviar para o Repositório processar
        registroDeVendasRepository.registrarVenda(novaVenda);

        System.out.println("Venda registrada com sucesso para o produto " + nomeProduto);

        return novaVenda;
    }
}