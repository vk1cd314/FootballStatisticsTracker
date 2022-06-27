package dashboard;

import com.football.statisticstracker.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardModel {
    private static double xShift = 0;
    private static double yShift = 0;
    DashboardController dashboardController;
    public void start(Admin admin) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dashboardFXML.fxml"));
            Parent root = loader.load();
            dashboardController = loader.getController();
            root.setOnMousePressed((MouseEvent event) -> {
                xShift = event.getSceneX();
                yShift = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - xShift);
                stage.setY(event.getScreenY() - yShift);
            });
            Scene scene = new Scene(root, Color.TRANSPARENT);
            //stage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("football_transparent.png"));
            stage.setResizable(false);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
            dashboardController.adminCredentials = admin;
            //System.out.println(admin.name);
            dashboardController.changeUsername(admin.name);
            dashboardController.changeProfilePicture(admin.name);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
