package players;

public enum Positions {
    Forward, Midfielder, Defender, Goalkeeper;

    Positions() {
    }

    public String value() {
        return name();
    }

    public static Positions fromValue(String v) {
        return valueOf(v);
    }
}
