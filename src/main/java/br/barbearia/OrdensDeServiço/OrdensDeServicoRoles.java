package br.barbearia.OrdensDeServiço;

import br.barbearia.repository.UsuarioRepository;

public class OrdensDeServicoRoles {

    private OrdensDeServicoRepository ordensDeServicoRepository;

    public OrdensDeServicoRoles(OrdensDeServicoRepository ordensDeServicoRepository){
        this.ordensDeServicoRepository = ordensDeServicoRepository;
    }

    public OrdensDeServicoModel registrarOrdensDeServico(String clienteCpf, String funcionarioCpf, String diagnosticoServico, double valorGasto, int servicoId)throws Exception{
        OrdensDeServicoModel ordemDeServicoParaRegistrar = new OrdensDeServicoModel();
        ordemDeServicoParaRegistrar.setClienteCpf(clienteCpf);
        ordemDeServicoParaRegistrar.setFuncionarioCpf(funcionarioCpf);
        ordemDeServicoParaRegistrar.setDiagnosticoServico(diagnosticoServico);
        ordemDeServicoParaRegistrar.setValorGasto(valorGasto);
        ordemDeServicoParaRegistrar.setServicoId(servicoId);

        return this.registrarOrdensDeServico(ordemDeServicoParaRegistrar);
    }

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
}
