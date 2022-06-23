package teams;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamViewController {
    @FXML
    private Button cross;

    @FXML
    private Label draws;

    @FXML
    private Label leaguePosition;

    @FXML
    private Label losses;

    @FXML
    private Label matchesPlayed;

    @FXML
    private VBox playerListContainer;

    @FXML
    private Label points;

    @FXML
    private Label goalDiff;

    @FXML
    private Label goalsConc;

    @FXML
    private Label goalsScored;
    @FXML
    private Label teamName;

    @FXML
    private Label wins;

    @FXML
    private Label cleanSheets;

    Team team;
    public void load(){
        teamName.setText(team.name);
        leaguePosition.setText(String.valueOf(team.position));
        matchesPlayed.setText(String.valueOf(team.matchesPlayed));
        wins.setText(String.valueOf(team.wins));
        draws.setText(String.valueOf(team.draws));
        losses.setText(String.valueOf(team.losses));
        cleanSheets.setText(String.valueOf(team.cleanSheet));
        goalsScored.setText(String.valueOf(team.goalsScored));
        goalsConc.setText(String.valueOf(team.goalsConceded));
        goalDiff.setText(String.valueOf(team.goalsConceded));
    }
    public void teamViewStart(BorderPane borderPane, Team inTeam){
        this.team = inTeam;
        try{
            FXMLLoader root = new FXMLLoader(getClass().getResource("teamViewFXML.fxml"));
            borderPane.setCenter(root.load());
            load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }

}
