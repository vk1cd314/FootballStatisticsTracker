package com.football.statisticstracker;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginController.loginStart(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}