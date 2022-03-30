package com.football.statisticstracker;

public class Forward extends Player {
    public Forward(String pos, String name, int age, String birthday, String league, String club, String nation, int app, int goals, int ass, int cs, int rc, int yc) {
        super("Forward", name, age, birthday, league, club, nation, app, goals, ass, 0, rc, yc);
    }
}
