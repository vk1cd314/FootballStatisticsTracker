package players;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import teams.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerListController implements Initializable {
    @FXML
    private Button cross;

    @FXML
    private VBox playerListCont;

    @FXML
    private TextField playerSearch;

    private final List<Player> data = new ArrayList<>();

    HBox tile = null;

    @FXML
    void quit(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.cross.getScene().getWindow();
        stage.close();
    }

    @FXML
    void search() {
        playerSearch.setOnKeyReleased(keyEvent -> {
            data.clear();
            playerListCont.getChildren().clear();
            if(playerSearch.getText().equals("")){
                loadPlayerData();
                loadCards();
            }
            else{
                try{
                    Connection conn = DatabaseConnection.getStatsConnection();
                    String sql = "SELECT position , full_name, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall FROM players WHERE (full_name LIKE '%"+playerSearch.getText()+"%' OR Current_Club LIKE  '%"+playerSearch.getText()+"%' OR nationality LIKE '%"+playerSearch.getText()+"%') ORDER BY Current_Club ASC;";
                    ResultSet rs = conn.createStatement().executeQuery(sql);
                    while(rs.next()){
                        this.data.add(new Player(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
                    }
                    loadCards();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        loadPlayerData();
        loadCards();
    }
    public void playerViewStart(BorderPane borderPane){
        try{
            FXMLLoader root = new FXMLLoader(getClass().getResource("playerList.fxml"));
            borderPane.setCenter(root.load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadPlayerData(){
        try {
            Connection conn = DatabaseConnection.getStatsConnection();
            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT position , full_name, age, birthday, league, Current_Club, nationality, appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall FROM players ORDER BY Current_Club ASC;");
            while (rs.next()) {
                this.data.add(new Player(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadCards(){
        for(int i=0; i<data.size(); i++){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("playerCard.fxml"));
                tile = fxmlLoader.load();
                PlayerCardController playerCard = fxmlLoader.getController();
                playerCard.setData(data.get(i));
                playerListCont.getChildren().add(tile);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
