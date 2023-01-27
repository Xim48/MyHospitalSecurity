package db;

import vw.GameVW;
import vw.PlayerGameVw;
import vw.PlayerVW;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDaoDS {
    private static final String FIND_ALL_QUERY = "SELECT * FROM game";
    private static final String FIND_BY_PLAYER_ID = "SELECT g.idgame, g.title, g.desc, pg.level FROM player p INNER JOIN player_game pg ON p.idplayer = pg.playeridplayer INNER JOIN game g ON pg.gameidgame = g.idgame WHERE p.idplayer = ?";
    private static final String INSERT_GAME_QUERY = "INSERT INTO player_game(playeridplayer, gameidgame, level) values (?, ?, ?)";
    private static final String DELETE_GAME_QUERY = "DELETE FROM player_game WHERE playeridPlayer=? AND gameidgame=?";

    public List<GameVW> findAll() {
        List<GameVW> games = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GameVW game = new GameVW();
                game.setIdGame(resultSet.getInt("idGame"));
                game.setTitle(resultSet.getString("title"));
                game.setDesc(resultSet.getString("desc"));
                games.add(game);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    public List<GameVW> findByPlayerId(int idPlayer) {
        List<GameVW> games = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PLAYER_ID);) {
            preparedStatement.setInt(1, idPlayer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlayerGameVw game = new PlayerGameVw();
                game.setIdGame(resultSet.getInt("idgame"));
                game.setTitle(resultSet.getString("title"));
                game.setDesc(resultSet.getString("desc"));
                game.setLevel(resultSet.getString("level"));
                games.add(game);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    public boolean addGame(int idPlayer, String title, String level) {
        boolean added = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GAME_QUERY);) {
            preparedStatement.setInt(1, idPlayer);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, level);
            if (preparedStatement.executeUpdate() == 1) added = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return added;
    }

    public boolean removeGame(int idPlayer, int idGame) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GAME_QUERY);) {
            preparedStatement.setInt(1, idPlayer);
            preparedStatement.setInt(2, idGame);
            if (preparedStatement.executeUpdate() == 1) deleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }
}
