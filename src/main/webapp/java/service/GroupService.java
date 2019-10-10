package service;

import dao.GroupDAO;
import dao.TaskDAO;
import dao.UserDAO;
import entity.Group;
import entity.Task;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public class GroupService {

    private GroupDAO groupDAO;
    private TaskDAO taskDAO;

    public GroupService() {
        groupDAO = new GroupDAO();
        taskDAO = new TaskDAO();
    }

    public void addGroup(Group group) throws SQLException {
        groupDAO.addGroup(group);
    }

    public List<Group> getGroups(User user) throws SQLException {
        return groupDAO.getGroups(user);
    }

    public Group getGroupByNum(int groupNum) throws SQLException {
        return groupDAO.getGroupByNum(groupNum - 1);
    }

    public void bindTasks(Group currentGroup) throws SQLException {
        List<Task> taskList = taskDAO.getTasksByGroupId(currentGroup.getGroupId());
        currentGroup.setTaskList(taskList);
    }

    public void deleteGroup(Group currentGroup) throws SQLException {
        groupDAO.deleteGroup(currentGroup);
    }
}
