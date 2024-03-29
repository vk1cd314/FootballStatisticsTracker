package players;

import database.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class PlayerEditController {

    @FXML
    private TextField ageField;

    @FXML
    private TextField appField;

    @FXML
    private TextField assBox;

    @FXML
    private DatePicker bdayPicker;

    @FXML
    private TextField cleanSheetBox;

    @FXML
    private ComboBox<String> clubComboBox;


    @FXML
    private ComboBox<String> leagueComboBox;

    @FXML
    private Button cross;

    @FXML
    private Label errLabel;

    @FXML
    private TextField goalsField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField nationalityField;

    @FXML
    private ComboBox<Positions> positionComboBox;

    @FXML
    private TextField redField;

    @FXML
    private TextField yellowField;

    String filterByTeam = "";
    String filterByLeague = "";

    Player player;
    ArrayList<String> clubList = new ArrayList<>();
    ArrayList<String> leagueList = new ArrayList<>();

    public void change() {

        if (nameField != null && positionComboBox != null && ageField != null && bdayPicker != null && leagueComboBox != null && clubComboBox != null && nationalityField != null && appField != null && goalsField != null && assBox != null && cleanSheetBox != null && redField != null && yellowField != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Changes");
            a.setContentText("Are you sure you want to make these changes?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String Bday = "";
                    if(bdayPicker.getValue()==null){
                        Bday = player.birthday;
                    }
                    else {
                        Bday = bdayPicker.getValue().toString();
                    }
                    Connection con = DatabaseConnection.getStatsConnection();
                    String stmt = "UPDATE players SET full_name = '" + nameField.getText() +
                            "'  , position = '" + positionComboBox.getValue().toString() +
                            "' , age = '" + ageField.getText() +
                            "' , birthday_GMT = '" + Bday +
                            "' , league  = '" + leagueComboBox.getValue() +
                            "' , Current_Club = '" + clubComboBox.getValue() +
                            "' , goals_overall = '" + goalsField.getText() +
                            "' , nationality = '" + nationalityField.getText() +
                            "' , appearances_overall = '" + appField.getText() +
                            "' , assists_overall = '" + assBox.getText() +
                            "' , clean_sheets_overall = '" + cleanSheetBox.getText() +
                            "' , red_cards_overall = '" + redField.getText() +
                            "' , yellow_cards_overall = '" + yellowField.getText() +
                            "' WHERE full_name = '" + player.name + "'  ;";
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
        } else {
            errLabel.setText("Please input valid entries");
        }
    }

    public void clear() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Clear");
        a.setContentText("Are you sure you want to clear all fields?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            nameField.clear();
            positionComboBox.setValue(null);
            ageField.clear();
            bdayPicker.setValue(null);
            leagueComboBox.setValue(null);
            clubComboBox.setValue(null);
            nationalityField.clear();
            appField.clear();
            goalsField.clear();
            assBox.clear();
            cleanSheetBox.clear();
            redField.clear();
            yellowField.clear();
        }
    }

    public void load(Player player) {
        this.player = player;
        leagueList.clear();
        clubList.clear();
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT league_name FROM leagues" + filterByTeam + ";";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.leagueList.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        leagueComboBox.setItems(FXCollections.observableArrayList(leagueList));
        leagueComboBox.setValue(player.league);
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT team_name FROM teams" + filterByLeague + ";";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.clubList.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ageField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ageField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        goalsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    goalsField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        assBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    assBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        appField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    appField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        cleanSheetBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cleanSheetBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        yellowField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    yellowField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        redField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    redField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        clubComboBox.setItems(FXCollections.observableArrayList(clubList));
        clubComboBox.setValue(player.club);
        nameField.setText(player.name);
        //clubField.setText(player.club);
        nationalityField.setText(player.nationality);
        //positionField.setText(player.position);
        positionComboBox.setItems(FXCollections.observableArrayList(Positions.values()));
        positionComboBox.setValue(Positions.fromValue(player.position));
        ageField.setText(String.valueOf(player.age));
        goalsField.setText(String.valueOf(player.goals_scored));
        assBox.setText(String.valueOf(player.assists));
        appField.setText(String.valueOf(player.appearances));
        cleanSheetBox.setText(String.valueOf(player.clean_sheets));
        redField.setText(String.valueOf(player.red_cards));
        yellowField.setText(String.valueOf(player.yellow_cards));
    }

    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
