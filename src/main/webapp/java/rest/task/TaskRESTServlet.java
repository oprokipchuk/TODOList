package rest.task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Group;
import entity.Task;
import entity.User;
import json.container.TaskEdited;
import service.GroupService;
import service.JSONService;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRESTServlet extends HttpServlet {

    private GroupService groupService;
    private TaskService taskService;
    private JSONService jsonService;

    public void init() throws ServletException {
        super.init();
        groupService = new GroupService();
        taskService = new TaskService();
        jsonService = new JSONService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

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

        ObjectNode base = jsonService.getBaseNode();
        jsonService.addArrayOfPOJOs(base, "tasks",  currentGroup.getTaskList());

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        System.out.println(base.toString());
        //response.getWriter().println(base.toString());
        mapper.writeValue(response.getOutputStream(), base);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int groupNum = (int) request.getAttribute("groupNum");
        Group ownerGroup = null;
        try {
            ownerGroup = groupService.getGroupByNum(groupNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ownerGroup == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        int groupOwnerId = ownerGroup.getGroupId();
        JsonNode resultJson = (JsonNode) request.getAttribute("jsonNode");
        String taskName = resultJson.get("taskName").textValue();
        String taskStatus = resultJson.get("taskStatus").textValue();
        String description = resultJson.get("taskDescription").textValue();

        Task newTask = new Task(0, groupOwnerId, taskName, taskStatus, description);

        try {
            taskService.addTask(newTask);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }

        //response.sendRedirect(request.getContextPath() + "/groups/group/" + groupNum + "/tasks/");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int taskNum =  Integer.valueOf(request.getParameter("taskNum"));
        int groupNum = (Integer) request.getAttribute("groupNum");

        Task currentTask = null;
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
            taskService.deleteTask(currentGroup, taskNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect(request.getContextPath() + "/groups");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int groupNum = (Integer) request.getAttribute("groupNum");

        Task editedTask = null;
        JsonNode jsonNode = (JsonNode) request.getAttribute("jsonNode");
        System.out.println(jsonNode.toString());
        String taskName = jsonNode.get("name").textValue();
        String taskStatus = jsonNode.get("status").textValue();
        String taskDescription = jsonNode.get("description").textValue();
        int taskNum = Integer.parseInt(jsonNode.get("taskNum").textValue());
        editedTask = new Task(0,0, taskName, taskStatus, taskDescription);

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
            taskService.editTask(currentGroup, taskNum, editedTask);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect(request.getContextPath() + "/groups");
    }

}
