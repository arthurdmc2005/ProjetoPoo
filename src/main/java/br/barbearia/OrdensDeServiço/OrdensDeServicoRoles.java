package br.barbearia.OrdensDeServiço;

import br.barbearia.repository.UsuarioRepository;
import net.bytebuddy.asm.Advice; // Esta importação parece não ser utilizada

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviço (Service Layer) responsável pelas regras de negócio
 * relacionadas às {@link OrdensDeServicoModel}.
 * <p>
 * Esta classe abstrai a lógica de validação para registrar e buscar
 * Ordens de Serviço, comunicando-se com o {@link OrdensDeServicoRepository}
 * para a persistência dos dados.
 * </p>
 *
 * @see OrdensDeServicoModel
 * @see OrdensDeServicoRepository
 */
public class OrdensDeServicoRoles {

    /** O repositório que gerencia a persistência dos dados de Ordens de Serviço. */
    private OrdensDeServicoRepository ordensDeServicoRepository;

    /**
     * Construtor da classe de serviço.
     * Realiza a injeção de dependência do {@link OrdensDeServicoRepository}.
     *
     * @param ordensDeServicoRepository A instância do repositório
     * a ser usada por este serviço.
     */
    public OrdensDeServicoRoles(OrdensDeServicoRepository ordensDeServicoRepository){
        this.ordensDeServicoRepository = ordensDeServicoRepository;
    }

    /**
     * Método de conveniência para registrar uma nova Ordem de Serviço
     * passando os parâmetros individualmente.
     * <p>
     * Este método cria um novo objeto {@link OrdensDeServicoModel} e o repassa
     * para o método {@link #registrarOrdensDeServico(OrdensDeServicoModel)}.
     * </p>
     *
     * @param clienteCpf O CPF do cliente.
     * @param funcionarioCpf O CPF do funcionário.
     * @param diagnosticoServico Notas sobre o serviço.
     * @param valorGasto O valor cobrado.
     * @param servicoId O ID do serviço principal.
     * @param dataDoServico A data da realização do serviço (ex: "dd/MM/yyyy").
     * @return O objeto {@link OrdensDeServicoModel} recém-criado e salvo.
     * @throws Exception Se a validação no método sobrecarregado falhar.
     */
    public OrdensDeServicoModel registrarOrdensDeServico(String clienteCpf, String funcionarioCpf, String diagnosticoServico, double valorGasto, int servicoId,String dataDoServico)throws Exception{
        OrdensDeServicoModel ordemDeServicoParaRegistrar = new OrdensDeServicoModel();
        ordemDeServicoParaRegistrar.setClienteCpf(clienteCpf);
        ordemDeServicoParaRegistrar.setFuncionarioCpf(funcionarioCpf);
        ordemDeServicoParaRegistrar.setDiagnosticoServico(diagnosticoServico);
        ordemDeServicoParaRegistrar.setValorGasto(valorGasto);
        ordemDeServicoParaRegistrar.setServicoId(servicoId);
        ordemDeServicoParaRegistrar.setDataDoServico(dataDoServico);

        return this.registrarOrdensDeServico(ordemDeServicoParaRegistrar);
    }

    /**
     * Valida e registra uma nova Ordem de Serviço.
     * <p>
     * Realiza diversas validações (ID de serviço, CPF do cliente, CPF do
     * funcionário, valor gasto, diagnóstico) antes de enviar o objeto
     * para o repositório.
     * </p>
     *
     * @param novaOrdemDeServico O objeto {@link OrdensDeServicoModel} a ser
     * validado e salvo.
     * @return A {@code novaOrdemDeServico} após ser salva no repositório.
     * @throws Exception Se qualquer regra de validação falhar
     * (ex: CPF vazio, valor nulo, etc.).
     */
    public OrdensDeServicoModel registrarOrdensDeServico(OrdensDeServicoModel novaOrdemDeServico)throws Exception{
        if(novaOrdemDeServico.getServicoId()== 0){
            throw new Exception("O id de serviço não pode ser nulo");
        }
        if(novaOrdemDeServico.getClienteCpf().trim().isEmpty()){
            throw new Exception("O cpf não pode ser vazio");
        }
        if(novaOrdemDeServico.getClienteCpf() == null && novaOrdemDeServico.getClienteCpf().replaceAll("[^0-9]","").length()!=11){
            throw new Exception("Verifique o atributo cpf");
        }
        if(novaOrdemDeServico.getFuncionarioCpf() == null && novaOrdemDeServico.getFuncionarioCpf().replaceAll("[^0-9]","").length()!=11){
            throw new Exception("Verifique o atributo funcionario");
        }
        if(novaOrdemDeServico.getValorGasto() == 0 && novaOrdemDeServico.getValorGasto() < 0){
            throw new Exception("O valor gasto não pode ser nulo");
        }
        if(novaOrdemDeServico.getDiagnosticoServico() == null){
            throw new Exception("Coloque um diagnostico para o serviço");
        }
        ordensDeServicoRepository.registrarOrdemDeServico(novaOrdemDeServico);
        return novaOrdemDeServico;
    }

    /**
     * Busca todas as Ordens de Serviço associadas a um CPF de cliente.
     *
     * @param cpfbuscado O CPF do cliente (deve ter 11 dígitos).
     * @return Uma {@link List} de {@link OrdensDeServicoModel} encontradas.
     * @throws Exception Se o CPF for nulo, vazio ou não tiver 11 dígitos.
     */
    public List<OrdensDeServicoModel> buscarOSPeloCpf(String cpfbuscado)throws Exception{
        if(cpfbuscado == null || cpfbuscado.trim().isEmpty()){
            throw new Exception("O cpfbuscado não pode ser nulo");
        }
        if(cpfbuscado.replaceAll("[^0-9]","").length()!= 11){
            throw new Exception("O formato digitado está incorreto");
        }

        List<OrdensDeServicoModel> listaPorCpf = ordensDeServicoRepository.buscarOSPeloCpf(cpfbuscado);
        return listaPorCpf;

    }


    /**
     * Busca todas as Ordens de Serviço pela data.
     *
     * @param dataBuscada A data no formato "dd/MM/yyyy".
     * @return Uma {@link List} de {@link OrdensDeServicoModel} encontradas.
     * @throws Exception Se a data for nula, vazia ou não estiver
     * no formato "dd/MM/yyyy".
     */
    public List<OrdensDeServicoModel> buscarOSPelaData(String dataBuscada)throws Exception{
        if(dataBuscada == null || dataBuscada.isBlank()){
            throw new Exception("A data não pode ser nula ou vazia");
        }
        if(!dataBuscada.matches("\\d{2}/\\d{2}/\\d{4}")){
            throw new Exception("Formato de data inválido");
        }
        return ordensDeServicoRepository.buscarOSPelaData(dataBuscada);
    }

    /**
     * Retorna a contagem total de Ordens de Serviço registradas.
     *
     * @return O número total de Ordens de Serviço.
     * @throws Exception Se o total for 0 (nenhuma OS cadastrada).
     */
    public int contadorDeOrdensDeServico()throws Exception{
        int total = ordensDeServicoRepository.contadorDeOrdensDeServico();

        if(total == 0){
            throw new Exception("Não há ordens de serviço cadastradas");
        }
        return total;

    }
}