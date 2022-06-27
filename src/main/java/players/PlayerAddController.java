package players;

import database.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class PlayerAddController implements Initializable {
    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Button cross;

    @FXML
    private Label errLabel;

    @FXML
    private ComboBox<String> leagueComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField nationalityTextField;

    @FXML
    private ComboBox<Positions> positionComboBox;

    @FXML
    private ComboBox<String> teamComboBox;

    @FXML
    private TextField ageTextBox;

    ArrayList<String> teamList = new ArrayList<>();
    ArrayList<String> leagueList = new ArrayList<>();

    public void initialize(URL url, ResourceBundle rb) {
        load();
    }

    public void load() {
        ageTextBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ageTextBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        positionComboBox.setItems(FXCollections.observableArrayList(Positions.values()));
        leagueList.clear();
        teamList.clear();
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT league_name FROM leagues;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.leagueList.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        leagueComboBox.setItems(FXCollections.observableArrayList(leagueList));
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT team_name FROM teams;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.teamList.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teamComboBox.setItems(FXCollections.observableArrayList(teamList));
    }

    public void clear() {
        nameTextField.clear();
        positionComboBox.setValue(null);
        leagueComboBox.setValue(null);
        teamComboBox.setValue(null);
        birthDatePicker.setValue(null);
        nationalityTextField.clear();
        ageTextBox.clear();
    }

    public void addPlayer() {
        if (nameTextField != null && nameTextField.getText() != "" && nationalityTextField != null && nationalityTextField.getText() != "" && positionComboBox != null && leagueComboBox != null && teamComboBox != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Changes");
            a.setContentText("Are you sure you want to add this player?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                String insert = "INSERT INTO players(full_name, position, age, birthday_GMT, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall)" +
                        "VALUES(?,?,?,?,?,?,?,0,0,0,0,0,0)";
                try {
                    Connection con = DatabaseConnection.getStatsConnection();
                    PreparedStatement stmt = con.prepareStatement(insert);
                    stmt.setString(1, nameTextField.getText());
                    stmt.setString(2, positionComboBox.getValue().toString());
                    stmt.setString(3, ageTextBox.getText());
                    stmt.setString(4, birthDatePicker.getValue().toString());
                    stmt.setString(5, leagueComboBox.getValue());
                    stmt.setString(6, teamComboBox.getValue());
                    stmt.setString(7, nationalityTextField.getText());
                    stmt.execute();
                    errLabel.setText("Success");
                    clear();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void show(BorderPane borderPane) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("playerAddFXML.fxml"));
            borderPane.setCenter(root.load());
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
