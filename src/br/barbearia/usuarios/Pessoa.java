package br.barbearia.usuarios;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String endereco;

    public Pessoa(String nome,String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    public Pessoa(){}
    
    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
       

    //Getters
    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEndereco(){
        return endereco;
    }


    public void setNome(String nome){
        if (this.nome != null){
            this.nome = nome.replaceAll("[[^\\\\p{L}\\\\s]]", "");
        }else {
            this.nome = null;
        }
    }

    public void setCpf(String cpf){
        if (cpf != null){
            this.cpf = cpf.replaceAll("[^0-9]", "");
        }else{
            this.cpf = null;
        }

    }

    public void setTelefone(String telefone){
        if (telefone != null){
            this.telefone = telefone.replaceAll("^0-9", "");
        }else{
            this.telefone = null;
        }
    }

    public void setEndereco(String endereco){
        if(endereco != null){
            this.endereco = endereco.replaceAll("[^\\\\p{L}0-9\\\\s,.-]", "" );
        }else {
            this.endereco = null;
        }
    }
}