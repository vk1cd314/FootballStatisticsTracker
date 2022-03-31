package com.football.statisticstracker;

public enum option {
    Admin, User;

    option() {
    }

    public String value() {
        return name();
    }

    public static option fromValue(String v) {
        return valueOf(v);
    }
}
