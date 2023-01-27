package db;

import vw.PlayerVW;
import vw.TeamVW;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDaoDS {
    private static final String FIND_BY_EMAIL = "SELECT * FROM team WHERE useremail=?";
    private final String selectQuery = "SELECT * FROM team";
    private final String deleteQuery = "DELETE FROM \"user\" WHERE email = ?";
    private static final String SIGN_UP = "INSERT INTO team (useremail, \"name\", leader) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM team WHERE idteam = ?";
    private static final String GET_PLAYERS_QUERY = "SELECT p.useremail FROM team t INNER JOIN player_team pt ON t.idTeam = pt.teamidTeam INNER JOIN player p ON pt.playeridPlayer = p.idPlayer WHERE t.idTeam = ?";
    private static final String DELETE_PLAYER_BY_ID = "DELETE FROM player_team pt where pt.teamidteam = ? AND pt.playeridplayer = ?";
    private static final String DELETE_EVENT_BY_ID = "DELETE FROM announcement where teamidteam = ? AND idevent = ?";

    public List<TeamVW> getTeams() {
        List<TeamVW> teams = new ArrayList<>();
        try (Connection connection = DataSource.getConnection(); PreparedStatement pst = connection.prepareStatement(selectQuery)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TeamVW team = new TeamVW();
                team.setIdteam(rs.getInt("idteam"));
                team.setUseremail(rs.getString("useremail"));
                team.setName(rs.getString("name"));
                team.setLeader(rs.getString("leader"));
                team.setPlayers(getPlayers(team.getIdteam()));
                teams.add(team);
            }
        } catch (Exception e) {
            throw new RuntimeException("No se a podido listar los equipos", e);
        }

        return teams;
    }

    public boolean deleteTeam(String mail) {
        boolean res = false;
        try (Connection connection = DataSource.getConnection(); PreparedStatement pst = connection.prepareStatement(deleteQuery)) {
            pst.setString(1, mail);
            if (pst.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in delete", e);
        }
        return res;
    }

    public boolean signUp(TeamVW team) {
        boolean signedUp = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP);) {
            preparedStatement.setString(1, team.getUseremail());
            preparedStatement.setString(2, team.getName());
            preparedStatement.setString(3, team.getLeader());
            int affected = preparedStatement.executeUpdate();
            if (affected == 1) signedUp = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return signedUp;
    }

    public TeamVW findById(int idTeam) {
        TeamVW team = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setInt(1, idTeam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team = new TeamVW();
                team.setIdteam(resultSet.getInt("idteam"));
                team.setName(resultSet.getString("name"));
                team.setLeader(resultSet.getString("leader"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    public TeamVW getTeamListedById(int idTeam) {
        TeamVW team = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setInt(1, idTeam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team = new TeamVW();
                team.setIdteam(resultSet.getInt("idteam"));
                team.setName(resultSet.getString("name"));
                team.setLeader(resultSet.getString("leader"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    public List<PlayerVW> getPlayers(int idTeam) {
        List<PlayerVW> players = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PLAYERS_QUERY);) {
            preparedStatement.setInt(1, idTeam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlayerDaoDS player = new PlayerDaoDS();
                players.add(player.getPlayerListedByEmail(resultSet.getString("useremail")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    public boolean deletePlayerById(int idTeam, int idPlayer) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAYER_BY_ID);) {
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setInt(2, idPlayer);
            if (preparedStatement.executeUpdate() == 1) deleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    public boolean deleteEventById(int idteam, int idEvent) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENT_BY_ID)) {
            preparedStatement.setInt(1, idteam);
            preparedStatement.setInt(2, idEvent);
            if (preparedStatement.executeUpdate() == 1) deleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    public TeamVW findByEmail(String email) {
        TeamVW team = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team = new TeamVW();
                team.setIdteam(resultSet.getInt("idteam"));
                team.setUseremail(resultSet.getString("useremail"));
                team.setName(resultSet.getString("name"));
                team.setLeader(resultSet.getString("leader"));
                team.setPlayers(getPlayers(team.getIdteam()));
                team.setEvents(new EventDaoDS().getTeamEvents(team.getIdteam()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return team;
    }
}
