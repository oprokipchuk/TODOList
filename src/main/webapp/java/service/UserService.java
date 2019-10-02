package service;

import dao.UserDAO;
import entity.User;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public boolean checkLogin(String login) throws SQLException {
        return !(userDAO.getLogin(login) == null);
    }

    public void addUser(User user, String password) throws SQLException {
        userDAO.addUser(user, password);
    }

}
