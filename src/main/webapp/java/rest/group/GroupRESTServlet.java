package rest.group;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Group;
import entity.User;
import service.GroupService;
import service.JSONService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRESTServlet extends HttpServlet {

    private GroupService groupService;
    private JSONService jsonService;

    public void init() throws ServletException {
        super.init();
        groupService = new GroupService();
        jsonService = new JSONService();
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

        ObjectNode base = jsonService.getBaseNode();
        jsonService.addArrayOfPOJOs(base, "groups",  groupList);

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        System.out.println(base.toString());
        //response.getWriter().println(base.toString());
        mapper.writeValue(response.getOutputStream(), base);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");

        JsonNode jsonNode = (JsonNode) request.getAttribute("jsonNode");
        String groupName = jsonNode.get("groupName").textValue();
        Group group = new Group(user.getUserId(), groupName);

        try {
            groupService.addGroup(group);
            response.sendRedirect(request.getContextPath() + "/groups");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
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
            response.sendError(500);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect(request.getContextPath() + "/groups");
    }
}
