package teams;

import players.Player;

import java.util.ArrayList;

public class Team {
    public String name;
    public String common_name;

    public String league;

    public int position;
    public int matchesPlayed;
    public int wins;
    public int draws;
    public int losses;
    public int goalsScored;
    public int goalsConceded;
    public int goalDiff;
    public int cleanSheet;
    public int points;
    ArrayList<Player> players = new ArrayList<>();

    public Team(String name, String league, int mp, int w, int d, int l, int gs, int gc, int gd, int cs, String common_name, int position) {
        this.name = name;
        this.common_name = common_name;
        this.league = league;
        this.matchesPlayed = mp;
        this.wins = w;
        this.losses = l;
        this.draws = d;
        this.goalsScored = gs;
        this.goalsConceded = gc;
        this.goalDiff = gd;
        this.cleanSheet = cs;
        this.points = wins * 3 + draws;
        this.position = position;
    }

    public void addPlayer(Player x) {
        players.add(x);
    }

    public void addPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public int getCleanSheet() {
        return cleanSheet;
    }

    public int getPoints() {
        return points;
    }
    public void print(){
        System.out.println(name+" "+position+" "+points+" "+wins);
    }
}
