package com.football.statisticstracker;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    public String name;
    public boolean isAdmin = false;
    public String password;
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    public void changeName(String name){
        String updatename = "UPDATE loginInfo SET Username = ? WHERE Username = ?";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(updatename);
            stmt.setString(1, name);
            stmt.setString(2, this.name);
            stmt.execute();
            conn.close();
            this.name = name;
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void changePass(String pass){
        String updatepass = "UPDATE loginInfo SET Password = ? WHERE Username = ?";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(updatepass);
            stmt.setString(1, this.name);
            stmt.setString(2, pass);
            stmt.execute();
            conn.close();
            this.password = pass;
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
