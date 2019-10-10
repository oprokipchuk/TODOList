package filter.redirection;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class GroupTaskURIRedirectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        System.out.println("In Redirection filter, path: " + request.getRequestURI());
        String[] pathParts = path.split("/");

        if (Pattern.compile(request.getContextPath() + "/groups/group/[0-9]+/tasks[/]?").matcher(path).matches()) {
            int groupNum = Integer.valueOf(pathParts[4]);
            request.setAttribute("groupNum", groupNum);
            request.getRequestDispatcher("/secure/GroupTasksPageServlet").forward(request, response);
        }
        else if (Pattern.compile(request.getContextPath() + "/groups/group/[0-9]+/tasks/get[/]?").matcher(path).matches()) {
            int groupNum = Integer.valueOf(pathParts[4]);
            request.setAttribute("groupNum", groupNum);
            request.getRequestDispatcher("/secure/TaskRESTServlet").forward(request, response);
            //filterChain.doFilter(request, response);
        }
        else if (Pattern.compile(request.getContextPath() + "/groups/group/[0-9]+/tasks/post[/]?").matcher(path).matches()) {
            int groupNum = Integer.valueOf(pathParts[4]);
            request.setAttribute("groupNum", groupNum);
            request.getRequestDispatcher("/secure/TaskRESTServlet").forward(request, response);
        }
        else if (Pattern.compile(request.getContextPath() + "/groups/group/[0-9]+/tasks/put[/]?").matcher(path).matches()) {
            int groupNum = Integer.valueOf(pathParts[4]);
            request.setAttribute("groupNum", groupNum);
            System.out.println(groupNum);
            request.getRequestDispatcher("/secure/TaskRESTServlet").forward(request, response);
        }
        else if (Pattern.compile(request.getContextPath() + "/groups/group/[0-9]+/tasks/delete[/]?").matcher(path).matches()) {
            int groupNum = Integer.valueOf(pathParts[4]);
            request.setAttribute("groupNum", groupNum);
            request.getRequestDispatcher("/secure/TaskRESTServlet").forward(request, response);
        }
        else {
            System.out.println("incorrect request");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
