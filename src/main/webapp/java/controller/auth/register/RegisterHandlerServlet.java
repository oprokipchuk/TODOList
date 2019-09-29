package controller.auth.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterHandlerServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");

        

    }

}
