package teams;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


    @FXML
    private ComboBox<String> leagueComboBox;

    ArrayList<String> leagueList = new ArrayList<>();

    public void load(){
        try
        {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT league_name FROM leagues;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                this.leagueList.add(rs.getString(1));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        leagueComboBox.setItems(FXCollections.observableArrayList(leagueList));
    }
    public void clear(){
        commonNameField.clear();
        teamNameField.clear();
        leagueComboBox.setValue("");
    }

    @FXML
    void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
    public void addTeam(){
        if(teamNameField != null && teamNameField.getText() != "" && commonNameField.getText() != "" && leagueComboBox != null) {
            String insert = "INSERT INTO teams(team_name, common_name, league, matches_played, wins, draws, losses, goals_scored, goals_conceded, goal_difference, clean_sheets)" +
                    "VALUES (?, ?, ?, '0', '0', '0', '0', '0', '0', '0', '0');";
            try {
                Connection con = DatabaseConnection.getStatsConnection();
                PreparedStatement stmt = con.prepareStatement(insert);
                stmt.setString(1, teamNameField.getText());
                stmt.setString(2, commonNameField.getText());
                stmt.setString(3, leagueComboBox.getValue());
                stmt.execute();
                progressLabel.setText("Success!");
                clear();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            progressLabel.setText("please enter valid data");
        }
    }
}
