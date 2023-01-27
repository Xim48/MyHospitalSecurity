package db;

import vw.EventVW;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDaoDS {
    // query to get the information on the database
    private final String eventsQuery = "SELECT * FROM announcement";
    private final String deleteQuery = "DELETE FROM announcement where idevent = ?";
    private final String insertQuery = "INSERT INTO announcement (name, location, category, dateevent, prize) values(?, ?, ?, ?, ?)";
    private final String updateQuery = "UPDATE announcement set name = ?, location = ?, category = ?, dateevent = ?, prize = ? where idevent = ?";
    private static final String INSERT_EVENT_TEAM = "INSERT into announcement (teamidteam, name, location, category, dateevent, prize,gameidgame) values (?,?,?,?,?,?,?)";
    private static final String GET_TEAM_EVENTS = "SELECT * FROM announcement where teamidteam = ?";

    // method to get all the announcements
    public List<EventVW> getEvents() {
        // create the object to return with all events
        List<EventVW> events = new ArrayList<>();
        // make the connection to the database and try the SQL query
        try (Connection connection = DataSource.getConnection(); PreparedStatement pst = connection.prepareStatement(eventsQuery)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Get all the properties from database and add to our view
                EventVW event = new EventVW();
                event.setIdevent(rs.getInt("idevent"));
                event.setName(rs.getString("name"));
                event.setLocation(rs.getString("location"));
                event.setCategory(rs.getString("category"));
                event.setDate(rs.getDate("dateevent"));
                event.setPrize(rs.getString("prize"));

                // add the current event to the List to return
                events.add(event);
            }
        } catch (Exception e) {
            throw new RuntimeException("error getEvents ", e);
        }
        // return all the events
        return events;
    }

    public List<EventVW> getTeamEvents(int idTeam) {
        List<EventVW> events = new ArrayList<>();
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAM_EVENTS)) {
            preparedStatement.setInt(1, idTeam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EventVW event = new EventVW();
                event.setIdevent(resultSet.getInt("idevent"));
                event.setName(resultSet.getString("name"));
                event.setLocation(resultSet.getString("location"));
                event.setCategory(resultSet.getString("category"));
                event.setDate(resultSet.getDate("dateevent"));
                event.setPrize(resultSet.getString("prize"));
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    public boolean deleteEvent(int id) {
        boolean res = false;
        try (Connection connection = DataSource.getConnection(); PreparedStatement pst = connection.prepareStatement(deleteQuery)) {
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("error deleteEvent", e);
        }
        return res;
    }

    public boolean insertEvent(EventVW event) {
        boolean res = false;
        try (Connection con = DataSource.getConnection(); PreparedStatement pst = con.prepareStatement(insertQuery)) {
            pst.setString(1, event.getName());
            pst.setString(2, event.getLocation());
            pst.setString(3, event.getCategory());
            pst.setDate(4, event.getDate());
            pst.setString(5, event.getPrize());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inserting ", e);
        }
        return res;
    }

    public boolean addEventTeam(int idteam, EventVW event) {
        boolean added = false;
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_TEAM)) {
            preparedStatement.setInt(1, idteam);
            preparedStatement.setString(2, event.getName());
            preparedStatement.setString(3, event.getLocation());
            preparedStatement.setString(4, event.getCategory());
            preparedStatement.setDate(5, event.getDate());
            preparedStatement.setString(6, event.getPrize());
            preparedStatement.setInt(7, 1);
            if (preparedStatement.executeUpdate() == 1) added = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return added;
    }

    public boolean updateEvent(EventVW event) {
        boolean res = false;
        try (Connection connection = DataSource.getConnection(); PreparedStatement pst = connection.prepareStatement(updateQuery)) {
            pst.setString(1, event.getName());
            pst.setString(2, event.getLocation());
            pst.setString(3, event.getCategory());
            pst.setDate(4, event.getDate());
            pst.setString(5, event.getPrize());
            pst.setInt(6, event.getIdevent());

            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error update ", e);
        }
        return res;
    }
}
