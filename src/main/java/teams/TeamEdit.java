package teams;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamEdit {
    TeamEditController teamEditController;
    public void show(Team team){
        teamEditController = new TeamEditController();
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("teamEditFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            teamEditController = loader.getController();
            teamEditController.load(team);
            //teamName.setText("pls");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
