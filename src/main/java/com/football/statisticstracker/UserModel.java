package com.football.statisticstracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UserModel {
    UserController userController;
    public void show(BorderPane borderPane, Admin admin) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("userControllerFXML.fxml"));
            borderPane.setCenter(root.load());
            userController = root.getController();
            userController.setUserData(admin);
            userController.loadData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
