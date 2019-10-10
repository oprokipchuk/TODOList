package service;

import dao.TaskDAO;
import entity.Group;
import entity.Task;

import java.sql.SQLException;
import java.util.List;

public class TaskService {

    private TaskDAO taskDAO;

    public TaskService() {
        taskDAO = new TaskDAO();
    }

    public void addTask(Task task) throws SQLException {
        taskDAO.addTask(task);
    }

    public void deleteTask(Group currentGroup, int taskNum) throws SQLException {
        List<Task> taskList = currentGroup.getTaskList();
        if (taskNum <= taskList.size()) {
            taskDAO.deleteTask(currentGroup.getTaskList().get(taskNum - 1));
        }
    }

    public void editTask(Group currentGroup, int taskNum, Task editedTask) throws SQLException {
        List<Task> taskList = currentGroup.getTaskList();
        if (taskNum <= taskList.size()) {
            System.out.println("In taskservice edittask");
            Task oldTask = currentGroup.getTaskList().get(taskNum - 1);
            editedTask.setIdTask(oldTask.getIdTask());
            editedTask.setGroupId(oldTask.getGroupId());
            System.out.println(editedTask.getName());
            System.out.println(editedTask.getStatus());
            System.out.println(editedTask.getDescription());
            taskDAO.editTask(editedTask);
        }
    }
}
