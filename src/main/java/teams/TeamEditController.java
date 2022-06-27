package teams;

import database.DatabaseConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class TeamEditController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cleanSheetsBox;

    @FXML
    private Button commitButton;

    @FXML
    private TextField drawsBox;

    @FXML
    private TextField goalDiffBox;

    @FXML
    private TextField goalsAgainstBox;

    @FXML
    private TextField goalsForBox;

    @FXML
    private TextField lossesBox;

    @FXML
    private TextField matchesPldbox;



    @FXML
    private TextField teamNamebox;

    @FXML
    private TextField winsBox;

    Team team;

    public void load(Team inTeam) {
        this.team = inTeam;
        winsBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    winsBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lossesBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    lossesBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        drawsBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    drawsBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        goalsForBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    goalsForBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        goalsAgainstBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    goalsAgainstBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        cleanSheetsBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cleanSheetsBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        goalDiffBox.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        teamNamebox.setText(team.name);
        //positionBox.setText(String.valueOf(team.position));
        matchesPldbox.setText(String.valueOf(team.matchesPlayed));
        winsBox.setText(String.valueOf(team.wins));
        lossesBox.setText(String.valueOf(team.losses));
        drawsBox.setText(String.valueOf(team.draws));
        goalDiffBox.setText(String.valueOf(team.goalsScored - team.goalsConceded));
        goalsForBox.setText(String.valueOf(team.goalsScored));
        goalsAgainstBox.setText(String.valueOf(team.goalsConceded));
        cleanSheetsBox.setText(String.valueOf(team.cleanSheet));
    }

    public void change() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Changes");
        a.setContentText("Are you sure you want to make these changes?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Connection con = DatabaseConnection.getStatsConnection();
                String stmt = "UPDATE teams SET team_name = '" + teamNamebox.getText() +
                        "' , matches_played = '" + matchesPldbox.getText() +
                        "' , wins = '" + winsBox.getText() +
                        "' , draws = '" + drawsBox.getText() +
                        "' , losses = '" + lossesBox.getText() +
                        "' , goals_scored = '" + goalsForBox.getText() +
                        "' , goals_conceded = '" + goalsAgainstBox.getText() +
                        "' , goal_difference = '" + goalDiffBox.getText() +
                        "' , clean_sheets = '" + cleanSheetsBox.getText() +
                        "' WHERE team_name = '" + team.name + "'  ;";
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

    public void quit() throws IOException {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

}
