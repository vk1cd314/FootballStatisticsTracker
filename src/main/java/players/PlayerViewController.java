package players;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerViewController {
    @FXML
    private Label ageLabel;

    @FXML
    private Label assistLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label cleanSheetLabel;

    @FXML
    private Label clubLabel;

    @FXML
    private Button cross;

    @FXML
    private Label goalsLabel;

    @FXML
    private Label matchesLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nationLabel;

    @FXML
    private Label positionLabel;

    @FXML
    private Label redLabel;

    @FXML
    private Label yellowLabel;

    Player player;

    public void load(Player player){
        this.player = player;
        nameLabel.setText(player.name);
        birthdayLabel.setText(player.birthday);
        clubLabel.setText(player.club);
        positionLabel.setText(player.position);
        nationLabel.setText(player.nationality);
        matchesLabel.setText(String.valueOf(player.appearances));
        goalsLabel.setText(String.valueOf(player.goals_scored));
        assistLabel.setText(String.valueOf(player.assists));
        cleanSheetLabel.setText(String.valueOf(player.clean_sheets));
        redLabel.setText(String.valueOf(player.red_cards));
        yellowLabel.setText(String.valueOf(player.yellow_cards));
    }
    public void deletePlayer(){
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String stmt  = "DELETE FROM players WHERE full_name = '"+player.name+"';";
            PreparedStatement prep = con.prepareStatement(stmt);
            prep.executeUpdate();
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
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
