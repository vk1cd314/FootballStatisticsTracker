package dashboard;

import com.football.statisticstracker.Admin;
import com.football.statisticstracker.UserModel;
import database.DatabaseConnection;
import javafx.scene.image.ImageView;
import leagues.LeagueAddController;
import leagues.LeagueController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import players.PlayerAdd;
import players.PlayerList;
import players.PlayerListController;
import teams.TeamAdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DashboardController {
    Admin adminCredentials;

    @FXML
    public Label username;

    @FXML
    public BorderPane borderPane;

    @FXML
    ImageView profilePicture;

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

    public void changeProfilePicture(String userName) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT pfpURL FROM loginInfo WHERE Username = ?";
        PreparedStatement ps =  con.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String currentFileURL = rs.getString(1);
            System.out.println("noki had a Gigantic dick eyaeyayo"+currentFileURL);
            if(!currentFileURL.equals(new String("noki"))) {
                Image image = new Image(currentFileURL);
                profilePicture.setImage(image);
            }
        }
        con.close();
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
        PlayerList playerList = new PlayerList();
        playerList.show(borderPane);
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
        //userModel.username.addListener((observable, oldValue, newValue) -> {
        //    System.out.println("baal textfield changed from " + oldValue + " to " + newValue);
        //    adminCredentials.name = newValue.toString();
        //    changeUsername(adminCredentials.name);
        //});
        userModel.userController.username.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            //username = new SimpleStringProperty(newValue);
            //System.out.println(username + " baaallllalalllll ");
            adminCredentials.name = newValue;
            changeUsername(adminCredentials.name);
        });
        //userModel.userController.password.textProperty().addListener((observable, oldValue, newValue) -> {
        //    System.out.println("textfield changed from " + oldValue + " to " + newValue);
        //    //password = new SimpleStringProperty(newValue);
        //    //System.out.println(password + " baaallllalalllll pass");
        //    adminCredentials.o = newValue;
        //    changeUsername(adminCredentials.name);
        //});
        userModel.userController.fileLocation.addListener((observable, oldValue, newValue) -> {
            System.out.println("StringProperty changed from " + oldValue + " to " + newValue);
            //username = new SimpleStringProperty(newValue);
            //System.out.println(username + " baaallllalalllll ");
            //adminCredentials.name = newValue;
            try {
                changeProfilePicture(adminCredentials.name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    //public void setTime() {
    //    long millis = System.currentTimeMillis();
    //    Date date = new Date(millis);
    //    this.currentDate.setText("Bro wot");
    //}
}
