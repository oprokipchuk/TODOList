package controller.auth.register;

import dao.UserDAO;
import utils.encryption.Encryptor;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterHandlerServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        User newUser = new User(0, userPassword);

        try {
            if (userDAO.checkLogin(userLogin) == false) {
                String encryptedPassword = Encryptor.md5Custom(userPassword);
                userDAO.addUser(newUser, encryptedPassword);
                request.getSession().setAttribute("User", newUser);
                request.getRequestDispatcher("/WEB-INF/view/auth/successfulRegister.jsp").forward(request, response);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        request.getRequestDispatcher("/register").forward(request, response);
    }

}
