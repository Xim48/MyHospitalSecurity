package vw;

public class PlayerGameVw extends GameVW {

    private String level;

    public PlayerGameVw() {
    }

    public PlayerGameVw(int idGame, String title, String desc, String level) {
        super(idGame, title, desc);
        this.level = level;
    }

    public PlayerGameVw(String title, String desc, String level) {
        super(title, desc);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "PlayerGameVw{" +
                "level='" + level + '\'' +
                "} " + super.toString();
    }
}
