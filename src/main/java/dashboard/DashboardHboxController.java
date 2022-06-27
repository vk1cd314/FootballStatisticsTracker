package dashboard;

import com.football.statisticstracker.TileModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teams.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DashboardHboxController {
    @FXML
    private Button cross;
    @FXML
    private HBox tilesContainer;

    //Team team;
    ArrayList <Team> teams = new ArrayList<>();

    public void initialize() {
        VBox tile = null;
        //try {
        //    System.out.println("Got here");
        //    tile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tileFXML.fxml")));
        //
        //    this.tilesContainer.getChildren().add(tile);
        //    //tile2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //if (this.tilesContainer == null) System.out.println("chudi life ke");
        //System.out.println("Gets Here");
        //this.tilesContainer.getChildren().add(tile1);
        //System.out.println("Gets Here");
        //this.tilesContainer.getChildren().add(tile2);
        //System.out.println("Gets Here");
        TileModel tileModel = new TileModel();
        //tileModel.showTeam()
    }

    public void dashboardStart(BorderPane borderPane) {
        //HBox tile1 = null, tile2  = null;
        //try {
        //    tile1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tileFXML.fxml")));
        //    tile2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
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

    public void quit() {
        //DashboardController dashboardController = new DashboardController();
        //dashboardController.quit();
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
