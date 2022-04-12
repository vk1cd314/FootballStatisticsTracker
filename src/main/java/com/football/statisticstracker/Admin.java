package com.football.statisticstracker;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends User{
    public boolean isAdmin = true;
    public Admin(String name, String pass){
        super(name, pass);
    }
    public void makeAdmin(String name){
        String makeAdmin = "UPDATE loginInfo SET Type = ? WHERE Username = ?";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(makeAdmin);
            stmt.setString(1, "Admin");
            stmt.setString(2, name);
            stmt.execute();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteUser(String name){
        String delete = "DELETE FROM loginInfo WHERE Usename = ?";
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(delete);
            stmt.setString(1, name);
            stmt.execute();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
