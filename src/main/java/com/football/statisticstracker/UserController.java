package com.football.statisticstracker;

import database.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @FXML
    Label progressLabel;
    @FXML
    ImageView profilePicture;
    Admin adminCredentials;
    @FXML
    ComboBox<String> userComboBox;
    @FXML
    ComboBox<String> deleteUserBox;
    @FXML
    Label typeOfUser;
    @FXML
    Button makeAdminButton;
    @FXML
    Button deleteUserButton;
    @FXML
    Label makeAdmin;
    @FXML
    Label deleteUser;
    ArrayList<String> users = new ArrayList<>();
    //File file = null;
    public StringProperty fileLocation = new SimpleStringProperty("");

    public void setUserData(Admin admin) {
        adminCredentials = admin;
        if (!adminCredentials.isAdmin) {
            typeOfUser.setText("User");
            userComboBox.setStyle("-fx-background-color: TRANSPARENT; ");
            userComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Do nothing");
                }
            });
            userComboBox.setDisable(true);
            deleteUserBox.setStyle("-fx-background-color: TRANSPARENT; ");
            deleteUserBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Do nothing");
                }
            });
            deleteUserBox.setDisable(true);
            makeAdmin.setText("");
            deleteUser.setText("");
            makeAdminButton.setStyle("-fx-background-color: TRANSPARENT; ");
            makeAdminButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Do nothing");
                }
            });
            makeAdminButton.setTextFill(Color.TRANSPARENT);
            deleteUserButton.setStyle("-fx-background-color: TRANSPARENT; ");
            deleteUserButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Do nothing");
                }
            });
            deleteUserButton.setTextFill(Color.TRANSPARENT);
        }
    }

    public void changeProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePicture.setImage(image);
            String updateProfilePicture = "UPDATE loginInfo SET pfpURL = ? WHERE Username = ?";
            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateProfilePicture);
                stmt.setString(1, selectedFile.toURI().toString());
                stmt.setString(2, adminCredentials.name);
                stmt.execute();
                conn.close();
                fileLocation.setValue(selectedFile.toURI().toString());
                System.out.println("Halo world" + fileLocation.getValue());
                informationUpdate.setTextFill(Color.GREEN);
                informationUpdate.setText("Profile Picture Updated");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            informationUpdate.setTextFill(Color.RED);
            informationUpdate.setText("Profile Picture Not Updated");
        }
    }

    public void setProfilePicture(String userName) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT pfpURL FROM loginInfo WHERE Username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String currentFileURL = rs.getString(1);
            if (!currentFileURL.equals(new String("Hello"))) {
                Image image = new Image(currentFileURL);
                profilePicture.setImage(image);
            }
        }
        con.close();
    }

    public void loadData() throws SQLException {
        changeUsername(adminCredentials.name);
        changePassword(adminCredentials.password);
        setProfilePicture(adminCredentials.name);
        loadBoxes();
    }

    void loadBoxes() {
        userComboBox.setItems(null);
        deleteUserBox.setItems(null);
        users.clear();
        try {
            String l = "SELECT Username FROM loginInfo WHERE Type = 'User'";
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(l);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(rs.getString(1));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userComboBox.setItems(FXCollections.observableArrayList(users));
        deleteUserBox.setItems(FXCollections.observableArrayList(users));
    }

    public void changeUsername(String name) {
        username.setText(name);
        String updatename = "UPDATE loginInfo SET Username = ? WHERE Username = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(updatename);
            stmt.setString(1, name);
            stmt.setString(2, adminCredentials.name);
            stmt.execute();
            conn.close();
            adminCredentials.name = name;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(String pass) {
        password.setText(pass);
        String updatepass = "UPDATE loginInfo SET Password = ? WHERE Username = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(updatepass);
            stmt.setString(1, pass);
            stmt.setString(2, adminCredentials.name);
            stmt.execute();
            conn.close();
            adminCredentials.password = pass;
        } catch (SQLException e) {
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
            changePassword(password.getText());
            informationUpdate.setTextFill(Color.GREEN);
            informationUpdate.setText("Information Updated");
        } else {
            informationUpdate.setTextFill(Color.RED);
            informationUpdate.setText("Information Not Updated");
        }
    }

    public void deleteUser() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Changes");
        a.setContentText("Are you sure you want to delete your account?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            String delete = "DELETE FROM loginInfo WHERE Username = ?";
            try {
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(delete);
                stmt.setString(1, adminCredentials.name);
                stmt.execute();
                conn.close();
                quit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOtherUser() {
        if (deleteUserBox.getValue() != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Changes");
            a.setContentText("Are you sure you want to delete this user?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                adminCredentials.deleteUser(deleteUserBox.getValue());
                progressLabel.setText("Success!");
                loadBoxes();
            }
        }
    }

    public void makeAdmin() {
        if (userComboBox.getValue() != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Changes");
            a.setContentText("Are you sure you want to this user an admin?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK)
                adminCredentials.makeAdmin(userComboBox.getValue());
            progressLabel.setText("Success!");
            loadBoxes();
        }
    }

    public void quit() {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
