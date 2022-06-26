package players;

import com.football.statisticstracker.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerCardController implements Initializable {
    @FXML
    private Label playerCountry;

    @FXML
    private Label playerName;

    @FXML
    private Label playerPos;

    @FXML
    private Label teamName;

    public Player player;

    public Admin adminCredentials;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
    public void setData(Player player){
        this.player = player;
        playerCountry.setText(player.nationality);
        playerName.setText(player.name);
        playerPos.setText(player.position);
        teamName.setText(player.club);
    }
    public void showPlayerData(){
        PlayerView playerView = new PlayerView();
        playerView.show(this.player, adminCredentials);
    }
}
