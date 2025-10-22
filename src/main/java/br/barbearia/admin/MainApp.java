package br.barbearia.admin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;


public class MainApp extends Application {

    @Override
    public void start(Stage palco){

        palco.setTitle("Barbearia");

        // --- 1. Crie os componentes do formulário (seu GridPane) ---
        Label rotuloNome = new Label("Nome");
        Label rotuloCPF = new Label("CPF");
        Label rotuloTelefone = new Label("Telefone");

        TextField campoNome = new TextField();
        TextField campoCPF = new TextField();
        TextField campoTelefone = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.addRow(0,rotuloNome,campoNome);
        gridPane.addRow(1,rotuloTelefone,campoTelefone);
        gridPane.addRow(2,rotuloCPF,campoCPF);


        // --- 2. Crie os componentes dos botões (seu HBox) ---
        Button botaoLogin = new Button("Logar");
        Button botaoCadastrar = new Button("Cadastrar");

        HBox hBox = new HBox(10, botaoCadastrar, botaoLogin); // Adiciona espaçamento de 10px entre os botões
        hBox.setAlignment(Pos.CENTER); // Centraliza os botões dentro do HBox


        // --- 3. Crie o Contêiner Raiz (VBox) ---
        // Este VBox será o nó raiz e conterá TODOS os outros elementos
        VBox rootContainer = new VBox(20); // Espaçamento de 20px entre os filhos (gridPane e hBox)
        rootContainer.setPadding(new Insets(15)); // Padding geral da janela
        rootContainer.setAlignment(Pos.CENTER); // Centraliza tudo (o grid e o hbox) verticalmente e horizontalmente

        // Adiciona seus layouts ao contêiner raiz
        rootContainer.getChildren().addAll(gridPane, hBox);


        // --- 4. Crie a Scene com o Contêiner Raiz ---
        Scene scene = new Scene(rootContainer, 500, 400); // Agora o rootContainer é o nó raiz
        palco.setScene(scene);
        palco.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}