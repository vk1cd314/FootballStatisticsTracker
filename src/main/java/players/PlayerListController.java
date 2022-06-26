package players;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teams.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlayerListController implements Initializable {
    @FXML
    private Button cross;

    @FXML
    private VBox playerListCont;

    @FXML
    private TextField playerSearch;

    @FXML
    private ComboBox<String> leagueFilter;

    @FXML
    private ComboBox<String> nationFilter;

    @FXML
    private ComboBox<Positions> positionFilter;

    @FXML
    private ComboBox<String> teamFilter;

    String leagueFilterString = "";
    ArrayList<String> leagueList = new ArrayList<>();
    String positionFilterString = "";
    String nationFilterString = "";
    Set<String> nationList = new HashSet<String>();
    String teamFilterString = "";
    ArrayList<String> teamList = new ArrayList<>();

    private final List<Player> data = new ArrayList<>();

    HBox tile = null;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        loadPlayerData();
        loadCards();
        loadFilters();
    }
    @FXML
    void quit(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
    public void loadFilters(){
        positionFilter.setValue(null);
        leagueFilter.setValue(null);
        nationFilter.setValue(null);
        teamFilter.setValue(null);
        positionFilter.setItems(FXCollections.observableArrayList(Positions.values()));
        leagueList.clear();
        teamList.clear();
        try{
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT league_name FROM leagues;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                this.leagueList.add(rs.getString(1));
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        leagueFilter.setItems(FXCollections.observableArrayList(leagueList));
        try{
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT team_name FROM teams;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                this.teamList.add(rs.getString(1));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        teamFilter.setItems(FXCollections.observableArrayList(teamList));
        try{
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT nationality FROM players;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                this.nationList.add(rs.getString(1));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        nationFilter.setItems(FXCollections.observableArrayList(nationList));
    }
    @FXML
    void search() {
        playerSearch.setOnKeyReleased(keyEvent -> {
            data.clear();
            playerListCont.getChildren().clear();
            if(playerSearch.getText().equals("")){
                loadPlayerData();
                loadCards();
            }
            else{
                try{
                    Connection conn = DatabaseConnection.getStatsConnection();
                    String sql = "SELECT position , full_name, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall FROM players WHERE (full_name LIKE '%"+playerSearch.getText()+"%' "+leagueFilterString+" "+teamFilterString+" "+positionFilterString+" "+nationFilterString+") ORDER BY Current_Club ASC;";
                    ResultSet rs = conn.createStatement().executeQuery(sql);
                    while(rs.next()){
                        this.data.add(new Player(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
                    }
                    loadCards();
                    conn.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void playerViewStart(BorderPane borderPane){
        try{
            FXMLLoader root = new FXMLLoader(getClass().getResource("playerList.fxml"));
            borderPane.setCenter(root.load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadPlayerData(){
        try {
            Connection conn = DatabaseConnection.getStatsConnection();
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT position , full_name, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall FROM players ORDER BY Current_Club ASC;");
            while (rs.next()) {
                this.data.add(new Player(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadCards(){
        for(int i=0; i<data.size(); i++){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("playerCard.fxml"));
                tile = fxmlLoader.load();
                PlayerCardController playerCard = fxmlLoader.getController();
                playerCard.setData(data.get(i));
                playerListCont.getChildren().add(tile);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
