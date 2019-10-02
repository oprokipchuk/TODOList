package filter.auth;

import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IsUserLoggedCheckerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        System.out.println("loggedCheckerFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        User user = (User) session.getAttribute("User");
        if (user == null){
            System.out.println("User not found");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        else {
            System.out.println(user.getLogin());
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
