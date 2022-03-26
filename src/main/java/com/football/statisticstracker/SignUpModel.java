package com.football.statisticstracker;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SignUpModel {
    Connection connection;

    public SignUpModel(){
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (this.connection == null) System.exit(1);
    }
}
