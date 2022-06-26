package dashboard;

import com.football.statisticstracker.Admin;
import com.football.statisticstracker.User;
import com.football.statisticstracker.UserController;
import com.football.statisticstracker.UserModel;
import leagues.LeagueAddController;
import leagues.LeagueController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import players.PlayerAdd;
import players.PlayerAddController;
import players.PlayerListController;
import teams.TeamAdd;
import teams.TeamAddController;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class DashboardController {
    Admin adminCredentials;

    @FXML
    public Label username;

    @FXML
    public BorderPane borderPane;

    public void initialize() {
        Parent root = null;
        //HBox tiles = null, tiles2 = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboardHboxFXML.fxml")));
            //tiles = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile1.fxml")));
            //tiles2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.borderPane.setCenter(root);
        DashboardHboxController dashboardHboxController = new DashboardHboxController();
        //dashboardHboxController.dashboardStart();
        //showDashboard();
        //this.tilescontainer.getChildren().add(tiles);
        //this.tilesContainer.getChildren().add(tiles);
        //this.tilesContainer.getChildren().add(tiles2);
    }

    //public void dashboardStart(Admin admin) {
        //try {
        //    Stage stage = new Stage();
        //    FXMLLoader loader = new FXMLLoader();
        //    loader.setLocation(getClass().getResource("dashboardFXML.fxml"));
        //    Parent root = loader.load();
        //    root.setOnMousePressed((MouseEvent event) -> {
        //        xShift = event.getSceneX();
        //        yShift = event.getSceneY();
        //    });
        //    root.setOnMouseDragged((MouseEvent event) -> {
        //        stage.setX(event.getScreenX() - xShift);
        //        stage.setY(event.getScreenY() - yShift);
        //    });
        //    Scene scene = new Scene(root, Color.TRANSPARENT);
        //    stage.initStyle(StageStyle.UNDECORATED);
        //    stage.getIcons().add(new Image("football_transparent.png"));
        //    stage.setResizable(false);
        //    stage.setTitle("Dashboard");
        //    stage.setScene(scene);
        //    stage.show();
        //    //this.dashboard.setText("NO");
        //    //dashboardHbox();
        //    //setTime();
        //} catch (Exception exception) {
        //    exception.printStackTrace();
        //}
        //adminCredentials = admin;
        //username.setText(admin.name);
    //}

    //public void dashboardHbox() throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("dashboardHboxFXML.fxml"));
        //assert this.borderPane == null;
        //assert this.borderPane != null;
        //assert 3 == 4;
        //if (this.borderPane.getScene().getWindow() == null) System.out.println("Cringe");
        //else System.out.println("Also Cringe");
        //if (this.cross == null) System.out.println("Cross Cringe");
        //else System.out.println("Cross Also Cringe");
        //this.borderPane = new BorderPane(root);
        //this.borderPane.setCenter(root);
    //}

    public void changeUsername(String userName) {
        username.setText(userName);
    }

    public void quit() {
        //assert this.cross != null;
        //if (this.cross == null) System.out.println("Cross Cringe");
        //else System.out.println("Cross Also Cringe");
        Stage stage = (Stage) this.borderPane.getScene().getWindow();
        stage.close();
    }

    public void showDashboard() {
        DashboardHboxController dashboardHboxController = new DashboardHboxController();
        dashboardHboxController.dashboardStart(this.borderPane);
    }

    public void showLeague() {
        LeagueController leagueController = new LeagueController();
        //System.out.println(borderPane);
        leagueController.leagueStart(this.borderPane);
    }
    public void showPlayers(){
        PlayerListController playerListController = new PlayerListController();
        playerListController.playerViewStart(this.borderPane);
    }
    public void showTeamAdd(){
//        TeamAddController teamAddController = new TeamAddController();
//        teamAddController.teamAddStart(this.borderPane);
//        teamAddController.load();
        TeamAdd teamAdd = new TeamAdd();
        teamAdd.show(this.borderPane);
    }
    public void showLeagueAdd(){
        LeagueAddController leagueAddController = new LeagueAddController();
        leagueAddController.show(this.borderPane);
    }
    public void showPlayerAdd(){
        PlayerAdd playerAdd = new PlayerAdd();
        playerAdd.show(this.borderPane);
    }

    public void showUserInfo() {
        //UserController userController = new UserController(adminCredentials);
        //userController.show(this.borderPane);
        UserModel userModel = new UserModel();
        userModel.show(this.borderPane, adminCredentials);
    }
    //public void setTime() {
    //    long millis = System.currentTimeMillis();
    //    Date date = new Date(millis);
    //    this.currentDate.setText("Bro wot");
    //}
}
