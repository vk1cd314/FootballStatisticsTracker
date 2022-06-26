package teams;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import players.Player;
import players.PlayerCardController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    HBox tile = null;

    private final List<Player> data = new ArrayList<>();

    Team team;
    @Override
    public void initialize(URL url, ResourceBundle rb){
//        if(team == null) System.out.print("eh?");
//        load();
    }

    public void load(Team team){
        this.team = team;
        loadPlayerData(team);
//        loadCards();
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
        points.setText(String.valueOf(team.points));
    }

    public void loadPlayerData(Team team){
        try {
            Connection conn = DatabaseConnection.getStatsConnection();
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT position , full_name, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall FROM players WHERE (Current_Club LIKE  '%"+team.common_name+"%') ORDER BY position ASC;");
            while (rs.next()) {
                this.data.add(new Player(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadCards(){
        System.out.println(data.size());
        for(int i=0; i<data.size(); i++){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("playerCard.fxml"));
                tile = fxmlLoader.load();
                PlayerCardController playerCard = fxmlLoader.getController();
                playerCard.setData(data.get(i));
                System.out.println(data.get(i).name);
                playerListContainer.getChildren().add(tile);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void editTeam(){
        try {
            quit();
        }catch(IOException e){
            e.printStackTrace();
        }
        TeamEdit teamEdit = new TeamEdit();
        teamEdit.show(team);
    }
    public void deleteTeam(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Deletion");
        a.setContentText("Are you sure you want to delete this team?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK) {
            try {
                Connection con = DatabaseConnection.getStatsConnection();
                String stmt = "DELETE FROM teams WHERE team_name = '" + team.name + "';";
                PreparedStatement prep = con.prepareStatement(stmt);
                prep.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                quit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }

}
