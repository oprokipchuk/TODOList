package filter.validation;

import utils.fieldchecker.GroupTaskFieldChecker;
import utils.fieldchecker.error.ErrorCode;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GroupInputValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            HttpSession session = httpRequest.getSession();

            String groupName = request.getParameter("groupName").trim();

            GroupTaskFieldChecker checker = new GroupTaskFieldChecker();

            boolean hasErrors = false;
            ErrorCode groupNameError = checker.chechNewGroupName(groupName);
            if (groupNameError.isError()) {
                hasErrors = true;
                //session.setAttribute("incorrectLoginOrPasswordError", loginOrPasswordError.getMessage());
            }
            if (hasErrors) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                //session.setAttribute("incorrectUserData", newUser);
            } else {
                request.setAttribute("groupName", groupName);
                filterChain.doFilter(request, response);
            }
        }
        else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
