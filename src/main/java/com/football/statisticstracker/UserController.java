package com.football.statisticstracker;

import database.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
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
    ArrayList<String> users = new ArrayList<>();
    //File file = null;
    public StringProperty fileLocation = new SimpleStringProperty("");

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
                //file = selectedFile;
                //fileLocation = new SimpleStringProperty(selectedFile.toURI().toString());
                //fileLocation.getValue();
                fileLocation.setValue(selectedFile.toURI().toString());
                System.out.println("Halo world" + fileLocation.getValue());
                informationUpdate.setTextFill(Color.GREEN);
                informationUpdate.setText("Profile Picture Updated");
                conn.close();
            } catch(SQLException e) {
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
        PreparedStatement ps =  con.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String currentFileURL = rs.getString(1);
            //System.out.println("I am a barbie girl"+currentFileURL);
            if(!currentFileURL.equals(new String("noki"))) {
                Image image = new Image(currentFileURL);
                profilePicture.setImage(image);
            }
        }
        con.close();
    }

    public void loadData() throws SQLException {
        //username.setText(adminCredentials.name);
        changeUsername(adminCredentials.name);
        changePassword(adminCredentials.password);
        setProfilePicture(adminCredentials.name);
        //password.setText("********");
        loadBoxes();
    }
    void loadBoxes(){
        userComboBox.setItems(null);
        deleteUserBox.setItems(null);
        users.clear();
        try{
            String l = "SELECT Username FROM loginInfo WHERE Type = 'User'";
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(l);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                users.add(rs.getString(1));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        userComboBox.setItems(FXCollections.observableArrayList(users));
        deleteUserBox.setItems(FXCollections.observableArrayList(users));
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
    public void deleteUser(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Changes");
        a.setContentText("Are you sure you want to delete your account?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK) {
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
        if (deleteUserBox.getValue() != null ){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Changes");
            a.setContentText("Are you sure you want to delete this user?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                adminCredentials.deleteUser(deleteUserBox.getValue());
                progressLabel.setText("Success!");
                loadBoxes();
            }
            //adminCredentials.deleteUser(deleteUserBox.getValue());
        }
    }
    public void makeAdmin(){
        if(userComboBox.getValue()!=null) {
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
