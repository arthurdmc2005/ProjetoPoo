/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.barbearia.admin;



import br.barbearia.model.Cliente;
import br.barbearia.model.Funcionario;
import br.barbearia.repository.ClientesRepository;
import br.barbearia.repository.FuncionariosRepository;
import br.barbearia.service.ClienteService;
import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Sistema  {

    public static void main(String[] args) {
        ClientesRepository meuRepo = new ClientesRepository();
        ClientesRepository meuService = new ClienteService(meuRepo);


    }



    }


    
}
