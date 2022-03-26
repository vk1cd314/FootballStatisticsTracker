package dashboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;

public class DashboardController {
    private double xShift = 0;
    private double yShift = 0;

    @FXML
    private Label currentDate;

    public DashboardController() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dashboardFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, Color.TRANSPARENT);
        root.setOnMousePressed((MouseEvent event) -> {
            xShift = event.getSceneX();
            yShift = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xShift);
            stage.setY(event.getScreenY() - yShift);
        });
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("football_transparent.png"));
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        setTime();
    }

    public void setTime() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        this.currentDate.setText(date.toString());
    }
}
