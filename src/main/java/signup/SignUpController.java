package signup;

import com.football.statisticstracker.LoginController;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    SignUpModel signUpModel = new SignUpModel();
    private double xShift = 0;
    private double yShift = 0;

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

    //public SignUpController() {
    //    initialize();
    //}

    public void realSignup() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signUp.fxml"));
            Parent root = loader.load();
            root.setOnMousePressed((MouseEvent event) -> {
                xShift = event.getSceneX();
                yShift = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xShift);
                stage.setY(event.getScreenY() - yShift);
            });
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image("football_transparent.png"));
            stage.setResizable(false);
            stage.setTitle("Sign Up");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public void initialize() {
    //    try {
    //        Stage stage = new Stage();
    //        FXMLLoader loader = new FXMLLoader();
    //        loader.setLocation(getClass().getResource("signUp.fxml"));
    //        Parent root = loader.load();
    //        root.setOnMousePressed((MouseEvent event) -> {
    //            xShift = event.getSceneX();
    //            yShift = event.getSceneY();
    //        });
    //        root.setOnMouseDragged((MouseEvent event) -> {
    //            stage.setX(event.getScreenX() - xShift);
    //            stage.setY(event.getScreenY() - yShift);
    //        });
    //        Scene scene = new Scene(root);
    //        stage.initStyle(StageStyle.UNDECORATED);
    //        stage.getIcons().add(new Image("football_transparent.png"));
    //        stage.setResizable(false);
    //        stage.setTitle("Sign Up");
    //        stage.setScene(scene);
    //        stage.show();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    public void signUp() {
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
