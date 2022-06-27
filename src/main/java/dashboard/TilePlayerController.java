package dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import players.Player;

public class TilePlayerController {
    @FXML
    Label playerName;

    @FXML
    Label goals;

    @FXML
    Label country;

    public void updatePlayer(Player player) {
        playerName.setText(player.name);
        goals.setText("Goals: " + String.valueOf(player.goals_scored));
        country.setText("Country: " + player.nationality);
    }
}
