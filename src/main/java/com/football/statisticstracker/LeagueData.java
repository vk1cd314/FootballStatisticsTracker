package com.football.statisticstracker;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LeagueData {
    private final StringProperty team_name;
    private final StringProperty matches_played;
    private final StringProperty wins;
    private final StringProperty draws;
    private final StringProperty losses;
    private final StringProperty league_position;
    private final StringProperty goals_scored;
    private final StringProperty goals_conceded;
    private final StringProperty goal_difference;
    private final StringProperty clean_sheets;
    public String getTeam_name() {
        return team_name.get();
    }

    public StringProperty team_nameProperty() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name.set(team_name);
    }

    public String getMatches_played() {
        return matches_played.get();
    }

    public StringProperty matches_playedProperty() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
    this.matches_played.set(String.valueOf(matches_played));
    }

    public String getWins() {
        return wins.get();
    }

    public StringProperty winsProperty() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins.set(String.valueOf(wins));
    }

    public String getDraws() {
        return draws.get();
    }

    public StringProperty drawsProperty() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws.set(draws);
    }

    public String getLosses() {
        return losses.get();
    }

    public StringProperty lossesProperty() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses.set(String.valueOf(losses));
    }

    public String getLeague_position() {
        return league_position.get();
    }

    public StringProperty league_positionProperty() {
        return league_position;
    }

    public void setLeague_position(int league_position) {
        this.league_position.set(String.valueOf(league_position));
    }

    public String getGoals_scored() {
        return goals_scored.get();
    }

    public StringProperty goals_scoredProperty() {
        return goals_scored;
    }

    public void setGoals_scored(int goals_scored) {
        this.goals_scored.set(String.valueOf(goals_scored));
    }

    public String getGoals_conceded() {
        return goals_conceded.get();
    }

    public StringProperty goals_concededProperty() {
        return goals_conceded;
    }

    public void setGoals_conceded(int goals_conceded) {
        this.goals_conceded.set(String.valueOf(goals_conceded));
    }

    public String getGoal_difference() {
        return goal_difference.get();
    }

    public StringProperty goal_differenceProperty() {
        return goal_difference;
    }

    public void setGoal_difference(int goal_difference) {
        this.goal_difference.set(String.valueOf(goal_difference));
    }

    public String getClean_sheets() {
        return clean_sheets.get();
    }

    public StringProperty clean_sheetsProperty() {
        return clean_sheets;
    }

    public void setClean_sheets(String clean_sheets) {
        this.clean_sheets.set(clean_sheets);
    }
    public LeagueData(String team_name, int league_position, int matches_played, int wins,  int draws, int losses, int goals_scored, int goals_conceded, int goal_difference, int clean_sheets){
        this.team_name = new SimpleStringProperty(team_name);
        this.league_position = new SimpleStringProperty(String.valueOf(league_position));
        this.matches_played = new SimpleStringProperty(String.valueOf(matches_played));
        this.wins = new SimpleStringProperty(String.valueOf(wins));
        this.draws = new SimpleStringProperty(String.valueOf(draws));
        this.losses = new SimpleStringProperty(String.valueOf(losses));
        this.goals_scored = new SimpleStringProperty(String.valueOf(goals_scored));
        this.goals_conceded = new SimpleStringProperty(String.valueOf(goals_conceded));
        this.goal_difference = new SimpleStringProperty(String.valueOf(goal_difference));
        this.clean_sheets = new SimpleStringProperty(String.valueOf(clean_sheets));
    }
}
