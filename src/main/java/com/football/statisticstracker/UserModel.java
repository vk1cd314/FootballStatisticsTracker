package com.football.statisticstracker;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;

public class UserModel {
    public UserController userController;
    public String hello;

    public void show(BorderPane borderPane, Admin admin) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("userControllerFXML.fxml"));
            borderPane.setCenter(root.load());
            userController = root.getController();
            userController.setUserData(admin);
            userController.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
