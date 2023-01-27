package db;

import vw.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoDS {
    private static final String FIND_ALL_QUERY = "SELECT * FROM player";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM player WHERE useremail=?";
    private static final String SIGN_UP_QUERY = "INSERT INTO player (useremail, \"name\", middlelastname, lastname) values (?, ?, ?, ?)";
    private static final String ADD_TEAM_BY_ID = "INSERT INTO player_team (playeridplayer, teamidteam) VALUES (?,?)";
    private static final String ADD_GAME_BY_ID = "INSERT INTO player_game (playeridplayer, gameidgame, level) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE player SET \"name\"=?, middlelastname=?, lastname=? WHERE idplayer=?";
    private static final String DELETE_QUERY = "DELETE FROM \"user\" where email=?";
    private static final String DELETE_TEAM_BY_ID = "DELETE FROM player_team pt where pt.playeridplayer = ? AND pt.teamidteam = ?";
    private static final String GET_TEAMS_QUERY = "SELECT t.idTeam, p.name FROM player p INNER JOIN player_team pt on p.idPlayer = pt.playeridPlayer INNER JOIN team t ON pt.teamidTeam = t.idTeam WHERE p.idPlayer = ?";
    private static final String DELETE_GAME_BY_ID = "DELETE FROM player_game pg where pg.playeridplayer = ? AND pg.gameidgame = ?";


    public List<PlayerVW> findAll() {
        List<PlayerVW> players = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlayerVW player = assignPlayerVules(resultSet);
                player.setTeams(getTeams(player.getIdPlayer()));
                player.setGames(new GameDaoDS().findByPlayerId(player.getIdPlayer()));
                players.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    public PlayerVW findByEmail(String email) {
        PlayerVW player = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                player = assignPlayerVules(resultSet);
                player.setTeams(getTeams(player.getIdPlayer()));
                player.setGames(new GameDaoDS().findByPlayerId(player.getIdPlayer()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    public PlayerVW getPlayerListedByEmail(String email) {
        PlayerVW player = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                player = new PlayerVW();
                player.setIdPlayer(resultSet.getInt("idplayer"));
                player.setName(resultSet.getString("name"));
                player.setMiddleLastName(resultSet.getString("middlelastname"));
                player.setLastName(resultSet.getString("lastname"));
                player.setEmail(resultSet.getString("useremail"));
                player.setGames(new GameDaoDS().findByPlayerId(player.getIdPlayer()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    public boolean signUp(PlayerVW player) {
        boolean signepUp = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP_QUERY);) {
            preparedStatement.setString(1, player.getEmail());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, player.getMiddleLastName());
            preparedStatement.setString(4, player.getLastName());
            int affected = preparedStatement.executeUpdate();
            if (affected == 1) signepUp = true;
        } catch (SQLException e) {
            System.out.println("LOG: El usuario " + player.getEmail() + " no ha podido ser registrado.");
            throw new RuntimeException(e);
        }
        return signepUp;
    }

    public boolean updatePlayer(PlayerVW player) {
        boolean updated = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getMiddleLastName());
            preparedStatement.setString(3, player.getLastName());
            preparedStatement.setString(4, player.getRole());
            preparedStatement.setInt(5, player.getIdPlayer());
            int affected = preparedStatement.executeUpdate();
            if (affected == 1) updated = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updated;
    }

    public boolean deletePlayer(String email) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);) {
            preparedStatement.setString(1, email);
            int affected = preparedStatement.executeUpdate();
            if (affected == 1) deleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    /*public void addGame(PlayerVW player, GameVW game) {
        new GameDaoDS().addGame(player.getIdPlayer(), game.getTitle(), game.getLevel());
    }*/

    public void deleteGame(PlayerVW player, GameVW game) {
        if (new GameDaoDS().removeGame(player.getIdPlayer(), game.getIdGame())) {
            System.out.println("LOG: Se eliminó " + game.getTitle());
        } else {
            System.out.println("LOG: No se eliminó " + game.getTitle());
        }
    }

    public List<TeamVW> getTeams(int idPlayer) {
        List<TeamVW> teams = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAMS_QUERY);) {
            preparedStatement.setInt(1, idPlayer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TeamDaoDS teamDaoDS = new TeamDaoDS();
                teams.add(teamDaoDS.getTeamListedById(resultSet.getInt("idteam")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teams;
    }

    public boolean addTeamById(int idPlayer, int idTeam) {
        boolean added = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TEAM_BY_ID)) {
            preparedStatement.setInt(1, idPlayer);
            preparedStatement.setInt(2, idTeam);
            if (preparedStatement.executeUpdate() == 1) added = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return added;
    }

    public boolean addGameById(int idPlayer, int idGame, String level) {
        boolean added = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_GAME_BY_ID)) {
            preparedStatement.setInt(1, idPlayer);
            preparedStatement.setInt(2, idGame);
            preparedStatement.setString(3, level);
            if (preparedStatement.executeUpdate() == 1) added = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return added;
    }

    public boolean deleteTeamById(int idPlayer, int idTeam) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEAM_BY_ID)) {
            preparedStatement.setInt(1, idPlayer);
            preparedStatement.setInt(2, idTeam);
            if (preparedStatement.executeUpdate() == 1) {
                deleted = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    public boolean deleteGameById(int idPlayer, int idGame) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GAME_BY_ID)) {
            preparedStatement.setInt(1, idPlayer);
            preparedStatement.setInt(2, idGame);
            if (preparedStatement.executeUpdate() == 1) {
                deleted = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }

    private PlayerVW assignPlayerVules(ResultSet resultSet) throws SQLException {
        PlayerVW player = new PlayerVW();
        player.setIdPlayer(resultSet.getInt("idplayer"));
        player.setEmail(resultSet.getString("useremail"));
        player.setPassword(new UserDaoDS().findByEmail(player.getEmail()).getPassword());
        player.setName(resultSet.getString("name"));
        player.setMiddleLastName(resultSet.getString("middlelastname"));
        player.setLastName(resultSet.getString("lastname"));
        return player;
    }
}
