package dashboard;

import eu.hansolo.tilesfx.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import players.Player;
import teams.Team;

import java.io.IOException;

public class TileModel {
    TileTeamController tileTeamController;
    TilePlayerController tilePlayerController;

    public VBox showTeam(Team team) {
        try {
            System.out.println("Got here");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("tileFXML.fxml"));
            Object tile = fxmlLoader.load();
            tileTeamController = fxmlLoader.getController();
            tileTeamController.updateTeam(team);
            return (VBox) tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public VBox showPlayer(Player player) {
        try {
            System.out.println("Got here");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("tilePlayer.fxml"));
            Object tile = fxmlLoader.load();
            tilePlayerController = fxmlLoader.getController();
            tilePlayerController.updatePlayer(player);
            return (VBox) tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
