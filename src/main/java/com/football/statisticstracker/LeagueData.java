package com.football.statisticstracker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LeagueData {
//    private final StringProperty team_name;
//    private final IntegerProperty matches_played;
//    private final IntegerProperty wins;
//    private final IntegerProperty losses;
//    private final IntegerProperty draws;
//    private final IntegerProperty league_position;
//    private final IntegerProperty goals_scored;
//    private final IntegerProperty goals_conceded;
//    private final IntegerProperty goal_difference;
//    private final IntegerProperty clean_sheets;
//
//    public String getTeam_name() {
//        return team_name.get();
//    }
//
//    public StringProperty team_nameProperty() {
//        return team_name;
//    }
//
//    public void setTeam_name(String team_name) {
//        this.team_name.set(team_name);
//    }
//
//    public int getMatches_played() {
//        return matches_played.get();
//    }
//
//    public IntegerProperty matches_playedProperty() {
//        return matches_played;
//    }
//
//    public void setMatches_played(int matches_played) {
//        this.matches_played.set(matches_played);
//    }
//
//    public int getWins() {
//        return wins.get();
//    }
//
//    public IntegerProperty winsProperty() {
//        return wins;
//    }
//
//    public void setWins(int wins) {
//        this.wins.set(wins);
//    }
//
//    public int getLosses() {
//        return losses.get();
//    }
//
//    public IntegerProperty lossesProperty() {
//        return losses;
//    }
//
//    public void setLosses(int losses) {
//        this.losses.set(losses);
//    }
//
//    public int getDraws() {
//        return draws.get();
//    }
//
//    public IntegerProperty drawsProperty() {
//        return draws;
//    }
//
//    public void setDraws(int draws) {
//        this.draws.set(draws);
//    }
//
//    public int getLeague_position() {
//        return league_position.get();
//    }
//
//    public IntegerProperty league_positionProperty() {
//        return league_position;
//    }
//
//    public void setLeague_position(int league_position) {
//        this.league_position.set(league_position);
//    }
//
//    public int getGoals_scored() {
//        return goals_scored.get();
//    }
//
//    public IntegerProperty goals_scoredProperty() {
//        return goals_scored;
//    }
//
//    public void setGoals_scored(int goals_scored) {
//        this.goals_scored.set(goals_scored);
//    }
//
//    public int getGoals_conceded() {
//        return goals_conceded.get();
//    }
//
//    public IntegerProperty goals_concededProperty() {
//        return goals_conceded;
//    }
//
//    public void setGoals_conceded(int goals_conceded) {
//        this.goals_conceded.set(goals_conceded);
//    }
//
//    public int getGoal_difference() {
//        return goal_difference.get();
//    }
//
//    public IntegerProperty goal_differenceProperty() {
//        return goal_difference;
//    }
//
//    public void setGoal_difference(int goal_difference) {
//        this.goal_difference.set(goal_difference);
//    }
//
//    public int getClean_sheets() {
//        return clean_sheets.get();
//    }
//
//    public IntegerProperty clean_sheetsProperty() {
//        return clean_sheets;
//    }
//
//    public void setClean_sheets(int clean_sheets) {
//        this.clean_sheets.set(clean_sheets);
//    }
    private String team_name;
    private int matches_played;
    private int wins;
    private int losses;
    private int draws;
    private int league_position;
    private int goals_scored;
    private int goals_conceded;
    private int goal_difference;
    private int clean_sheets;

    public String getTeam_name() {
        return team_name;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void setLeague_position(int league_position) {
        this.league_position = league_position;
    }

    public void setGoals_scored(int goals_scored) {
        this.goals_scored = goals_scored;
    }

    public void setGoals_conceded(int goals_conceded) {
        this.goals_conceded = goals_conceded;
    }

    public void setGoal_difference(int goal_difference) {
        this.goal_difference = goal_difference;
    }

    public void setClean_sheets(int clean_sheets) {
        this.clean_sheets = clean_sheets;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getLeague_position() {
        return league_position;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public int getGoals_conceded() {
        return goals_conceded;
    }

    public int getGoal_difference() {
        return goal_difference;
    }

    public int getClean_sheets() {
        return clean_sheets;
    }

    public LeagueData(String team_name, int league_position, int matches_played, int wins, int draws, int losses, int goals_scored, int goals_conceded, int goal_difference, int clean_sheets){
//        this.team_name = new SimpleStringProperty(team_name);
//        this.league_position = new SimpleIntegerProperty(league_position);
//        this.matches_played = new SimpleIntegerProperty(matches_played);
//        this.wins = new SimpleIntegerProperty(wins);
//        this.draws = new SimpleIntegerProperty(draws);
//        this.losses = new SimpleIntegerProperty(losses);
//        this.goals_scored = new SimpleIntegerProperty(goals_scored);
//        this.goals_conceded = new SimpleIntegerProperty(goals_conceded);
//        this.goal_difference = new SimpleIntegerProperty(goal_difference);
//        this.clean_sheets = new SimpleIntegerProperty(clean_sheets);
        this.team_name = team_name;
        this.league_position = league_position;
        this.matches_played = matches_played;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goals_scored = goals_scored;
        this.goals_conceded = goals_conceded;
        this.goal_difference = goal_difference;
        this.clean_sheets = clean_sheets;
    }
    public void printLeague(){
        System.out.println(getTeam_name()+" "+getLeague_position()+" "+getMatches_played()+" "+getWins()+" "+getDraws()+" "+getLosses()+" "+getGoal_difference());
    }
}
