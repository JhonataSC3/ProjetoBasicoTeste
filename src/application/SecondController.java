package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SecondController {

	@FXML
    private void aoClicarBotaoVoltar(ActionEvent evento) {
        try {
            // Carrega a interface inicial do arquivo FXML
            Parent raiz = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene cena = new Scene(raiz);
            
            // Obtém o palco atual e configura com a cena inicial
            Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            palco.setScene(cena);
            palco.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
