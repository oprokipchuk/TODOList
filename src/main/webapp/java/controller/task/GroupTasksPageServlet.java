package controller.task;

import entity.Group;
import entity.User;
import service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupTasksPageServlet extends HttpServlet {

    private GroupService groupService;

    public void init() throws ServletException {
        super.init();
        groupService = new GroupService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int groupNum = (int) request.getAttribute("groupNum");
        Group currentGroup = null;
        try {
            currentGroup = groupService.getGroupByNum(groupNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (currentGroup == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            groupService.bindTasks(currentGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(currentGroup.getGroupName());
        request.setAttribute("group", currentGroup);

        request.getRequestDispatcher("/WEB-INF/view/groups/tasks/tasks.jsp").forward(request, response);
    }
}
