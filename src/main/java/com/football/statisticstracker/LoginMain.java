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
    @Override
    public void start(Stage stage) throws IOException {
        LoginController.loginStart(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}