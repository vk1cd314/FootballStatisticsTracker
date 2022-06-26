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

public class UserModel {
    public UserController userController;
    //public ObservableValue<String> username;
    //public StringProperty username;
    //public ObservableValue<String> password;
    //public StringProperty password;
    public String hello;

    public void show(BorderPane borderPane, Admin admin) {
        //username = new SimpleStringProperty(admin.name);
        //password = new SimpleStringProperty(admin.password);
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("userControllerFXML.fxml"));
            borderPane.setCenter(root.load());
            userController = root.getController();
            userController.setUserData(admin);
            userController.loadData();
            //userController.username.textProperty().addListener((observable, oldValue, newValue) -> {
            //    System.out.println("textfield changed from " + oldValue + " to " + newValue);
            //    //username = new SimpleStringProperty(newValue);
            //    hello = newValue;
            //    //System.out.println(username + " baaallllalalllll ");
            //});
            //userController.password.textProperty().addListener((observable, oldValue, newValue) -> {
            //    System.out.println("textfield changed from " + oldValue + " to " + newValue);
            //    //password = new SimpleStringProperty(newValue);
            //    //System.out.println(password + " baaallllalalllll pass");
            //});
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
