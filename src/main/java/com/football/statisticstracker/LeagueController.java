package com.football.statisticstracker;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeagueController {
    @FXML
    private TableView<LeagueData> leagueTable;
    @FXML
    private TableColumn<LeagueData, String> team_name_col;
    @FXML
    private TableColumn<LeagueData, String> league_position_col;
    @FXML
    private TableColumn<LeagueData, String> matches_played_col;
    @FXML
    private TableColumn<LeagueData, String> wins_col;
    @FXML
    private TableColumn<LeagueData, String> draws_col;
    @FXML
    private TableColumn<LeagueData, String> losses_col;
    @FXML
    private TableColumn<LeagueData, String> goals_scored_col;
    @FXML
    private TableColumn<LeagueData, String> goals_conceded_col;
    @FXML
    private TableColumn<LeagueData, String> goal_difference_col;
    @FXML
    private TableColumn<LeagueData, String> clean_sheets_col;
    @FXML
    private Button backbutton;

    private ObservableList<LeagueData> data;

    @FXML
    private void loadLeagueData(){
        try {
            Connection conn = DatabaseConnection.getStatsConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT team_name, league_position, matches_played, wins, draws, losses, goals_scored, goals_conceded, goal_difference, clean_sheets FROM teams ORDER BY league_position ASC;");
            while(rs.next()){
                this.data.add(new LeagueData(rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
//        this.team_name_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("team_name"));
//        this.league_position_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("league_position"));
//        this.matches_played_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("matches_played"));
//        this.wins_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("wins"));
//        this.draws_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("draws"));
//        this.losses_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("losses"));
//        this.goals_scored_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("goals_scored"));
//        this.goals_conceded_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("goals_conceded"));
//        this.goal_difference_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String >("goal_difference"));
//        this.clean_sheets_col.setCellValueFactory(new PropertyValueFactory<LeagueData, String>("clean_sheets"));
//        this.leagueTable.setItems(null);
//        this.leagueTable.setItems(this.data);
    }
    public void leagueStart(){
        loadLeagueData();
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("leagueFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("football_transparent.png"));
            stage.setResizable(false);
            stage.setTitle("LeagueView");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void back() throws IOException {
        Stage stage = (Stage) this.backbutton.getScene().getWindow();
        stage.close();
        DashboardController dashboardController = new DashboardController();
        dashboardController.dashboardStart();
    }
}
