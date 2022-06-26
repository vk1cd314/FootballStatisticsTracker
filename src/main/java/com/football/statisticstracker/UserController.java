package com.football.statisticstracker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    @FXML
    Button cross;
    public void show(BorderPane borderPane) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("userControllerFXML.fxml"));
            borderPane.setCenter(root.load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void quit() {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
