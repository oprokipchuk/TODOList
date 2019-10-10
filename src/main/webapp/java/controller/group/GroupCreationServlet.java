package controller.group;

import entity.Group;
import entity.User;
import service.GroupService;
import service.JSONService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class GroupCreationServlet extends HttpServlet {

    private GroupService groupService;
    private JSONService jsonService;

    public void init() throws ServletException {
        super.init();
        groupService = new GroupService();
        jsonService = new JSONService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.getRequestDispatcher("/WEB-INF/view/groups/newGroup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");
        String groupName = request.getParameter("groupName");
        if (groupName == null) {
            groupName = jsonService.JSONStringToNode(jsonService.InputStreamToJSON(request.getInputStream())).get("groupName").textValue();
        }
        Group group = new Group(user.getUserId(), groupName);

        try {
            groupService.addGroup(group);
            response.sendRedirect(request.getContextPath() + "/groups");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //response.sendRedirect(request.getContextPath() + "/groups");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int groupNum =  Integer.valueOf(request.getParameter("groupNum"));

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
            groupService.deleteGroup(currentGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect(request.getContextPath() + "/groups");
    }
}
