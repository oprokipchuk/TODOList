package controller.auth.login;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    private UserService userService;

    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        request.setAttribute("incorrectLoginOrPasswordError", session.getAttribute("incorrectLoginOrPasswordError"));

        session.invalidate();
        request.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userLogin = request.getParameter("login").trim();
        String userPassword = request.getParameter("password").trim();

        try {
            User user = userService.getUserByLogin(userLogin);
            if (user != null && userService.checkUser(user, userPassword)) {
                session.setAttribute("User", user);
                response.sendRedirect(request.getContextPath() + "/groups");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("incorrectLoginOrPasswordError", "Login or Password is incorrect");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
