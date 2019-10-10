package dao;

import entity.Group;
import entity.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO extends DAO {

    public List<Task> getTasksByGroupId(int groupId) throws SQLException {
        String sql = "SELECT id_task, group_id, `name`, `status`, `description` FROM `task` WHERE group_id = ? ORDER BY id_task";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, groupId);
        ResultSet resultSet = statement.executeQuery();

        List<Task> taskList = new ArrayList<>();

        while (resultSet.next()) {


            int task_Id = resultSet.getInt(1);
            int group_Id = resultSet.getInt(2);
            String name = resultSet.getString(3);
            String status = resultSet.getString(4);
            String description = resultSet.getString(5);

            taskList.add(new Task(task_Id, group_Id, name, status, description));
        }
        statement.close();
        return taskList;
    }

    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO `task` (group_id, `name`, `status`, `description`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, task.getGroupId());
        statement.setString(2, task.getName());
        statement.setString(3, task.getStatus());
        statement.setString(4, task.getDescription());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteTask(Task task) throws SQLException {
        String sql = "DELETE FROM `task` WHERE id_task = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, task.getIdTask());
        statement.executeUpdate();
    }

    public void editTask(Task task) throws SQLException {
        String sql = "UPDATE `task` SET `name` = ?, `status` = ?, `description`=? WHERE id_task = ?";
        System.out.println("in taskdao " + sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, task.getName());
        statement.setString(2, task.getStatus());
        statement.setString(3, task.getDescription());
        statement.setInt(4, task.getIdTask());
        statement.executeUpdate();
        statement.close();
    }
}
