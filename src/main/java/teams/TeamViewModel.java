package teams;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamViewModel {
    public void show(Team team){
        teamViewController = new TeamViewController();
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("teamViewFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            teamViewController = loader.getController();
            teamViewController.load(team);
//            teamViewController.loadCards();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    TeamViewController teamViewController;

}
