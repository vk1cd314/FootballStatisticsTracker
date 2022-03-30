package com.football.statisticstracker;

import java.util.ArrayList;

public class Team {
    String name;
    int position;
    int matchesplayed;
    int wins;
    int draws;
    int losses;
    int goalsscored;
    int goalsconceded;
    int goaldiff;
    int cleansheet;
    ArrayList<Player> players = new ArrayList<>();
    public Team(String name, int position, int mp, int w, int l, int d, int gs, int gc, int gd, int cs){
        this.name = name;
        this.position = position;
        this.matchesplayed = mp;
        this.wins = w;
        this.losses = l;
        this.draws = d;
        this.goalsscored = gs;
        this.goalsconceded = gc;
        this.goaldiff = gd;
        this.cleansheet = cs;
    }
    public void addPlayer(Player x){
        players.add(x);
    }
    public void addPlayers(ArrayList<Player> players){
        this.players = players;
    }
}
