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
    @FXML
    private Label currentDate;

    public DashboardController() {
    }

    public void setTime() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        this.currentDate.setText("Bro wot");
    }
}
