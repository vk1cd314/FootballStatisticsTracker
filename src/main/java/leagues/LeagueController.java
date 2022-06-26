package leagues;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teams.Team;
import teams.TeamCardController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeagueController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private VBox teamListCont;
    @FXML
    private Button cross;
    private Connection dc;
    private final List<Team> data = new ArrayList<>();
    HBox tile = null;
    @FXML
    private TextField teamSearch;
    @FXML
    private Button addMatch;

    @FXML
    private Label leagueLabel;

    @FXML
    private ComboBox<String> leagueComboBox;

    ArrayList<String> leagueList = new ArrayList<>();

    String filterLeague = "";
    String filterLeagueLoad = "";

    BorderPane borderPane1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLeagueData();
        loadLeagues();
        loadCards();
    }

    @FXML
    private void search(){
        teamSearch.setOnKeyReleased(keyEvent -> {
            data.clear();
            teamListCont.getChildren().clear();
            if(teamSearch.getText().equals("")){
                loadLeagueData();
                loadCards();
            }
            else{
                try{
                    Connection conn = DatabaseConnection.getStatsConnection();
                    String sql = "SELECT team_name, league, matches_played, wins, draws,losses, goals_scored, goals_conceded, goal_difference, clean_sheets, common_name FROM teams WHERE (team_name like '%"+teamSearch.getText()+"%'"+ filterLeague +") ORDER BY points DESC";
                    ResultSet rs = conn.createStatement().executeQuery(sql);
                    int i = 0;
                    while(rs.next()){
                        this.data.add(new Team(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                                rs.getInt(5), rs.getInt(6), rs.getInt(7),
                                rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), i));
                        i++;
                    }
                    loadCards();
                    conn.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void loadLeagues(){
        try
        {
            Connection con = DatabaseConnection.getStatsConnection();
            String sql = "SELECT league_name FROM leagues;";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                this.leagueList.add(rs.getString(1));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        leagueComboBox.setItems(FXCollections.observableArrayList(leagueList));
    }
    public void loadCards(){
        for (int i = 0; i < data.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("teamCard.fxml"));
                tile = fxmlLoader.load();
                TeamCardController teamCard = fxmlLoader.getController();
                teamCard.setData(data.get(i), this.borderPane1);
                teamListCont.getChildren().add(tile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadLeagueData() {
        try {
            Connection conn = DatabaseConnection.getStatsConnection();
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT team_name, league, matches_played, wins, draws," +
                    "losses, goals_scored, goals_conceded, goal_difference, clean_sheets, common_name FROM teams "+filterLeagueLoad+" ORDER BY points DESC;");
            int i = 1;
            while (rs.next()) {
                this.data.add(new Team(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), i));
                i++;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leagueStart(BorderPane borderPane) {
        try {
            //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboardHboxFXML.fxml")));
            FXMLLoader root = new FXMLLoader(getClass().getResource("leagueFXML.fxml"));
            borderPane.setCenter(root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filter(){
        filterLeagueLoad = "WHERE league = ";
        filterLeague = "AND league = ";
        String lg = "'"+leagueComboBox.getValue()+"'";
        filterLeague += lg;
        filterLeagueLoad += lg;
        leagueLabel.setText(leagueComboBox.getValue());
        loadLeagueData();
    }
    public void clear(){
        teamSearch.clear();
        leagueComboBox.setValue("");
        leagueComboBox.setPromptText("Select League");
        filterLeague = "";
        filterLeagueLoad = "";
    }
    public void reload(){
        loadLeagueData();
    }
    public void showAddMatch(){
        Match match = new Match();
        match.show();
        reload();
    }
    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
