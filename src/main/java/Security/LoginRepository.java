package Security;

import br.barbearia.model.Usuarios;
import br.barbearia.repository.GerenciadorJSON;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class LoginRepository {

    private GerenciadorJSON<LoginModel> gerenciadorJSON;

    private List<LoginModel> listaDeLogins;

    public LoginRepository(String caminhoDoArquivo) {
        this.gerenciadorJSON = new GerenciadorJSON<>(caminhoDoArquivo, new TypeReference<List<LoginModel>>() {
        });

        this.listaDeLogins = this.gerenciadorJSON.carregar();
    }

    public void salvarNoJson(){
        gerenciadorJSON.salvar((this.listaDeLogins));
    }
    private int proximoId() {
        int maxId = 0;

        for (LoginModel loginModel : listaDeLogins) {
            if (loginModel.getId() > maxId) {
                maxId = loginModel.getId();
            }
        }
        return maxId + 1;
    }

    public void registrarLogin(LoginModel novoLogin){
       if(novoLogin.getId()==0){
           novoLogin.setId(proximoId());
           listaDeLogins.add(novoLogin);
       }
       salvarNoJson();

    }
    public List<LoginModel> buscarPelosLogins(){
        return new ArrayList<>(this.listaDeLogins);
    }


}
