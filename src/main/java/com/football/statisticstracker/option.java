package com.football.statisticstracker;

public enum option {
    Admin, User;

    private option() {}
    public String value() { return name(); }
    public static option fromValue(String v) { return valueOf(v); }
}
