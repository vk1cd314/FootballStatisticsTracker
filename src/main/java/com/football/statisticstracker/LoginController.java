package com.football.statisticstracker;

import dashboard.DashboardController;
import dashboard.DashboardModel;
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
import signup.SignUpController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel();
    private static double xShift = 0;
    private static double yShift = 0;

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
    @FXML
    private Button signUpbutton;

    public void login(ActionEvent event) {
        try {

            if (this.loginModel.isLogin(this.loginUsername.getText(), this.loginPassword.getText(), this.choice.getValue().toString())) {
                quit();
                switch (this.choice.getValue().toString()) {
                    case "Admin" -> adminLogin(true);
                    case "User" -> adminLogin(false);
                }
            } else {
                this.loginStatus.setText("Wrong Credentials");
                this.loginStatus.setTextFill(Color.RED);
            }
        } catch (Exception localex) {
            localex.printStackTrace();
        }
    }

    public void adminLogin(boolean isAdmin) throws IOException {
        //DashboardController dashboardController = new DashboardController();
        Admin adminCredentials = new Admin(loginUsername.getText(), loginPassword.getText());
        //dashboardController.dashboardStart(adminCredentials);
        //dashboardController.changeUsername(adminCredentials.name);
        DashboardModel dashboardModel = new DashboardModel();
        adminCredentials.isAdmin = isAdmin;
        dashboardModel.start(adminCredentials);
    }
    //public void userLogin() throws IOException {
    //    Admin adminCredentials = new Admin(loginUsername.getText(), loginPassword.getText());
    //    //dashboardController.dashboardStart(adminCredentials);
    //    //dashboardController.changeUsername(adminCredentials.name);
    //    DashboardModel dashboardModel = new DashboardModel();
    //    adminCredentials.isAdmin = false;
    //    dashboardModel.start(adminCredentials);
    //}

    public void signUp() {
        try {
            quit();
            //Stage stage = new Stage();
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("signUp.fxml"));
            //Parent root = loader.load();
            //root.setOnMousePressed((MouseEvent event) -> {
            //    xShift = event.getSceneX();
            //    yShift = event.getSceneY();
            //});
            //root.setOnMouseDragged((MouseEvent event) -> {
            //    stage.setX(event.getScreenX() - xShift);
            //    stage.setY(event.getScreenY() - yShift);
            //});
            //Scene scene = new Scene(root);
            //stage.initStyle(StageStyle.UNDECORATED);
            //stage.getIcons().add(new Image("football_transparent.png"));
            //stage.setResizable(false);
            //stage.setTitle("Sign Up");
            //stage.setScene(scene);
            //stage.show();
            SignUpController signUpController = new SignUpController();
            signUpController.realSignup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loginStart(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("login.fxml")));
        Scene scene = new Scene(root, Color.TRANSPARENT);
        root.setOnMousePressed((MouseEvent event) -> {
            xShift = event.getSceneX();
            yShift = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xShift);
            stage.setY(event.getScreenY() - yShift);
        });
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("football_transparent.png"));
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
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