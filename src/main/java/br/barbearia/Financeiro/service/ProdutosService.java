package br.barbearia.Financeiro.service;

import br.barbearia.Financeiro.model.Produtos;
import br.barbearia.Financeiro.repository.ProdutosRepository;

import java.util.List;

/**
 * Classe de serviço (Service Layer) responsável pelas regras de negócio
 * relacionadas aos {@link Produtos} (Estoque).
 * <p>
 * Esta classe abstrai a lógica de validação para criar, atualizar,
 * buscar e listar produtos, comunicando-se com o {@link ProdutosRepository}
 * para a persistência dos dados.
 * </p>
 *
 * @see Produtos
 * @see ProdutosRepository
 */
public class ProdutosService {

    /**
     * O repositório que gerencia a persistência dos dados de produtos.
     */
    private ProdutosRepository produtosRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência do {@link ProdutosRepository}.
     *
     * @param produtosRepository A instância do repositório
     * a ser usada por este serviço.
     */
    public ProdutosService(ProdutosRepository produtosRepository){
        this.produtosRepository = produtosRepository;
    }

    /**
     * Método de conveniência para cadastrar um produto no estoque
     * passando os parâmetros individualmente.
     * <p>
     * Este método cria um novo objeto {@link Produtos} e o repassa
     * para o método {@link #cadastrarProdutoNoEstoque(Produtos)}.
     * </p>
     *
     * @param nomeDoProduto O nome do produto.
     * @param quantidadeDoProduto A quantidade inicial.
     * @param fornecedor O fornecedor do produto.
     * @param valor O preço de venda do produto.
     * @return O objeto {@link Produtos} recém-criado e salvo.
     * @throws Exception Se a validação no método sobrecarregado falhar.
     */
    public Produtos cadastrarProdutoNoEstoque(String nomeDoProduto, double quantidadeDoProduto, String fornecedor, double valor)throws Exception{
        Produtos produtosParaCadastrar = new Produtos();
        produtosParaCadastrar.setNomeProduto(nomeDoProduto);
        produtosParaCadastrar.setFornecedor(fornecedor);
        produtosParaCadastrar.setQuantidade(quantidadeDoProduto);
        produtosParaCadastrar.setValor(valor);

        return this.cadastrarProdutoNoEstoque(produtosParaCadastrar);

    }

    /**
     * Valida e cadastra um novo produto no estoque.
     * <p>
     * Realiza diversas validações (nome, fornecedor, quantidade, valor)
     * antes de enviar o objeto para o repositório.
     * </p>
     *
     * @param novoProduto O objeto {@link Produtos} a ser validado e salvo.
     * @return O {@code novoProduto} após ser salvo no repositório.
     * @throws Exception Se qualquer regra de validação falhar
     * (ex: nome vazio, valor negativo).
     */
    public Produtos cadastrarProdutoNoEstoque(Produtos novoProduto)throws Exception{
        if(novoProduto.getNomeProduto()==null || novoProduto.getNomeProduto().trim().isEmpty()){
            throw new Exception("O nome do produto não pode estar vazio");
        }
        if(novoProduto.getFornecedor()==null || novoProduto.getFornecedor().trim().isEmpty()){
            throw new Exception("Informe o fornecedor. Caso ele não exista digite NENHUM");
        }
        if(novoProduto.getQuantidade()<0){
            throw new Exception("A quantidade não pode ser maior que 0");
        }
        if(novoProduto.getQuantidade()>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        if(novoProduto.getValor()<0){
            throw new Exception("O valor do produto não pode ser negativo");
        }
        if(novoProduto.getValor()==0){
            throw new Exception("O valor do produto não pode ser 0");
        }
        System.out.println("Validado com sucesso");
        produtosRepository.adicionarProdutoAoEstoque(novoProduto);
        return novoProduto;

    }

    /**
     * Remove (subtrai) uma quantidade do estoque de um produto.
     *
     * @param produtoAlterado O nome do produto a ter o estoque alterado.
     * @param quantidadeParaRemover A quantidade a ser subtraída.
     * @throws Exception Se o nome do produto for nulo/vazio ou
     * se a quantidade for inválida (negativa ou > 100).
     */
    public void removerProdutoDoEstoque(String produtoAlterado, double quantidadeParaRemover)throws Exception{
        if(produtoAlterado == null || produtoAlterado.trim().isEmpty()){
            throw new Exception("Não existe produto no estoque");
        }
        if(quantidadeParaRemover>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        if(quantidadeParaRemover<0){
            throw new Exception("O numero para remover não pode ser negativo");
        }
        System.out.println("Validado com sucesso");
        produtosRepository.removerQuantidadeDoProduto(produtoAlterado,quantidadeParaRemover);
    }


    /**
     * Adiciona (soma) uma quantidade ao estoque de um produto.
     *
     * @param produtoAlterado O nome do produto a ter o estoque alterado.
     * @param quantidadeParaAdicionar A quantidade a ser somada.
     * @throws Exception Se o nome do produto for nulo/vazio ou
     * se a quantidade for inválida (negativa ou > 100).
     */
    public void adicionarProdutoNoEstoque(String produtoAlterado, double quantidadeParaAdicionar)throws Exception{
        if(produtoAlterado == null || produtoAlterado.trim().isEmpty()){
            throw new Exception("Coloque o nome do produto a ser alterado");
        }
        if(quantidadeParaAdicionar<0){
            throw new Exception("A quantidade para ser adicionada tem que ser maior que 0");
        }
        if(quantidadeParaAdicionar>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        produtosRepository.adicionarQuantidadeDoProduto(produtoAlterado,quantidadeParaAdicionar);
        System.out.println("Validado com sucesso");
    }


    /**
     * Busca um produto no estoque pelo seu nome (case-insensitive).
     *
     * @param nomeDoProduto O nome do produto a ser buscado.
     * @return O objeto {@link Produtos} encontrado.
     * @throws Exception Se o nome for nulo/vazio ou se o produto
     * não for encontrado no repositório.
     */
    public Produtos buscarProdutoPorNome(String nomeDoProduto)throws Exception{
        if(nomeDoProduto==null || nomeDoProduto.trim().isEmpty()){
            throw new Exception("O produto buscado não pode ser vazio");
        }
        Produtos produtoBuscado = produtosRepository.buscarProdutoPorNome(nomeDoProduto);

        if(produtoBuscado==null){
            throw new Exception("Usuário não encontrado");
        }
        System.out.println("Validado com sucesso");
        return produtoBuscado;
    }

    /**
     * Retorna a lista completa de produtos no estoque.
     *
     * @return Uma {@link List} de {@link Produtos}.
     * @throws Exception Se a lista estiver vazia ou se algum produto
     * na lista tiver dados inválidos (nome vazio ou quantidade negativa).
     */
    public List<Produtos> listarProdutos() throws Exception {
        List<Produtos> produtos = produtosRepository.listarProdutos();

        if (produtos == null || produtos.isEmpty()) {
            throw new Exception("Nenhum produto cadastrado no estoque.");
        }

        for (Produtos produto : produtos) {
            if (produto.getNomeProduto() == null || produto.getNomeProduto().trim().isEmpty()) {
                throw new Exception("Um dos produtos cadastrados está com o nome inválido.");
            }
            if (produto.getQuantidade() < 0) {
                throw new Exception("Produto com quantidade negativa encontrado: " + produto.getNomeProduto());
            }
        }

        return produtos;
    }

    /**
     * Atualiza todos os dados de um produto no repositório.
     *
     * @param novoProduto O objeto {@link Produtos} com os dados
     * atualizados (deve conter o ID do produto original).
     * @throws Exception Se o objeto for nulo ou se algum de seus
     * campos (quantidade, nome, fornecedor) falhar na validação.
     */
    public void atualizarProdutoCompleto(Produtos novoProduto)throws Exception{
        if(novoProduto==null){
            throw new Exception("Produto inválido");
        }
        if(novoProduto.getQuantidade()<0){
            throw new Exception("A quantidade não pode ser menor que 0");
        }
        if(novoProduto.getQuantidade()>100){
            throw new Exception("A quantidade não pode ser maior que 100");
        }
        if(novoProduto.getNomeProduto()==null || novoProduto.getNomeProduto().trim().isEmpty()){
            throw new Exception("Coloque o nome do produto");
        }
        if(novoProduto.getFornecedor()==null || novoProduto.getFornecedor().trim().isEmpty()){
            throw new Exception("Coloque o nome do fornecedor");
        }
        produtosRepository.atualizarProdutoNoEstoque(novoProduto);
    }
}