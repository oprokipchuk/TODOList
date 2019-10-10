package controller.task;

import entity.Group;
import entity.Task;
import entity.User;
import json.container.TaskEdited;
import service.GroupService;
import service.JSONService;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class TaskCreationServlet extends HttpServlet {
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

        if (request.getParameter("taskNum") != null) {
            int taskNum =  Integer.valueOf(request.getParameter("taskNum"));
            int groupNum = (Integer) request.getAttribute("groupNum");

            try {
                Group group = groupService.getGroupByNum(groupNum);
                groupService.bindTasks(group);
                Task task = group.getTaskList().get(taskNum - 1);
                request.setAttribute("Task", task);
                request.setAttribute("taskNum", taskNum);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/WEB-INF/view/groups/tasks/editTask.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/WEB-INF/view/groups/tasks/newTask.jsp").forward(request, response);
        }
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
        String taskName = request.getParameter("taskName");
        String taskStatus = request.getParameter("taskStatus");
        String description = request.getParameter("taskDescription");

        Task newTask = new Task(0, groupOwnerId, taskName, taskStatus, description);

        try {
            taskService.addTask(newTask);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/groups/group/" + groupNum + "/tasks/");
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

        TaskEdited taskEditedData = jsonService.JSONToUserEditedData(jsonService.InputStreamToJSON(request.getInputStream()));
        System.out.println(taskEditedData);
        //int taskNum =  Integer.valueOf(request.getParameter("taskNum"));
        int groupNum = (Integer) request.getAttribute("groupNum");

        //System.out.println("In do put: " + taskNum + " " + groupNum);

        Task editedTask = null;
        //String taskName = request.getParameter("taskName");
        //String taskStatus = request.getParameter("taskStatus");
        //String taskDescription = request.getParameter("taskDescription");
        editedTask = new Task(0,0,
                taskEditedData.getName(),
                taskEditedData.getStatus(),
                taskEditedData.getDescription());

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
            taskService.editTask(currentGroup, taskEditedData.getTaskNum(), editedTask);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect(request.getContextPath() + "/groups");
    }

}
