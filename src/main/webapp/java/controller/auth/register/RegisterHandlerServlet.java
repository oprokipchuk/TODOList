package controller.auth.register;

import dao.UserDAO;
import service.UserService;
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

    private UserService userService;

    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userEmail = request.getParameter("email");
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");

        User newUser = new User(0, userLogin);
        newUser.setEmail(userEmail);
        try {
            if (userService.checkLogin(userLogin) == false && userService.checkEmail(userEmail) == false) {
                String encryptedPassword = Encryptor.md5Custom(userPassword);
                userService.addUser(newUser, encryptedPassword);
                session.setAttribute("User", newUser);
                request.getRequestDispatcher("/WEB-INF/view/auth/successfulRegister.jsp").forward(request, response);
                return;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        session.setAttribute("alreadyExistingUserError", "user already exists");
        response.sendRedirect(request.getContextPath() + "/register");
    }

}
