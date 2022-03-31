package com.football.statisticstracker;

import java.util.ArrayList;

public class MyTeam extends Team{
    private int goalkeepernumber;
    private int defendernumber;
    private int midfieldernumber;
    private int forwardnumber;
    private int goalkeeperlimit;
    private int defenderlimit;
    private int midfielderlimit;
    private int forwardlimit;
    private ArrayList<Goalkeeper> goalkeepers = new ArrayList<>();
    private ArrayList<Defender> defenders = new ArrayList<>();
    private ArrayList<Midfielder> midfielders = new ArrayList<>();
    private ArrayList<Forward> forwards = new ArrayList<>();
    public MyTeam(String name, int position, int mp, int w, int l, int d, int gs, int gc, int gd, int cs) {
        super(name, 0, mp, 0, 0, 0, gs, gc, gd, cs);
        goalkeepernumber = 0;
        goalkeeperlimit = 2;
        defendernumber = 0;
        defenderlimit = 5;
        midfieldernumber = 0;
        midfielderlimit = 6;
        forwardnumber = 0;
        forwardlimit = 3;
    }
    public void setGoalkeepernumber(int goalkeepernumber) {
        this.goalkeepernumber = goalkeepernumber;
    }

    public void setGoalkeeperlimit(int goalkeeperlimit) {
        this.goalkeeperlimit = goalkeeperlimit;
    }

    public void setDefenderlimit(int defenderlimit) {
        this.defenderlimit = defenderlimit;
    }

    public void setMidfielderlimit(int midfielderlimit) {
        this.midfielderlimit = midfielderlimit;
    }

    public void setForwardlimit(int forwardlimit) {
        this.forwardlimit = forwardlimit;
    }

    public void addGoalkeeper(Goalkeeper x){
        if(goalkeepernumber<goalkeeperlimit){
            this.goalkeepers.add(x);
            goalkeepernumber++;
        }
    }
    public void addDefender(Defender x){
        if(defendernumber<defenderlimit){
            this.defenders.add(x);
            defendernumber++;
        }
    }
    public void addMidfielder(Midfielder x){
        if(midfieldernumber<midfielderlimit){
            this.midfielders.add(x);
            midfieldernumber++;
        }
    }
    public void addForward(Forward x){
        if(forwardnumber<forwardlimit){
            this.forwards.add(x);
            forwardnumber++;
        }
    }
}
