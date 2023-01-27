package vw;

public class GameVW {
    private int idGame;
    private String title;
    private String desc;

    public GameVW() {
    }

    public GameVW(int idGame, String title, String desc) {
        this.idGame = idGame;
        this.title = title;
        this.desc = desc;
    }

    public GameVW(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "GameVW{" +
                "idGame=" + idGame +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String printToPlayer() {
        return "{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
