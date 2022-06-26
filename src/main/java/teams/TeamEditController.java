package teams;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TeamEditController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cleanSheetsBox;

    @FXML
    private Button commitButton;

    @FXML
    private TextField drawsBox;

    @FXML
    private TextField goalDiffBox;

    @FXML
    private TextField goalsAgainstBox;

    @FXML
    private TextField goalsForBox;

    @FXML
    private TextField lossesBox;

    @FXML
    private TextField matchesPldbox;

    @FXML
    private TextField positionBox;

    @FXML
    private TextField teamNamebox;

    @FXML
    private TextField winsBox;

    Team team;

    public void load(Team inTeam){
        this.team = inTeam;
        teamNamebox.setText(team.name);
        positionBox.setText(String.valueOf(team.position));
        matchesPldbox.setText(String.valueOf(team.matchesPlayed));
        winsBox.setText(String.valueOf(team.wins));
        lossesBox.setText(String.valueOf(team.losses));
        drawsBox.setText(String.valueOf(team.draws));
        goalDiffBox.setText(String.valueOf(team.goalDiff));
        goalsForBox.setText(String.valueOf(team.goalsScored));
        goalsAgainstBox.setText(String.valueOf(team.goalsConceded));
        cleanSheetsBox.setText(String.valueOf(team.cleanSheet));
    }
    public void change(){
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String stmt  = "UPDATE teams SET team_name = '" + teamNamebox.getText() +
                    "'  , league_position = '" + positionBox.getText() +
                    "' , matches_played = '" +matchesPldbox.getText()+
                    "' , wins = '" + winsBox.getText() +
                    "' , draws = '" + drawsBox.getText() +
                    "' , losses = '" + lossesBox.getText() +
                    "' , goals_scored = '" + goalsForBox.getText() +
                    "' , goals_conceded = '" + goalsAgainstBox.getText()+
                    "' , goal_difference = '" + goalDiffBox.getText()+
                    "' , clean_sheets = '" +cleanSheetsBox.getText()+
                    "' WHERE team_name = '" +team.name+ "'  ;";
            PreparedStatement prep = con.prepareStatement(stmt);
            prep.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            quit();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void quit() throws IOException {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

}
