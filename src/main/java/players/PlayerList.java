package players;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PlayerList {
    PlayerListController playerListController = new PlayerListController();
    public void show(BorderPane borderPane){
        try{
            FXMLLoader root = new FXMLLoader(getClass().getResource("playerList.fxml"));
            borderPane.setCenter(root.load());
            playerListController = root.getController();
            playerListController.loadPlayerData();
            playerListController.loadCards();
            playerListController.loadFilters();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
