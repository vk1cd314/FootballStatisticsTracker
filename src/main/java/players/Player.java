package players;

public class Player {
    public String name;
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

    public Player(String pos, String name, int age, String birthday, String league,
                  String club, String nation, int app, int goals, int ass, int cs, int rc, int yc) {
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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLeague() {
        return league;
    }

    public String getPosition() {
        return position;
    }

    public String getClub() {
        return club;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAppearances() {
        return appearances;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public int getAssists() {
        return assists;
    }

    public int getClean_sheets() {
        return clean_sheets;
    }

    public int getRed_cards() {
        return red_cards;
    }

    public int getYellow_cards() {
        return yellow_cards;
    }

}
