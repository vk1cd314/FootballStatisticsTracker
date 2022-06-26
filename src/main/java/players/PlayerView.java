package players;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerView {
    PlayerViewController playerViewController;
    public void show(Player player){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("playerView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            playerViewController = loader.getController();
            playerViewController.load(player);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
