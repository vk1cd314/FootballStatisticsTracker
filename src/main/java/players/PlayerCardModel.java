package players;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PlayerCardModel {
    PlayerCardController playerCardController;

    public HBox show(Player player) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("playerCard.fxml"));
            Object tile = fxmlLoader.load();
            playerCardController = fxmlLoader.getController();
            playerCardController.setData(player);
            System.out.println(player.name);
            return (HBox) tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
