package com.football.statisticstracker;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML
    private Label loginStatus;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField loginUsername;
    @FXML
    private Label databaseStatus;
    @FXML
    private ComboBox<option> choice;

    public void login(ActionEvent event) {
        try {
            if (this.loginModel.isLogin(this.loginUsername.getText(), this.loginPassword.getText(), ((option)this.choice.getValue()).toString())) {
                //Stage stage = (Stage)this.loginButton.getScene().getWindow();
                //stage.close();
                switch (((option) this.choice.getValue()).toString()) {
                    case "Admin" -> adminLogin();
                    case "User" -> userLogin();
                }
            } else {
                this.loginStatus.setText("Wrong Credentials");
            }
        } catch (Exception localex) {
            localex.printStackTrace();
        }
    }

    public void adminLogin() {
        //try {
        System.out.println("In Admin");
    }

    public void userLogin() {
        System.out.println("In User");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.loginModel.isDatabaseConnected()) {
            this.databaseStatus.setText("Connected");
        } else {
            this.databaseStatus.setText("Not Connected");
        }
        this.choice.setItems(FXCollections.observableArrayList(option.values()));
    }

}