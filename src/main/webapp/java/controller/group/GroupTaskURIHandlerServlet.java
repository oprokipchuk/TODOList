package controller.group;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class GroupTaskURIHandlerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        String[] pathParts = path.split("/");
        System.out.println("HUB: " + request.getPathInfo() + " | " + request.getContextPath());
        System.out.println(Arrays.toString(pathParts));

        if (pathParts.length == 3 && "tasks".equals(pathParts[2])) {
            int groupNum;
            try {
                groupNum = Integer.valueOf(pathParts[1]);
                request.setAttribute("groupNum", groupNum);
                request.getRequestDispatcher("/GroupTasksPageServlet").forward(request, response);
            }
            catch (NumberFormatException ex) {

            }
        }
        else if (pathParts.length == 4 && "tasks".equals(pathParts[2]) && "new".equals(pathParts[3])) {
            int groupNum;
            int groupId;
            try {
                groupNum = Integer.valueOf(pathParts[1]);
                request.setAttribute("groupNum", groupNum);
                request.getRequestDispatcher("/tasks/new").forward(request, response);
            }
            catch (NumberFormatException ex) {

            }
        }
        else if (pathParts[2].equals("task") && pathParts.length == 4) {

        }

    }
}
