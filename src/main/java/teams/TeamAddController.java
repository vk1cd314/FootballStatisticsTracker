package teams;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamAddController {
    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button cross;

    @FXML
    private TextField commonNameField;

    @FXML
    private Label progressLabel;

    @FXML
    private TextField teamNameField;

    public void  teamAddStart(BorderPane borderPane){
        try{
            FXMLLoader root = new FXMLLoader(getClass().getResource("teamAddFXML.fxml"));
            borderPane.setCenter(root.load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clear(){
        commonNameField.clear();
        teamNameField.clear();
    }

    ////error here idk why
    @FXML
    void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
    public void addTeam(){
        if(teamNameField != null) {
            String insert = "INSERT INTO teams(team_name, common_name, league_position, matches_played, wins, draws, losses, goals_scored, goals_conceded, goal_difference, clean_sheets)" +
                    "VALUES (?, ?,'21', '0', '0', '0', '0', '0', '0', '0', '0');";
            try {
                Connection con = DatabaseConnection.getStatsConnection();
                PreparedStatement stmt = con.prepareStatement(insert);
                stmt.setString(1, teamNameField.getText());
                stmt.setString(2, commonNameField.getText());
                stmt.execute();
                progressLabel.setText("Success!");
                clear();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
