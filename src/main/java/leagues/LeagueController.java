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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
//    @FXML
//    private TableView<LeagueData> table;
//    @FXML
//    private TableColumn<LeagueData, String> team_name_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> league_position_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> matches_played_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> wins_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> draws_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> losses_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> goals_scored_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> goals_conceded_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> goal_difference_col;
//    @FXML
//    private TableColumn<LeagueData, Integer> clean_sheets_col;
    @FXML
    private VBox teamListCont;
    @FXML
    private Button cross;
    private Connection dc;
    private final List<Team> data = new ArrayList<>();
    HBox tile = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLeagueData();
        for (int i = 0; i <= 1; i++) {
            //data.get(i).print();

            try {
                TeamCardController teamCard = new TeamCardController();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("teamCard.fxml"));
                tile = fxmlLoader.load();

                teamCard.setData(data.get(i));
                teamListCont.getChildren().add(tile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        try {
//            tile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tileL.fxml")));
//            this.teamListCont.getChildren().add(tile);
////            tile = new FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
////            this.teamListCont.getChildren().add(tile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.team_name_col.setCellValueFactory(new PropertyValueFactory<>("team_name"));
//        this.league_position_col.setCellValueFactory(new PropertyValueFactory<>("league_position"));
//        this.matches_played_col.setCellValueFactory(new PropertyValueFactory<>("matches_played"));
//        this.wins_col.setCellValueFactory(new PropertyValueFactory<>("wins"));
//        this.draws_col.setCellValueFactory(new PropertyValueFactory<>("draws"));
//        this.losses_col.setCellValueFactory(new PropertyValueFactory<>("losses"));
//        this.goals_scored_col.setCellValueFactory(new PropertyValueFactory<>("goals_scored"));
//        this.goals_conceded_col.setCellValueFactory(new PropertyValueFactory<>("goals_conceded"));
//        this.goal_difference_col.setCellValueFactory(new PropertyValueFactory<>("goal_difference"));
//        this.clean_sheets_col.setCellValueFactory(new PropertyValueFactory<>("clean_sheets"));
//        table.setItems(data);
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
//        try {
//            tile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("teamCard.fxml")));
//            this.teamListCont.getChildren().add(tile);
////            tile = new FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
////            this.teamListCont.getChildren().add(tile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //System.out.println(data.size());
        //data.forEach(LeagueData::printLeague);
    }

    public void leagueStart(BorderPane borderPane) {
        Parent root = null;
        try {
            //Stage stage = new Stage();
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("leagueFXML.fxml"));
            //Parent root = loader.load();
            //Scene scene = new Scene(root);
            //stage.getIcons().add(new Image("football_transparent.png"));
            //stage.setResizable(false);
            //stage.setTitle("LeagueView");
            //stage.setScene(scene);
            //stage.show();
            root = FXMLLoader.load(getClass().getResource("leagueFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);
    }

    public void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
        //DashboardController dashboardController = new DashboardController();
        //dashboardController.dashboardStart();
    }
}
