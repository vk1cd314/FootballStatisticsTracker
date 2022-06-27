package teams;

import com.football.statisticstracker.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TeamViewModel {
    double xShift = 0;
    double yShift = 0;
    public void show(Team team, Admin admin) {
        teamViewController = new TeamViewController();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("teamViewFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) -> {
                xShift = event.getSceneX();
                yShift = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xShift);
                stage.setY(event.getScreenY() - yShift);
            });
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            teamViewController = loader.getController();
            teamViewController.adminCredentials = admin;
            System.out.println("In teamViewModel " + admin.name + " " + admin.password);
            teamViewController.load(team);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TeamViewController teamViewController;

}
