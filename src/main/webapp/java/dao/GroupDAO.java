package dao;

import entity.Group;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends DAO {

    public void addGroup(Group group) throws SQLException {
        String sql = "INSERT INTO `group` (group_owner_id, group_name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, group.getOwnerId());
        statement.setString(2, group.getGroupName());
        statement.executeUpdate();
        statement.close();
    }

    public List<Group> getGroups(User user) throws SQLException {
        String sql = "SELECT id_group, group_owner_id, group_name FROM `group` WHERE group_owner_id = ? ORDER BY id_group";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getUserId());
        ResultSet resultSet = statement.executeQuery();

        List<Group> groupList = new ArrayList<>();

        while (resultSet.next()) {

            int groupId = resultSet.getInt(1);
            int ownerId = resultSet.getInt(2);
            String name = resultSet.getString(3);
            groupList.add(new Group(groupId, ownerId, name));
        }
        statement.close();
        return groupList;
    }

    public Group getGroupByNum(int groupNum) throws SQLException {
        String sql = "SELECT id_group, group_owner_id, group_name FROM `group` ORDER BY id_group LIMIT ?,1";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, groupNum);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Group group = new Group(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            );
            statement.close();
            return group;
        }
        statement.close();
        return null;
    }

    public void deleteGroup(Group currentGroup) throws SQLException {
        String sql = "DELETE FROM `group` WHERE id_group = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, currentGroup.getGroupId());
        statement.executeUpdate();
    }
}
