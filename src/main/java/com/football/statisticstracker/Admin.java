package com.football.statisticstracker;

import database.DatabaseConnection;
import players.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
    public boolean isAdmin = true;
    public String name, password;
    public Admin(String name, String pass){
        this.name = name;
        this.password = pass;
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
    public void addPlayer(Player player){
        String insert = "INSERT INTO players(full_name, age, birthday, league, position, Current_Club, nationality,  appearances_overall, goals_overall, assists_overall, clean_sheets_overall, red_cards_overall, yellow_cards_overall) Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            Connection conn = DatabaseConnection.getStatsConnection();
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, player.name);
            stmt.setString(2, String.valueOf(player.age));
            stmt.setString(3, player.birthday);
            stmt.setString(4, player.league);
            stmt.setString(5, player.position);
            stmt.setString(6, player.club);
            stmt.setString(7, player.nationality);
            stmt.setString(8, String.valueOf(player.appearances));
            stmt.setString(9, String.valueOf(player.goals_scored));
            stmt.setString(10, String.valueOf(player.assists));
            stmt.setString(11, String.valueOf(player.clean_sheets));
            stmt.setString(12, String.valueOf(player.red_cards));
            stmt.setString(13, String.valueOf(player.yellow_cards));
            stmt.execute();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void addTeam(String name){
        String insert = "INSERT INTO teams(team_name, league_position, matches_played, wins, draws, losses, goals_scored, goals_conceded, goal_difference, clean_sheets) VALUES()";
    }
}
