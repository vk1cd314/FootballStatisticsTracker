package leagues;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LeagueAdd {
    LeagueAddController leagueAddController = new LeagueAddController();

    public void show(BorderPane borderPane) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("addLeagueFXML.fxml"));
            borderPane.setCenter(root.load());
            leagueAddController = root.getController();
            leagueAddController.loadBox();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
