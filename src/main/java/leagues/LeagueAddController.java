package leagues;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class LeagueAddController implements Initializable {
    @FXML
    private TextField countryNameField;

    @FXML
    private Label errorPrompt;
    @FXML
    private ComboBox<String> leagueComboBox;
    ArrayList<String> leagueList = new ArrayList<>();
    @FXML
    private TextField leagueNameField;
    @FXML
    private Button cross;

    public void initialize(URL url, ResourceBundle rb) {
    }

    public void clear() {
        leagueNameField.clear();
        countryNameField.clear();
        loadBox();
    }

    public void addLeague() {
        if (leagueNameField.getText() != "" && leagueNameField != null && countryNameField.getText() != "" && countryNameField != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm New League");
            a.setContentText("Are you sure you want to add this league?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                String insert = "INSERT INTO leagues(league_name, country) VALUES (?,?)";
                try {
                    Connection con = DatabaseConnection.getStatsConnection();
                    PreparedStatement stmt = con.prepareStatement(insert);
                    stmt.setString(1, leagueNameField.getText());
                    stmt.setString(2, countryNameField.getText());
                    stmt.execute();
                    clear();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            errorPrompt.setText("Please enter valid data");
        }
    }

    void loadBox() {
        leagueList.clear();
        leagueComboBox.setItems(null);
        try {
            String l = "SELECT league_name FROM leagues ORDER BY league_name ASC";
            Connection con = DatabaseConnection.getStatsConnection();
            PreparedStatement stmt = con.prepareStatement(l);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                leagueList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        leagueComboBox.setItems(FXCollections.observableArrayList(leagueList));
    }

    public void deleteLeague() {
        if (leagueComboBox.getValue() != null && !leagueComboBox.getValue().equals("")) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Delete League");
            a.setContentText("Are you sure you want to delete this league");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                String delete = "DELETE FROM leagues WHERE league_name = ?";
                try {
                    Connection con = DatabaseConnection.getStatsConnection();
                    PreparedStatement stmt = con.prepareStatement(delete);
                    stmt.setString(1, leagueComboBox.getValue());
                    stmt.execute();
                    con.close();
                    loadBox();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
