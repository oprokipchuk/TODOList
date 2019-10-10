package controller.auth.register;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        request.setAttribute("loginError", session.getAttribute("loginError"));
        request.setAttribute("passwordError", session.getAttribute("passwordError"));
        request.setAttribute("emailError", session.getAttribute("emailError"));
        request.setAttribute("incorrectUserData", session.getAttribute("incorrectUserData"));
        request.setAttribute("alreadyExistingUserError", session.getAttribute("alreadyExistingUserError"));

        session.invalidate();

        request.getRequestDispatcher("/WEB-INF/view/auth/register.jsp").forward(request, response);
    }
}
