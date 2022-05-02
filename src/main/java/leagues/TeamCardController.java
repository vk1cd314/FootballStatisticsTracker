package leagues;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import teams.Team;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamCardController implements Initializable {

    @FXML
    private Label leaguePosition;

    @FXML
    private Label lost;

    @FXML
    private Label matches;

    @FXML
    private Label points;

    @FXML
    private Label teamName;

    @FXML
    private Label won;

    Team team;

    public TeamCardController(){
        //this.team = inTeam;
        System.out.println("el chodu");
        //leaguePosition.setText(String.valueOf(this.team.getPosition()));
        //teamName.setText(this.team.getName());
        //matches.setText(String.valueOf(this.team.getMatchesPlayed()));
        //won.setText(String.valueOf(this.team.getWins()));
        //lost.setText(String.valueOf(this.team.getLosses()));
        //points.setText(String.valueOf(this.team.getPoints()));
    }
    public void setData(Team inTeam){
        this.team = inTeam;
        System.out.println(team.getName()+" "+team.getWins());

        leaguePosition.setText(String.valueOf(inTeam.getPosition()));
        teamName.setText(inTeam.getName());
        matches.setText(String.valueOf(inTeam.getMatchesPlayed()));
        won.setText(String.valueOf(inTeam.getWins()));
        lost.setText(String.valueOf(inTeam.getLosses()));
        points.setText(String.valueOf(inTeam.getPoints()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //leaguePosition.setText("baal");
//        teamName.setText(this.team.getName());
//        matches.setText(String.valueOf(this.team.getMatchesPlayed()));
//        won.setText(String.valueOf(this.team.getWins()));
//        lost.setText(String.valueOf(this.team.getLosses()));
//        points.setText(String.valueOf(this.team.getPoints()));
    }
}
