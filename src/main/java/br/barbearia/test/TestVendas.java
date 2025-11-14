package br.barbearia.test;

import br.barbearia.Financeiro.model.RegistroDeVendas;
import br.barbearia.Financeiro.repository.RegistroDeVendasRepository;
import br.barbearia.Financeiro.service.RegistroDeVendasServices;
import br.barbearia.repository.UsuarioRepository;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class TestVendas {



    public static void main(String[] args) {

        RegistroDeVendasRepository registroDeVendasRepository = new RegistroDeVendasRepository("BarbeariaComMaven/Vendas.JSON");
        UsuarioRepository usuarioRepository = new UsuarioRepository("BarbeariaComMaven/Usuarios.JSON");
        RegistroDeVendasServices registroDeVendasServices = new RegistroDeVendasServices(registroDeVendasRepository,usuarioRepository);
        try {
            registroDeVendasServices.registrarVenda("Minox", LocalDate.of(2019, 10, 10), 2, 30, 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
