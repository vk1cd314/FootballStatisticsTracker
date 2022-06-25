package leagues;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LeagueAddController implements Initializable {
    @FXML
    private TextField countryNameField;

    @FXML
    private Label errorPrompt;

    @FXML
    private TextField leagueNameField;
    @FXML
    private Button cross;

    public void initialize(URL url, ResourceBundle rb){
    }
    public void show(BorderPane borderPane){
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("addLeagueFXML.fxml"));
            borderPane.setCenter(root.load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clear(){
        leagueNameField.clear();
        countryNameField.clear();
    }
    public void addLeague(){
        if(leagueNameField.getText()!= "" && leagueNameField != null && countryNameField.getText()!= "" && countryNameField != null){
            String insert = "INSERT INTO leagues(league_name, country) VALUES (?,?)";
            try{
                Connection con = DatabaseConnection.getStatsConnection();
                PreparedStatement stmt = con.prepareStatement(insert);
                stmt.setString(1, leagueNameField.getText());
                stmt.setString(2, countryNameField.getText());
                stmt.execute();
                clear();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            errorPrompt.setText("Please enter valid data");
        }
    }
    @FXML
    void quit() throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }
}
