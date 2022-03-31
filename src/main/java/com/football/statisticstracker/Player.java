package com.football.statisticstracker;

public abstract class Player {
    public String  name;
    public int age;
    public String birthday;
    public String league;
    public String position;
    public String club;
    public String nationality;
    public int appearances;
    public int goals_scored;
    public int assists;
    public int clean_sheets;
    public int red_cards;
    public int yellow_cards;
    public Player(String pos, String name, int age, String birthday, String league, String club, String nation, int app, int goals, int ass, int cs, int rc, int yc){
        this.position = pos;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.league = league;
        this.club = club;
        this.nationality = nation;
        this.appearances = app;
        this.goals_scored = goals;
        this.assists = ass;
        this.clean_sheets = cs;
        this.red_cards = rc;
        this.yellow_cards = yc;
    }
}
