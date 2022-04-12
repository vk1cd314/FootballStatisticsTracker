package leagues;

public class LeagueData {
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

    public LeagueData(String team_name, int league_position) {
        this.team_name = team_name;
        this.league_position = league_position;
    }

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

    public LeagueData(String team_name, int league_position, int matches_played, int wins, int draws,
                      int losses, int goals_scored, int goals_conceded, int goal_difference, int clean_sheets) {
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

    public void printLeague() {
        System.out.println(getTeam_name() + " " + getLeague_position() + " " + getMatches_played() + " " + getWins() + " " + getDraws() + " " + getLosses() + " " + getGoal_difference());
    }
}
