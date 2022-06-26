package teams;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
        leagueList.clear();
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
        leagueComboBox.setPromptText("Select League");
    }

    @FXML
    void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
    public void addTeam(){
        if(teamNameField != null && teamNameField.getText() != "" && commonNameField.getText() != "" && leagueComboBox != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Changes");
            a.setContentText("Are you sure you want to add this team?");
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.OK) {
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
        }
        else{
            progressLabel.setText("please enter valid data");
        }
    }
}
