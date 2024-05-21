package application;


// bibliotecas necessaria para executar o codigo
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;




import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class Controller {
	
	
	
	
	
	@FXML
    private void aoClicarBotaoProximaTela(ActionEvent evento) {
        try {
            // Carrega a nova interface do arquivo FXML
            Parent raiz = FXMLLoader.load(getClass().getResource("second.fxml"));
            Scene cena = new Scene(raiz);
            
            // Obtém o palco atual e configura com a nova cena
            Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            palco.setScene(cena);
            palco.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	

    // Blocos pequenos
    @FXML private ImageView blocoPequeno1;
    @FXML private ImageView blocoPequeno2;
    @FXML private ImageView blocoPequeno3;
    @FXML private ImageView blocoPequeno4;
    @FXML private ImageView blocoPequeno12;
    @FXML private ImageView blocoPequeno13;
    @FXML private ImageView blocoPequeno22;
    @FXML private ImageView blocoPequeno23;
    @FXML private ImageView blocoPequeno32;
    @FXML private ImageView blocoPequeno42;
    @FXML private ImageView blocoPequeno43;

    // Lixeiras
    @FXML private ImageView lixeira1;
    @FXML private ImageView lixeira2;
    @FXML private ImageView lixeira3;
    @FXML private ImageView lixeira4;

    // texto para exibir a pontuação
    @FXML private Label pontuacaoLabel;

    // Variável para armazenar a pontuação
    private int pontuacao = 0;

    // Método chamado quando o FXML é carregado (da movimento aos objeto)
    @FXML
    private void initialize() {
        // Inicializar eventos de arrastar os blocos pequenos (bloco pequeno são os lixo que serão arrastado até a lixeira)
        configurarEventosDrag(blocoPequeno1, "lixeira1");
        configurarEventosDrag(blocoPequeno2, "lixeira2");
        configurarEventosDrag(blocoPequeno3, "lixeira3");
        configurarEventosDrag(blocoPequeno4, "lixeira4");
        configurarEventosDrag(blocoPequeno12, "lixeira1");
        configurarEventosDrag(blocoPequeno13, "lixeira1");
        configurarEventosDrag(blocoPequeno22, "lixeira2");
        configurarEventosDrag(blocoPequeno23, "lixeira2");
        configurarEventosDrag(blocoPequeno32, "lixeira3");
        configurarEventosDrag(blocoPequeno42, "lixeira4");
        configurarEventosDrag(blocoPequeno43, "lixeira4");

        // Definindo eventos da para as lixeiras
        configurarEventosDrop(lixeira1);
        configurarEventosDrop(lixeira2);
        configurarEventosDrop(lixeira3);
        configurarEventosDrop(lixeira4);
    }

    // Configura os eventos de drag para um bloco
    private void configurarEventosDrag(ImageView bloco, String lixeiraCorrespondente) {
        bloco.setOnDragDetected(event -> {
            Dragboard db = bloco.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(bloco.getId() + ";" + lixeiraCorrespondente); // Inclui o ID do bloco e a lixeira correspondente
            db.setContent(content);
            event.consume();
        });

        bloco.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                // Remove o bloco pequeno da cena
                bloco.setVisible(false);
            }
            event.consume();
        });
    }

    // Configura os eventos de drop para uma lixeira
    private void configurarEventosDrop(ImageView lixeira) {
        lixeira.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });

        lixeira.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean sucesso = false;

            // Verifica se o bloco está sendo arrastado para a lixeira correta
            if (db.hasString()) {
                String[] blocoInfo = db.getString().split(";");
                String lixeiraCorrespondente = blocoInfo[1];

                if (lixeira.getId().equals(lixeiraCorrespondente)) {
                    sucesso = true;
                    pontuacao++; // Incrementa a pontuação
                    atualizarPontuacao(); // Atualiza a exibição da pontuação
                }
            }

            mostrarMensagemResultado(sucesso);

            event.setDropCompleted(sucesso);
            event.consume();
        });
    }

    // Exibe uma mensagem dependendo do sucesso da operação
    private void mostrarMensagemResultado(boolean sucesso) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Resultado");
        alerta.setHeaderText(sucesso ? "Sucesso!" : "Errado!");
        alerta.setContentText(sucesso ? "Você acertou!" : "Você errou!");
        alerta.showAndWait();
    }

    // Atualiza a exibição da pontuação
    private void atualizarPontuacao() {
        pontuacaoLabel.setText("Pontuação: " + pontuacao);
    }
}
