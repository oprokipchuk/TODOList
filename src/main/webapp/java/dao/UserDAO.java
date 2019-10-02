package dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static Connection connection;

    public static void setConnection(Connection connection) {
        UserDAO.connection = connection;
    }

    public boolean checkLogin(String login) throws SQLException {
        String sql = "SELECT login FROM user WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet resultOfQuery = statement.executeQuery();

        boolean existsLogin = resultOfQuery.next();

        statement.close();
        return existsLogin;
    }

    public String getLogin(String login) throws SQLException {
        String sql = "SELECT login FROM user WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet resultOfQuery = statement.executeQuery();

        if (resultOfQuery.next()) {
            String loginToReturn = resultOfQuery.getString(1);
            statement.close();
            return loginToReturn;
        }
        else {
            statement.close();
            return null;
        }
    }

    public void addUser(User user, String password) throws SQLException {
        String sql = "INSERT INTO user (login, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getLogin());
        statement.setString(2, password);
        statement.executeUpdate();
        statement.close();
    }

    public User getUserByLogin(String login) throws SQLException {

        String sql = "SELECT id_user, login FROM user WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String userLogin = resultSet.getString(2);
            User user = new User(userId, userLogin);
            statement.close();
            return user;
        }
        else {
            statement.close();
            return null;
        }
    }

    public boolean checkPassword(String login, String password) throws SQLException {

        String sql = "SELECT login FROM user WHERE login = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        boolean result = resultSet.next();
        statement.close();

        return result;
    }

}
