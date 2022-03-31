package com.football.statisticstracker;

import java.util.ArrayList;

public class MyTeam extends Team {
    private int goalKeeperNumber;
    private int defenderNumber;
    private int midfielderNumber;
    private int forwardNumber;
    private int goalkeeperLimit;
    private int defenderLimit;
    private int midfielderLimit;
    private int forwardLimit;
    private final ArrayList<Goalkeeper> goalkeepers = new ArrayList<>();
    private final ArrayList<Defender> defenders = new ArrayList<>();
    private final ArrayList<Midfielder> midfielders = new ArrayList<>();
    private final ArrayList<Forward> forwards = new ArrayList<>();

    public MyTeam(String name, int position, int mp, int w, int l, int d, int gs, int gc, int gd, int cs) {
        super(name, 0, mp, 0, 0, 0, gs, gc, gd, cs);
        goalKeeperNumber = 0;
        goalkeeperLimit = 2;
        defenderNumber = 0;
        defenderLimit = 5;
        midfielderNumber = 0;
        midfielderLimit = 6;
        forwardNumber = 0;
        forwardLimit = 3;
    }

    public void setGoalKeeperNumber(int goalKeeperNumber) {
        this.goalKeeperNumber = goalKeeperNumber;
    }

    public void setGoalkeeperLimit(int goalkeeperLimit) {
        this.goalkeeperLimit = goalkeeperLimit;
    }

    public void setDefenderLimit(int defenderLimit) {
        this.defenderLimit = defenderLimit;
    }

    public void setMidfielderLimit(int midfielderLimit) {
        this.midfielderLimit = midfielderLimit;
    }

    public void setForwardLimit(int forwardLimit) {
        this.forwardLimit = forwardLimit;
    }

    public void addGoalkeeper(Goalkeeper x) {
        if (goalKeeperNumber < goalkeeperLimit) {
            this.goalkeepers.add(x);
            goalKeeperNumber++;
        }
    }

    public void addDefender(Defender x) {
        if (defenderNumber < defenderLimit) {
            this.defenders.add(x);
            defenderNumber++;
        }
    }

    public void addMidfielder(Midfielder x) {
        if (midfielderNumber < midfielderLimit) {
            this.midfielders.add(x);
            midfielderNumber++;
        }
    }

    public void addForward(Forward x) {
        if (forwardNumber < forwardLimit) {
            this.forwards.add(x);
            forwardNumber++;
        }
    }
}
