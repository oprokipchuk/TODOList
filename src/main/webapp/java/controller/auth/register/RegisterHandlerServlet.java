package controller.auth.register;

import dao.UserDAO;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegisterHandlerServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        User newUser = new User(0, userPassword);

        if (userDAO.checkLogin(userLogin) == false) {
            userDAO.addUser(newUser, userPassword);
        }
        else {

        }

    }

}
