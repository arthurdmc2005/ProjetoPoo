package br.barbearia.OrdensDeServiço;

import br.barbearia.repository.UsuarioRepository;
import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.List;

public class OrdensDeServicoRoles {

    private OrdensDeServicoRepository ordensDeServicoRepository;

    public OrdensDeServicoRoles(OrdensDeServicoRepository ordensDeServicoRepository){
        this.ordensDeServicoRepository = ordensDeServicoRepository;
    }

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


    public List<OrdensDeServicoModel> buscarOSPelaData(String dataBuscada)throws Exception{
       if(dataBuscada == null || dataBuscada.isBlank()){
       throw new Exception("A data não pode ser nula ou vazia");
       }
       if(!dataBuscada.matches("\\d{2}/\\d{2}/\\d{4}")){
           throw new Exception("Formato de data inválido");
       }
       return ordensDeServicoRepository.buscarOSPelaData(dataBuscada);
    }

    public int contadorDeOrdensDeServico()throws Exception{
        int total = ordensDeServicoRepository.contadorDeOrdensDeServico();

        if(total == 0){
            throw new Exception("Não há ordens de serviço cadastradas");
        }
        return total;

    }
    }
