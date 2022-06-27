package com.football.statisticstracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import teams.Team;

import java.io.IOException;
import java.util.Objects;

public class TileModel {
    TileController tileController;

    public VBox showTeam(Team team) {
        try {
            System.out.println("Got here");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("tileFXML.fxml"));
            Object tile = fxmlLoader.load();
            //this.tilesContainer.getChildren().add(tile);
            //tile2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tile2.fxml")));
            tileController = fxmlLoader.getController();
            tileController.doStuff(team);
            return (VBox) tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
