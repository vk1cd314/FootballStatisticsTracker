package players;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerEdit {
    PlayerEditController playerEditController = new PlayerEditController();

    public void show(Player player) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PlayerEditFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            playerEditController = loader.getController();
            playerEditController.load(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
