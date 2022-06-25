package leagues;

import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MatchController {
    @FXML
    private Button addMatchButton;

    @FXML
    private TextField awayGoalsbox1;

    @FXML
    private ComboBox<String> awayteamCombobox;

    @FXML
    private Button clearButton;

    @FXML
    private Button cross;

    @FXML
    private TextField homeGoalsbox;

    @FXML
    private ComboBox<String> homeTeamCombobox;

    ArrayList<String> teamList = new ArrayList<>();

    public void load(){
        try
        {
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
        this.homeTeamCombobox.setItems(FXCollections.observableArrayList(teamList));
        this.awayteamCombobox.setItems(FXCollections.observableArrayList(teamList));
    }

}
