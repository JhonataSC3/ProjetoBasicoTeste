// PROJETO SEGUINDO O PADRÃO RECOMENDADO PELO PROFESSOR 
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
        	Parent root = FXMLLoader.load(getClass().getResource("Second.fxml")); // inicializa O "sample.fxml" que é o arquivo que está a interface grafica
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Jogo de Lixeiras");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
