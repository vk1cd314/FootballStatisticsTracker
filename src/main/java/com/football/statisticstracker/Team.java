package com.football.statisticstracker;

import java.util.ArrayList;

public class Team {
    String name;
    int position;
    int matchesPlayed;
    int wins;
    int draws;
    int losses;
    int goalsScored;
    int goalsConceded;
    int goalDiff;
    int cleanSheet;
    ArrayList<Player> players = new ArrayList<>();

    public Team(String name, int position, int mp, int w, int l, int d, int gs, int gc, int gd, int cs) {
        this.name = name;
        this.position = position;
        this.matchesPlayed = mp;
        this.wins = w;
        this.losses = l;
        this.draws = d;
        this.goalsScored = gs;
        this.goalsConceded = gc;
        this.goalDiff = gd;
        this.cleanSheet = cs;
    }

    public void addPlayer(Player x) {
        players.add(x);
    }

    public void addPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
