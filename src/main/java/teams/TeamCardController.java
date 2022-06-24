package teams;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import teams.Team;
import teams.TeamViewController;

import java.io.IOException;
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

    BorderPane borderPane;


    Team team;

    public TeamCardController(){

    }
    public void setData(Team inTeam, BorderPane borderPane){
        this.team = inTeam;
        this.borderPane = borderPane;
        //if(borderPane==null) System.out.println("what the fuck");
        //System.out.println(team.getName()+" "+team.getWins());

        leaguePosition.setText(String.valueOf(inTeam.getPosition()));
        teamName.setText(inTeam.getName());
        matches.setText(String.valueOf(inTeam.getMatchesPlayed()));
        won.setText(String.valueOf(inTeam.getWins()));
        lost.setText(String.valueOf(inTeam.getLosses()));
        points.setText(String.valueOf(inTeam.getPoints()));
    }
    public void showTeamData(){
        System.out.println(team.name);
        TeamViewModel teamViewModel = new TeamViewModel();
        teamViewModel.show(team);

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
