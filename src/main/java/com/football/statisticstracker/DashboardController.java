package com.football.statisticstracker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;

public class DashboardController {
    private static double xShift = 0;
    private static double yShift = 0;

    @FXML
    private Label currentDate;
    @FXML
    private Label dashboard;
    @FXML
    private Button cross;

    public void dashboardStart() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dashboardFXML.fxml"));
            Parent root = loader.load();
            root.setOnMousePressed((MouseEvent event) -> {
                xShift = event.getSceneX();
                yShift = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xShift);
                stage.setY(event.getScreenY() - yShift);
            });
            Scene scene = new Scene(root, Color.TRANSPARENT);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image("football_transparent.png"));
            stage.setResizable(false);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
            //this.dashboard.setText("NO");
            //setTime();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void quit() {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
    public void showLeague(){
        LeagueController leagueController = new LeagueController();
        quit();
        leagueController.leagueStart();
    }

    public void setTime() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        this.currentDate.setText("Bro wot");
    }
}
