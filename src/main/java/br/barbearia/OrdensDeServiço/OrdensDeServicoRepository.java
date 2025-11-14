package br.barbearia.OrdensDeServiço;

import br.barbearia.model.RegistroPonto;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

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
}