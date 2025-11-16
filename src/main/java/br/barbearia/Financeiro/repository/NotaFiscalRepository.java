package br.barbearia.Financeiro.repository;

import br.barbearia.Financeiro.model.NotaFiscalModel;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciamento (CRUD) de NotasFiscais.
 * Segue o padrão do projeto, usando GerenciadorJSON para persistência.
 */
public class NotaFiscalRepository {

    private GerenciadorJSON<NotaFiscalModel> gerenciadorJSON;
    private List<NotaFiscalModel> listaDeNotasFiscais;

    /**
     * Construtor do repositório.
     *
     * @param caminhoDoArquivo O caminho para o arquivo JSON (ex: "BarbeariaComMaven/NotasFiscais.JSON").
     */
    public NotaFiscalRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<NotaFiscalModel>>() {});
        this.listaDeNotasFiscais = this.gerenciadorJSON.carregar();
    }

    /**
     * Salva a lista em memória de volta para o arquivo JSON.
     */
    public void salvarNoJson() {
        gerenciadorJSON.salvar(this.listaDeNotasFiscais);
    }

    /**
     * Encontra o próximo ID disponível.
     */
    private int proximoId() {
        int maxId = 0;
        for (NotaFiscalModel nota : listaDeNotasFiscais) {
            if (nota.getId() > maxId) {
                maxId = nota.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Adiciona uma nova nota fiscal à lista em memória.
     * (Não salva no JSON automaticamente).
     *
     * @param novaNota O objeto NotaFiscalModel a ser adicionado.
     */
    public void adicionarNotaFiscal(NotaFiscalModel novaNota) {
        if (novaNota.getId() == 0) {
            novaNota.setId(proximoId());
            listaDeNotasFiscais.add(novaNota);
        }
    }

    /**
     * Busca todas as notas fiscais associadas a um CPF de cliente.
     *
     * @param cpf O CPF do cliente.
     * @return Uma lista de NotaFiscalModel.
     */
    public List<NotaFiscalModel> buscarPorCpfCliente(String cpf) {
        List<NotaFiscalModel> encontradas = new ArrayList<>();
        for (NotaFiscalModel nota : listaDeNotasFiscais) {
            if (nota.getCpfCliente() != null && nota.getCpfCliente().equals(cpf)) {
                encontradas.add(nota);
            }
        }
        return encontradas;
    }
}