package br.barbearia.servico;

import br.barbearia.usuarios.Cliente;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class NotaFiscal {
    private int id;
    private LocalDateTime dataemissao;
    private Cliente cliente;
    private List<Servico> servico;
    private double valortotal;
    
    public double calcularTotal() {
        double total = 0.0;
        
        for (Servico servico : servico) {
            total += servico.getPreco();
        }
        this.valortotal = total;
        return total;
    }

    
    public NotaFiscal (int id, LocalDateTime dataemissao, Cliente cliente, List<Servico> servico){
        this.id = id;
        this.dataemissao = dataemissao;
        this.cliente = cliente;
        this.servico = servico != null ? servico : new ArrayList<>();
        this.valortotal = calcularTotal();
    }
    
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public LocalDateTime getDataemissao() { 
        return dataemissao; 
    }
    
    public void setDataemissao(LocalDateTime dataemissao) { 
        this.dataemissao = dataemissao; 
    }

    public Cliente getCliente() { 
        return cliente; 
    }

    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }

    public List<Servico> getServico() { 
        return servico; 
    }

    public void setServico(List<Servico> servico) {
    this.servico = servico != null ? servico : new ArrayList<>();
    calcularTotal();
    }

    public double getValortotal() { 
        return valortotal; 
    }

}
