package com.football.statisticstracker;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserController {
    @FXML
    Button cross;
    @FXML
    public
    TextField username;
    @FXML
    public
    TextField password;
    @FXML
    Label informationUpdate;
    Admin adminCredentials;

    public void setUserData(Admin admin) {
        adminCredentials = admin;
    }
    //public void show(BorderPane borderPane) {
    //    try {
    //        FXMLLoader root = new FXMLLoader(getClass().getResource("userControllerFXML.fxml"));
    //        borderPane.setCenter(root.load());
    //    }catch (IOException e){
    //        e.printStackTrace();
    //    }
    //}

    //void

    public void loadData() {
        //username.setText(adminCredentials.name);
        changeUsername(adminCredentials.name);
        changePassword(adminCredentials.password);
        //password.setText("********");
    }

    public void changeUsername(String name) {
        username.setText(name);
        String updatename = "UPDATE loginInfo SET Username = ? WHERE Username = ?";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(updatename);
            stmt.setString(1, name);
            stmt.setString(2, adminCredentials.name);
            stmt.execute();
            conn.close();
            adminCredentials.name = name;
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void changePassword(String pass) {
        password.setText(pass);
        String updatepass = "UPDATE loginInfo SET Password = ? WHERE Username = ?";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(updatepass);
            stmt.setString(1, pass);
            stmt.setString(2, adminCredentials.name);
            stmt.execute();
            conn.close();
            adminCredentials.password = pass;
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateInformation() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Changes");
        a.setContentText("Are you sure you want to Update your Information?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            changeUsername(username.getText());
            //adminCredentials.name = username.getText();
            changePassword(password.getText());
            //adminCredentials.password = password.getText();
            informationUpdate.setTextFill(Color.GREEN);
            informationUpdate.setText("Information Updated");
        } else {
            informationUpdate.setTextFill(Color.RED);
            informationUpdate.setText("Information Not Updated");
        }
    }

    public void quit() {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
