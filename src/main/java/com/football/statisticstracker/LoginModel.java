package com.football.statisticstracker;

//import database.DatabaseConnection;
import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (this.connection == null) System.exit(1);
    }

    public boolean isDatabaseConnected() { return this.connection != null; }

    public boolean isLogin(String username, String password, String type) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sqlQuery = "SELECT * FROM loginInfo where Username = ? and Password = ? and Type = ?";

        try {
            preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, type);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
    }
}
