package leagues;

import dashboard.DashboardController;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LeagueController implements Initializable {
//hagu
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


    BorderPane borderPane1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLeagueData();
        loadCards();
//
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
                    String sql = "SELECT team_name, league_position, matches_played, wins, draws,losses, goals_scored, goals_conceded, goal_difference, clean_sheets FROM teams WHERE team_name like '%"+teamSearch.getText()+"%' ORDER BY league_position ASC";
                    ResultSet rs = conn.createStatement().executeQuery(sql);
                    while(rs.next()){
                        this.data.add(new Team(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                                rs.getInt(5), rs.getInt(6), rs.getInt(7),
                                rs.getInt(8), rs.getInt(9), rs.getInt(10)));
                    }
                    loadCards();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void loadCards(){
        //BorderPane bp = this.borderPane1;
        System.out.println(borderPane1);
        if(this.borderPane1==null) System.out.println("Hi noki?");
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
            ResultSet rs = conn.createStatement().executeQuery("SELECT team_name, league_position, matches_played, wins, draws," +
                    "losses, goals_scored, goals_conceded, goal_difference, clean_sheets FROM teams ORDER BY league_position ASC;");
            while (rs.next()) {
                this.data.add(new Team(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leagueStart(BorderPane borderPane) {
        this.borderPane1 = borderPane;
        if(this.borderPane1 == null) System.out.println("huh?");
        System.out.println(borderPane1+"hagu"+borderPane);
        try {
            //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboardHboxFXML.fxml")));
            FXMLLoader root = new FXMLLoader(getClass().getResource("leagueFXML.fxml"));
            borderPane.setCenter(root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
