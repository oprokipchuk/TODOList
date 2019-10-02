package service;

import dao.UserDAO;
import entity.User;
import utils.encryption.Encryptor;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public boolean checkLogin(String login) throws SQLException {
        return !(userDAO.getLogin(login) == null);
    }

    public boolean checkPassword(String login, String password) throws SQLException {

        String encodedPassword = Encryptor.md5Custom(password);
        return userDAO.checkPassword(login, encodedPassword);
    }

    public User getUserByLogin(String login) throws SQLException {
        return userDAO.getUserByLogin(login);
    }

    public boolean checkUser(User user, String password) throws SQLException {

        User checkedUser = getUserByLogin(user.getLogin());
        if (checkedUser != null && checkPassword(user.getLogin(), password)) {
            return true;
        }
        return false;
    }

    public void addUser(User user, String password) throws SQLException {
        userDAO.addUser(user, password);
    }

}
