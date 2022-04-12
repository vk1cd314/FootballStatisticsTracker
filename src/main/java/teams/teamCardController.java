package teams;

import leagues.LeagueData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class teamCardController {
    @FXML
    private Label teamName;

    public void setData(LeagueData league) {
        teamName.setText(league.getTeam_name());
    }
}
