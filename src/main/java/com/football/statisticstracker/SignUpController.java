package com.football.statisticstracker;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private PasswordField password;
    @FXML
    private PasswordField password1;
    @FXML
    private Label statuslabel;
    @FXML
    private Button backbutton;

    public void sigunUp() {
        if (password.getText().compareTo(password1.getText()) != 0) {
            statuslabel.setText("Passwords don't match.");
        } else if (this.signUpModel.isUser(username.getText())) {
            statuslabel.setText("Username already exists.");
        } else {
            String sqlInsert = "INSERT INTO loginInfo(Username, Password, Type) VALUES(?,?,?)";
            try {
                Connection conn = DatabaseConnection.getConnection();
                assert conn != null;
                PreparedStatement stmt = conn.prepareStatement(sqlInsert);

                stmt.setString(1, this.username.getText());
                stmt.setString(2, this.password.getText());
                stmt.setString(3, "User");
                stmt.execute();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statuslabel.setText("Success!");
        }
    }

    public void clear() {
        this.username.setText("");
        this.password.setText("");
        this.password1.setText("");
    }

    public void back() throws IOException {
        Stage stage = (Stage) this.backbutton.getScene().getWindow();
        stage.close();
        LoginController.loginStart(new Stage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
