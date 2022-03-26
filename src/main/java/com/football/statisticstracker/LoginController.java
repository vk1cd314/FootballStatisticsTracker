package com.football.statisticstracker;

import dashboard.DashboardController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import static dashboard.DashboardController.setTime;

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
    @FXML
    private Button quitButton;

    public void login(ActionEvent event) {
        try {
            if (this.loginModel.isLogin(this.loginUsername.getText(), this.loginPassword.getText(), ((option)this.choice.getValue()).toString())) {
                quit();
                switch (((option) this.choice.getValue()).toString()) {
                    case "Admin" -> adminLogin();
                    case "User" -> userLogin();
                }
            } else {
                this.loginStatus.setText("Wrong Credentials");
                //this.loginStatus.setTextFill(Color.RED);
            }
        } catch (Exception localex) {
            localex.printStackTrace();
        }
    }

    public void adminLogin() throws IOException {
        //System.out.println("In Admin");
        //DashboardController dashboardController = new DashboardController();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/dashboard/dashboardFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, Color.TRANSPARENT);
            stage.getIcons().add(new Image("football_transparent.png"));
            stage.setResizable(false);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
            //setTime();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void userLogin() {
        System.out.println("In User");
    }

    public void quit() {
        Stage stage = (Stage) this.quitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.loginModel.isDatabaseConnected()) {
            this.databaseStatus.setText("Connected");
        } else {
            this.databaseStatus.setText("Not Connected");
            this.databaseStatus.setTextFill(Color.RED);
        }
        this.choice.setItems(FXCollections.observableArrayList(option.values()));
    }

}