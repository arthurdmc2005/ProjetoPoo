package br.barbearia.admin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import org.w3c.dom.Text;

import java.io.File;


public class MainApp extends Application {

    @Override
    public void start(Stage palco){

        palco.setTitle("Barbearia");


        Label rotuloNome = new Label("Nome");
        Label rotuloCPF = new Label("CPF");
        Label rotuloTelefone = new Label("Telefone");

        TextField campoNome = new TextField();
        TextField campoCPF = new TextField();
        TextField campoTelefone = new TextField();


        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.addRow(0,rotuloNome,campoNome);
        gridPane.addRow(1,rotuloTelefone,campoTelefone);
        gridPane.addRow(2,rotuloCPF,campoCPF);


        String caminhoImagem = "C:/Users/Arthur/Documents/img/imagembarber.jpg";


        Image image = new Image(new File(caminhoImagem).toURI().toString());

        ImageView imageView = new ImageView(image);


        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);


        Button botaoLogin = new Button("Logar");
        Button botaoCadastrar = new Button("Cadastrar");

        HBox hBox = new HBox(10, botaoCadastrar, botaoLogin);
        hBox.setAlignment(Pos.CENTER);



        VBox rootContainer = new VBox(20);
        rootContainer.setPadding(new Insets(15));
        rootContainer.setAlignment(Pos.CENTER);


        rootContainer.setStyle("fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-style: italic; -fx-font-weight: bold; -fx-background-color: black;");



        rootContainer.getChildren().addAll(imageView, gridPane, hBox);



        Scene scene = new Scene(rootContainer, 500, 550);
        palco.setScene(scene);
        palco.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}