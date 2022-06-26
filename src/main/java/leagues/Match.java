package leagues;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Match {
    MatchController matchController = new MatchController();;
    public void show(String league){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("matchFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            matchController = loader.getController();
            matchController.load(league);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
