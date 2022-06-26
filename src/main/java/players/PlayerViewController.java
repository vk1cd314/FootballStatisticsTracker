package players;

import com.football.statisticstracker.Admin;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

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

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    Player player;

    Admin adminCredentials;

    public void load(Player player){
        this.player = player;
        nameLabel.setText(player.name);
        birthdayLabel.setText(player.birthday);
        ageLabel.setText(String.valueOf(player.age));
        clubLabel.setText(player.club);
        positionLabel.setText(player.position);
        nationLabel.setText(player.nationality);
        matchesLabel.setText(String.valueOf(player.appearances));
        goalsLabel.setText(String.valueOf(player.goals_scored));
        assistLabel.setText(String.valueOf(player.assists));
        cleanSheetLabel.setText(String.valueOf(player.clean_sheets));
        redLabel.setText(String.valueOf(player.red_cards));
        yellowLabel.setText(String.valueOf(player.yellow_cards));
        if (!adminCredentials.isAdmin) {
            edit.setStyle("-fx-background-color: TRANSPARENT; ");
            delete.setStyle("-fx-background-color: TRANSPARENT; ");
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Do nothing");
                }
            });
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Do nothing");
                }
            });
            edit.setTextFill(Color.TRANSPARENT);
            delete.setTextFill(Color.TRANSPARENT);
        }
    }
    public void deletePlayer(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Delete Player");
        a.setContentText("Are you sure you want to delete this player?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK) {
            try {
                Connection con = DatabaseConnection.getStatsConnection();
                String stmt = "DELETE FROM players WHERE full_name = '" + player.name + "';";
                PreparedStatement prep = con.prepareStatement(stmt);
                prep.executeUpdate();
                con.close();
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
    public void editPlayer(){
        try {
            quit();
        }catch(IOException e){
            e.printStackTrace();
        }
        PlayerEdit playerEdit = new PlayerEdit();
        playerEdit.show(this.player);
    }
    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
