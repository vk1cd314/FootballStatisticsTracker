package teams;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamViewController implements Initializable {
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

    //Team team;
    @Override
    public void initialize(URL url, ResourceBundle rb){
//        if(team == null) System.out.print("eh?");
//        load();
    }

    public void load(Team team){
        if(team==null) System.out.println("team is null when passed into load");
        //if(this.team == null) System.out.println("team is null when called inside load why");
        System.out.println(team.name);
        System.out.println(team.goalDiff);
//        teamName.setText(team.name);
//        leaguePosition.setText(String.valueOf(team.position));
//        matchesPlayed.setText(String.valueOf(team.matchesPlayed));
//        wins.setText(String.valueOf(team.wins));
//        draws.setText(String.valueOf(team.draws));
//        losses.setText(String.valueOf(team.losses));
//        cleanSheets.setText(String.valueOf(team.cleanSheet));
//        goalsScored.setText(String.valueOf(team.goalsScored));
//        goalsConc.setText(String.valueOf(team.goalsConceded));
//        goalDiff.setText(String.valueOf(team.goalsConceded));
    }
    public void teamViewStart(){
//        this.team = team;
//        if(this.team == null) System.out.println("team is null when passed");
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("teamViewFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            teamName.setText("pls");
        }catch (IOException e){
            e.printStackTrace();
        }
        //load(team);
    }
    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }

}
