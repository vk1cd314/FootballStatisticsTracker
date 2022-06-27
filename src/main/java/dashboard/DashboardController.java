package dashboard;

import com.football.statisticstracker.Admin;
import com.football.statisticstracker.UserModel;
import database.DatabaseConnection;
import javafx.scene.image.ImageView;
import leagues.LeagueAdd;
import leagues.LeagueAddController;
import leagues.LeagueController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import leagues.LeagueModel;
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
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboardHboxFXML.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.borderPane.setCenter(root);
        DashboardHboxController dashboardHboxController = new DashboardHboxController();
    }

    public void changeUsername(String userName) {
        username.setText(userName);
    }

    public void changeProfilePicture(String userName) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT pfpURL FROM loginInfo WHERE Username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String currentFileURL = rs.getString(1);
            if (!currentFileURL.equals(new String("Hello"))) {
                Image image = new Image(currentFileURL);
                profilePicture.setImage(image);
            }
        }
        con.close();
    }

    public void quit() {
        Stage stage = (Stage) this.borderPane.getScene().getWindow();
        stage.close();
    }

    public void showDashboard() {
        DashboardHboxController dashboardHboxController = new DashboardHboxController();
        dashboardHboxController.dashboardStart(this.borderPane);
    }

    public void showLeague() {
        LeagueModel leagueModel = new LeagueModel();
        System.out.println("In dash " + adminCredentials.name + " " + adminCredentials.password);
        leagueModel.show(this.borderPane, adminCredentials);
    }

    public void showPlayers() {
        PlayerList playerList = new PlayerList();
        playerList.show(borderPane, adminCredentials);
    }

    public void showTeamAdd() {
        TeamAdd teamAdd = new TeamAdd();
        teamAdd.show(this.borderPane);
    }

    public void showLeagueAdd() {
        LeagueAdd leagueAdd = new LeagueAdd();
        leagueAdd.show(this.borderPane);
    }

    public void showPlayerAdd() {
        PlayerAdd playerAdd = new PlayerAdd();
        playerAdd.show(this.borderPane);
    }

    public void showUserInfo() {
        UserModel userModel = new UserModel();
        userModel.show(this.borderPane, adminCredentials);
        userModel.userController.username.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            adminCredentials.name = newValue;
            changeUsername(adminCredentials.name);
        });
        userModel.userController.fileLocation.addListener((observable, oldValue, newValue) -> {
            System.out.println("StringProperty changed from " + oldValue + " to " + newValue);
            try {
                changeProfilePicture(adminCredentials.name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
