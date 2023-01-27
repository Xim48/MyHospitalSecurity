package vw;

import java.sql.Date;
import java.time.LocalDate;

public class EventVW {
    int idevent;
    private String name;
    private String location;
    private String category;
    private Date date;
    private String prize;

    public EventVW() {
    }

    //this is only the model
    public EventVW(int idevent, String name, String location, String category, Date date, String prize) {
        this.idevent = idevent;
        this.name = name;
        this.location = location;
        this.category = category;
        this.date = date;
        this.prize = prize;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "EventVW{" +
                "idevent=" + idevent +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", prize='" + prize + '\'' +
                '}';
    }
}
