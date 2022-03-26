package com.football.statisticstracker;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    SignUpModel signUpModel = new SignUpModel();

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button signupbutton;
    @FXML
    private Button backbutton;

    public void sigunUp(){
        String sqlInsert = "INSERT INTO loginInfo(Username, Password, Type) VALUES(?,?,?)";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.username.getText());
            stmt.setString(2, this.password.getText());
            stmt.setString(3, "User");
            stmt.execute();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void clear(){
        this.username.setText("");
        this.password.setText("");
    }
    public void back(){
        Stage stage = (Stage) this.backbutton.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

}
