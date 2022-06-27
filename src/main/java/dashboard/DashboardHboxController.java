package dashboard;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import players.Player;
import teams.Team;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardHboxController {
    @FXML
    private Button cross;
    @FXML
    private HBox tilesContainer;

    @FXML
    private HBox tilesContainerPlayer;
    //Team team;
    ArrayList <Team> teams = new ArrayList<>();
    //incase you want players too
    ArrayList <Player> players = new ArrayList<>();

    public void initialize() {
        VBox tile = null;
        //try {
        //    System.out.println("Got here");
        //    tile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tileFXML.fxml")));
        //
        //    this.tilesContainer.getChildren().add(tile);
        //    //tile2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tilePlayer.fxml")));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //if (this.tilesContainer == null) System.out.println("chudi life ke");
        //System.out.println("Gets Here");
        //this.tilesContainer.getChildren().add(tile1);
        //System.out.println("Gets Here");
        //this.tilesContainer.getChildren().add(tile2);
        //System.out.println("Gets Here");
        loadTeamData();
        loadPlayerData();
        //TileModel tileModel = new TileModel();
        for (int i = 0; i < 3; ++i) {
            TileModel tileModel = new TileModel();
            tilesContainer.getChildren().add(tileModel.showTeam(teams.get(i)));
        }
        System.out.println(players.size());
        for (int i = 0; i < 3; ++i) {
            TileModel tileModel = new TileModel();
            tilesContainerPlayer.getChildren().add(tileModel.showPlayer(players.get(i)));
        }
        //tileModel.showTeam()

    }

    public void dashboardStart(BorderPane borderPane) {
        //HBox tile1 = null, tile2  = null;
        //try {
        //    tile1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tileFXML.fxml")));
        //    tile2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tilePlayer.fxml")));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        ////if (this.tilesContainer == null) System.out.println("chudi life ke");
        //System.out.println("Gets Here");
        //this.tilesContainer.getChildren().add(tile1);
        //System.out.println("Gets Here");
        //this.tilesContainer.getChildren().add(tile2);
        //System.out.println("Gets Here");
        //Do nothing? not anymore lul
        //Parent root = null;
        try {
            //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboardHboxFXML.fxml")));
            FXMLLoader root = new FXMLLoader(getClass().getResource("dashboardHboxFXML.fxml"));
            borderPane.setCenter(root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadTeamData(){
        teams.clear();
        try{
            Connection conn = DatabaseConnection.getStatsConnection();
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT team_name, league, matches_played, wins, draws," +
                    "losses, goals_scored, goals_conceded, goal_difference, clean_sheets, common_name FROM teams ORDER BY points DESC;");
            //how many you want 1 indexed for the users who are likely normies
            int i = 1;
            while (rs.next() && i<=3) {
                this.teams.add(new Team(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), i));
                i++;
            }
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void loadPlayerData(){
        try{
            Connection con = DatabaseConnection.getStatsConnection();
            String get = "SELECT position , full_name, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall FROM players ORDER BY goals_overall ASC;";
            ResultSet rs = con.createStatement().executeQuery(get);
            //how many you want 0 indexed
            int i = 0;
            while (rs.next() && i < 3) {
                this.players.add(new Player(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void quit() {
        //DashboardController dashboardController = new DashboardController();
        //dashboardController.quit();
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
