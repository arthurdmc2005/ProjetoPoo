package br.barbearia.OrdensDeServiço;

import br.barbearia.model.RegistroPonto;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdensDeServicoRepository {

    private GerenciadorJSON<OrdensDeServicoModel> gerenciadorJSON;

    private List<OrdensDeServicoModel> ordensDeServicoLista;

    public OrdensDeServicoRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<OrdensDeServicoModel>>() {
        });

        this.ordensDeServicoLista = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.ordensDeServicoLista));
    }

    private int proximoId() {
        int maxId = 0;

        for (OrdensDeServicoModel os : ordensDeServicoLista) {
            if (os.getOrdemId() > maxId) {
                maxId = os.getOrdemId();
            }
        }
        return maxId + 1;
    }
    public void registrarOrdemDeServico(OrdensDeServicoModel os){
        if(os.getOrdemId()==0){
            os.setOrdemId(proximoId());
            ordensDeServicoLista.add(os);
        }
    }

    public List<OrdensDeServicoModel> buscarOSPeloCpf(String cpfBuscado){

        List<OrdensDeServicoModel> ordensEncontradas = new ArrayList<>();

        for(OrdensDeServicoModel ordem : ordensDeServicoLista){
            if(ordem.getClienteCpf() != null && ordem.getClienteCpf().equalsIgnoreCase(cpfBuscado)){
                ordensEncontradas.add(ordem);
            }
        }
        return ordensEncontradas;
    }

    public List<OrdensDeServicoModel> buscarOSPelaData(String dataBuscada){

        List<OrdensDeServicoModel> ordensEnconstrada = new ArrayList<>();

        for(OrdensDeServicoModel ordem : ordensDeServicoLista){
            if(ordem.getDataDoServico().equals(dataBuscada)){
                ordensEnconstrada.add(ordem);
            }
        }
        return ordensEnconstrada;
    }

    public int contadorDeOrdensDeServico(){
        if(ordensDeServicoLista == null){
            return 0;
        }
        return ordensDeServicoLista.size();
    }

    }

