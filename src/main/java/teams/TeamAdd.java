package teams;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class TeamAdd {
    TeamAddController teamAddController = new TeamAddController();
    public void show(BorderPane borderPane){
        try{

            FXMLLoader root = new FXMLLoader(getClass().getResource("teamAddFXML.fxml"));
            borderPane.setCenter(root.load());
            teamAddController = root.getController();
            teamAddController.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
