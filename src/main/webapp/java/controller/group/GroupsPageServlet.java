package controller.group;

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

public class GroupsPageServlet extends HttpServlet {

    private GroupService groupService;

    public void init() throws ServletException {
        super.init();
        groupService = new GroupService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        List<Group> groupList = new ArrayList<>();
        try {
            groupList = groupService.getGroups(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("groupList", groupList);

        request.getRequestDispatcher("/WEB-INF/view/groups/groups.jsp").forward(request, response);
    }
}
