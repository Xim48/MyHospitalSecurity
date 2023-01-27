package vw;

import java.util.List;

public class TeamVW {
    private int idteam;
    private String useremail;
    private String name;
    private String leader;
    private List<PlayerVW> players;
    private List<EventVW> events;

    public TeamVW() {
    }

    public TeamVW(int idteam, String useremail, String name, String leader, List<PlayerVW> players, List<EventVW> events) {
        this.idteam = idteam;
        this.useremail = useremail;
        this.name = name;
        this.leader = leader;
        this.players = players;
        this.events = events;
    }

    public TeamVW(String useremail, String name, String leader) {
        this.idteam = idteam;
        this.useremail = useremail;
        this.name = name;
        this.leader = leader;
    }

    public int getIdteam() {
        return idteam;
    }

    public void setIdteam(int idteam) {
        this.idteam = idteam;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public List<PlayerVW> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerVW> players) {
        this.players = players;
    }

    public List<EventVW> getEvents() {
        return events;
    }

    public void setEvents(List<EventVW> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "TeamVW{" +
                "idteam=" + idteam +
                ", useremail='" + useremail + '\'' +
                ", name='" + name + '\'' +
                ", leader='" + leader + '\'' +
                ", players=" + players +
                ", events=" + events +
                '}';
    }
}
