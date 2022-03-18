package com.football.statisticstracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label loginStatus;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField loginUsername;

    public void onLoginClickActionEvent(ActionEvent event) {
        //try {
        ////    to be done
        //} catch() {
        //
        //}
    //    to be done
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}