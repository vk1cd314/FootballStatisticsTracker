package players;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerAddController implements Initializable {
        @FXML
        private DatePicker birthDatePicker;

        @FXML
        private Button cross;

        @FXML
        private Label errLabel;

        @FXML
        private ComboBox<String> leagueComboBox;

        @FXML
        private TextField nameTextField;

        @FXML
        private TextField nationalityTextField;

        @FXML
        private ComboBox<Positions> positionComboBox;

        @FXML
        private ComboBox<String> teamComboBox;

        @FXML
        private TextField ageTextBox;

        ArrayList<String> teamList = new ArrayList<>();
        ArrayList<String> leagueList = new ArrayList<>();

        public void initialize(URL url, ResourceBundle rb){
                load();
        }
        public void load(){
                positionComboBox.setItems(FXCollections.observableArrayList(Positions.values()));
                leagueList.clear();
                teamList.clear();
                try{
                        Connection con = DatabaseConnection.getStatsConnection();
                        String sql = "SELECT league_name FROM leagues;";
                        ResultSet rs = con.createStatement().executeQuery(sql);
                        while(rs.next()){
                                this.leagueList.add(rs.getString(1));
                        }
                        con.close();
                }catch (SQLException e){
                        e.printStackTrace();
                }
                leagueComboBox.setItems(FXCollections.observableArrayList(leagueList));
                try{
                        Connection con = DatabaseConnection.getStatsConnection();
                        String sql = "SELECT team_name FROM teams;";
                        ResultSet rs = con.createStatement().executeQuery(sql);
                        while(rs.next()){
                                this.teamList.add(rs.getString(1));
                        }
                        con.close();
                }catch(SQLException e){
                        e.printStackTrace();
                }
                teamComboBox.setItems(FXCollections.observableArrayList(teamList));
        }
        public void clear(){
                nameTextField.clear();
                positionComboBox.setValue(null);
                leagueComboBox.setValue(null);
                teamComboBox.setValue(null);
                birthDatePicker.setValue(null);
                nationalityTextField.clear();
                ageTextBox.clear();
        }
        public void addPlayer(){
                if(nameTextField != null && nameTextField.getText() != "" && nationalityTextField != null && nationalityTextField.getText() != "" && positionComboBox != null && leagueComboBox != null && teamComboBox != null){
                        String insert = "INSERT INTO players(full_name, position, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall)"+
                                "VALUES(?,?,?,?,?,?,?,0,0,0,0,0,0)";
                        try{
                             Connection con = DatabaseConnection.getStatsConnection();
                             PreparedStatement stmt = con.prepareStatement(insert);
                             stmt.setString(1, nameTextField.getText());
                             stmt.setString(2, positionComboBox.getValue().toString());
                             stmt.setString(3, ageTextBox.getText());
                             stmt.setString(4, birthDatePicker.getValue().toString());
                             stmt.setString(5, leagueComboBox.getValue());
                             stmt.setString(5, teamComboBox.getValue());
                             stmt.setString(6, nationalityTextField.getText());
                             stmt.execute();
                             errLabel.setText("Success");
                             clear();
                             con.close();
                        }catch (SQLException e){
                                e.printStackTrace();
                        }
                }
        }
        public void show(BorderPane borderPane){
                try {
                        FXMLLoader root = new FXMLLoader(getClass().getResource("playerAddFXML.fxml"));
                        borderPane.setCenter(root.load());
                        load();
                }catch (IOException e){
                        e.printStackTrace();
                }
        }
        public void quit() throws IOException{
                Stage stage = (Stage) this.cross.getScene().getWindow();
                stage.close();
        }
}