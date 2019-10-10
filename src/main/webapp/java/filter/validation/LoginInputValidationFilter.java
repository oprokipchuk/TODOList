package filter.validation;

import entity.User;
import utils.fieldchecker.AuthFieldChecker;
import utils.fieldchecker.error.ErrorCode;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInputValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
            filterChain.doFilter(request, response);
        }
        else {
            HttpSession session = httpRequest.getSession();

            String userLogin = request.getParameter("login").trim();
            String userPassword = request.getParameter("password").trim();

            AuthFieldChecker checker = new AuthFieldChecker();

            boolean hasErrors = false;
            ErrorCode loginOrPasswordError = checker.loginEmptyFieldsCheck(userLogin, userPassword);
            if (loginOrPasswordError.isError()) {
                hasErrors = true;
                session.setAttribute("incorrectLoginOrPasswordError", loginOrPasswordError.getMessage());
            }
            if (hasErrors) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                //session.setAttribute("incorrectUserData", newUser);
            } else {
                request.setAttribute("login", userLogin);
                request.setAttribute("password", userPassword);
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
