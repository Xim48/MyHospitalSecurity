package vw;

import java.util.List;

public class PlayerVW extends UserVW {
    private int idPlayer;
    private List<TeamVW> teams;
    private String name;
    private String middleLastName;
    private String lastName;
    private String role;
    private List<GameVW> games;

    public PlayerVW() {
    }

    public PlayerVW(int idPlayer, String email, String password, List<TeamVW> teams, String name, String middleLastName, String lastName, String role, List<GameVW> games) {
        super(email, password);
        this.idPlayer = idPlayer;
        this.teams = teams;
        this.name = name;
        this.middleLastName = middleLastName;
        this.lastName = lastName;
        this.role = role;
        this.games = games;
    }

    public PlayerVW(String email, String password, String name, String middleLastName, String lastName) {
        super(email, password);
        this.name = name;
        this.middleLastName = middleLastName;
        this.lastName = lastName;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public List<TeamVW> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamVW> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleLastName() {
        return middleLastName;
    }

    public void setMiddleLastName(String middleLastName) {
        this.middleLastName = middleLastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GameVW> getGames() {
        return games;
    }

    public void setGames(List<GameVW> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "PlayerVW{" +
                "idPlayer=" + idPlayer +
                ", teams=" + teams +
                ", name='" + name + '\'' +
                ", middleLastName='" + middleLastName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", role='" + role + '\'' +
                ", games=" + games +
                '}';
    }
}
