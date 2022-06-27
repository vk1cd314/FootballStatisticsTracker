package leagues;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MatchController {
    @FXML
    private Button addMatchButton;

    @FXML
    private TextField awayGoalsbox;

    @FXML
    private ComboBox<String> awayteamCombobox;

    @FXML
    private Button clearButton;

    @FXML
    private Button cross;

    @FXML
    private TextField homeGoalsbox;

    @FXML
    private ComboBox<String> homeTeamCombobox;
    @FXML
    private Label errorLabel;

    ArrayList<String> teamList = new ArrayList<>();
    String league;

    public void load(String league) {
        try {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT team_name FROM teams WHERE league = '" + league + "';";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                this.teamList.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.homeTeamCombobox.setItems(FXCollections.observableArrayList(teamList));
        this.awayteamCombobox.setItems(FXCollections.observableArrayList(teamList));
    }

    public void clear() {
        homeGoalsbox.clear();
        awayGoalsbox.clear();
        homeTeamCombobox.setValue("");
        awayteamCombobox.setValue("");
        homeTeamCombobox.setPromptText("Select Home Team");
        awayteamCombobox.setPromptText("Select Away Team");
    }

    public void addMatch() {
        if (homeTeamCombobox.getValue() == awayteamCombobox.getValue() || homeTeamCombobox.getValue() == null || awayteamCombobox.getValue() == null) {
            errorLabel.setText("Error");
        } else {
            try {
                Connection con = DatabaseConnection.getStatsConnection();
                int homeGoals = Integer.valueOf(homeGoalsbox.getText());
                int awayGoals = Integer.valueOf(awayGoalsbox.getText());
                int homeWins = 0, homeLosses = 0, homeDraws = 0;
                int homeCleanSheet = 0, awayCleanSheet = 0;
                int homePoints = 0, awayPoints = 0;
                //cleanSheetCalc
                if (awayGoals == 0) homeCleanSheet = 1;
                if (homeGoals == 0) awayCleanSheet = 1;
                //wincalc
                if (homeGoals > awayGoals) {
                    homeWins = 1;
                    homePoints = 3;
                } else if (homeGoals < awayGoals) {
                    homeLosses = 1;
                    awayPoints = 3;
                } else {
                    homeDraws = 1;
                    homePoints = 1;
                    awayPoints = 1;
                }

                String stmt = "UPDATE teams SET matches_played = matches_played + 1," +
                        "    wins = wins + ?," +
                        "    draws = draws + ?," +
                        "    losses = losses + ?," +
                        "    goals_scored = goals_scored + ?," +
                        "    goals_conceded = goals_conceded + ?," +
                        "    goal_difference = goals_scored + ? - goals_conceded - ?," +
                        "    clean_sheets = clean_sheets + ?," +
                        "     points = points + ?" +
                        "WHERE team_name = ?;";

                //home team update
                PreparedStatement query = con.prepareStatement(stmt);
                query.setInt(1, homeWins);
                query.setInt(2, homeDraws);
                query.setInt(3, homeLosses);
                query.setInt(4, homeGoals);
                query.setInt(5, awayGoals);
                query.setInt(6, homeGoals);
                query.setInt(7, awayGoals);
                query.setInt(8, homeCleanSheet);
                query.setInt(9, homePoints);
                query.setString(10, homeTeamCombobox.getValue());
                query.execute();

                //awayteamUpdate
                query = con.prepareStatement(stmt);
                query.setInt(1, homeLosses);
                query.setInt(2, homeDraws);
                query.setInt(3, homeWins);
                query.setInt(4, awayGoals);
                query.setInt(5, homeGoals);
                query.setInt(6, awayGoals);
                query.setInt(7, homeGoals);
                query.setInt(8, awayCleanSheet);
                query.setInt(9, awayPoints);
                query.setString(10, awayteamCombobox.getValue());
                query.execute();
                String st = "UPDATE players SET appearances_overall = appearances_overall + 1  WHERE Current_Club = ?";
                PreparedStatement q = con.prepareStatement(st);
                q.setString(1, homeTeamCombobox.getValue());
                q.execute();
                q.setString(1, awayteamCombobox.getValue());
                q.execute();
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
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
