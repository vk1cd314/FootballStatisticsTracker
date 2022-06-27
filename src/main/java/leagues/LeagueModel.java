package leagues;

import com.football.statisticstracker.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LeagueModel {
    LeagueController leagueController;

    public void show(BorderPane borderPane, Admin admin) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("leagueFXML.fxml"));
            borderPane.setCenter(root.load());
            leagueController = root.getController();
            leagueController.adminCredentials = admin;
            System.out.println("In leagueModel " + admin.name + " " + admin.password);
            leagueController.loadLeagueData();
            leagueController.loadLeagues();
            leagueController.loadCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
