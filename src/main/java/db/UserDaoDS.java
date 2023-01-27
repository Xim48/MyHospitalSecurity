package db;

import vw.UserVW;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDS {
    private static final String FIND_ALL_QUERY = "SELECT * FROM \"user\"";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM \"user\" where email=?";
    private static final String SIGN_UP_QUERY = "INSERT INTO \"user\" (email, password, type) values (?, ?, ?)";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT * FROM \"user\" where email=? AND password=?";
    private static final String DELETE_QUERY = "DELETE FROM \"user\" where email=?";

    public List<UserVW> findAll() {
        List<UserVW> users = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserVW user = new UserVW();
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
                System.out.println("LOG: User found: " + user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public UserVW findByEmail(String email) {
        UserVW user = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserVW();
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public UserVW findByLogin(String email, String password) {
        UserVW user = null;
        System.out.println("Find By Login: email = " + email + "\tpassword: " + password);
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_QUERY);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new UserVW();
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getBoolean("type"));
                System.out.println("LOG: user found: " + user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean signUp(UserVW user) {
        boolean signedUp = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP_QUERY);) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword()); // Encryption
            preparedStatement.setBoolean(3, user.getType());
            int affected = preparedStatement.executeUpdate();
            if (affected == 1) signedUp = true;
            System.out.println("LOG: user signed up.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return signedUp;
    }

    public boolean delete(String email) {
        boolean deleted = false;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);) {
            preparedStatement.setString(1, email);
            int affected = preparedStatement.executeUpdate();
            if (affected == 1) {
                deleted = true;
                System.out.println("LOG: User " + email + " deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted;
    }
}
