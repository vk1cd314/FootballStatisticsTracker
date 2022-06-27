package dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import teams.Team;

public class TileTeamController {

    @FXML
    Label teamName;

    @FXML
    Label wins;

    @FXML
    Label losses;

    public void updateTeam(Team team) {
        teamName.setText(team.name);
        wins.setText("Wins: " + String.valueOf(team.wins));
        losses.setText("Losses: " + String.valueOf(team.losses));
    }
}
