package teams;

import players.Player;

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
    int points;
    ArrayList<Player> players = new ArrayList<>();

    public Team(String name, int position, int mp, int w, int d, int l, int gs, int gc, int gd, int cs) {
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
        this.points = wins * 3 + draws;
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
