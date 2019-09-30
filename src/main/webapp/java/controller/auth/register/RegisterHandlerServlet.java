package controller.auth.register;

import dao.UserDAO;
import utils.encryption.Encryptor;
import entity.User;
import utils.fieldchecker.AuthFieldChecker;
import utils.fieldchecker.error.ErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterHandlerServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userLogin = request.getParameter("login").trim();
        String userPassword = request.getParameter("password").trim();

        User newUser = new User(0, userLogin);
        AuthFieldChecker checker = new AuthFieldChecker();

        boolean hasErrors = false;
        ErrorCode loginError = checker.checkLogin(userLogin);
        if (loginError.isError()) {
            hasErrors = true;
            session.setAttribute("loginError", loginError.getMessage());
        }
        ErrorCode passwordError = checker.checkPassword(userPassword);
        if (passwordError.isError()) {
            hasErrors = true;
            session.setAttribute("passwordError", passwordError.getMessage());
        }

        if (hasErrors) {
            response.sendRedirect(request.getContextPath() + "/register");
            session.setAttribute("incorrectUserData", newUser);
        }
        else {

            try {
                if (userDAO.checkLogin(userLogin) == false) {
                    String encryptedPassword = Encryptor.md5Custom(userPassword);
                    userDAO.addUser(newUser, encryptedPassword);
                    session.setAttribute("User", newUser);
                    request.getRequestDispatcher("/WEB-INF/view/auth/successfulRegister.jsp").forward(request, response);
                    return;
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
