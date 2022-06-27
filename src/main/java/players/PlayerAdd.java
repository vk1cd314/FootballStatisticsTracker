package players;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PlayerAdd {
    PlayerAddController playerAddController = new PlayerAddController();

    public void show(BorderPane borderPane) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("playerAddFXML.fxml"));
            borderPane.setCenter(root.load());
            playerAddController = root.getController();
            playerAddController.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
