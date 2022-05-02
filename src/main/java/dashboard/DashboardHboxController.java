package dashboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashboardHboxController {
    @FXML
    private Button cross;
    @FXML
    private HBox tilesContainer;

    public void initialize() {
        HBox tile1 = null, tile2  = null;
        try {
            tile1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile1.fxml")));
            tile2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if (this.tilesContainer == null) System.out.println("chudi life ke");
        //System.out.println("Gets Here");
        this.tilesContainer.getChildren().add(tile1);
        //this.tilesContainer.getChildren().add(tile1);
        //System.out.println("Gets Here");
        this.tilesContainer.getChildren().add(tile2);
        //System.out.println("Gets Here");
    }

    public void dashboardStart(BorderPane borderPane) {
        //HBox tile1 = null, tile2  = null;
        //try {
        //    tile1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile1.fxml")));
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
