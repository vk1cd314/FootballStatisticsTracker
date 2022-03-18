package com.football.statisticstracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class LoginMain extends Application {
    private double xShift = 0;
    private double yShift = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LoginMain.class.getResource("login.fxml")));
        //FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("login.fxml"));
        Scene scene = new Scene(root, Color.TRANSPARENT);
        root.setStyle("-fx-background-radius: 15px;");
        root.setStyle("-fx-background-color: transparent;");
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
    }

    public static void main(String[] args) {
        launch();
    }
}