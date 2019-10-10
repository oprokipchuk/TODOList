package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @JsonProperty("id")
    private int groupId;

    @JsonProperty("ownerId")
    private int ownerId;

    @JsonProperty("name")
    private String groupName;

    @JsonIgnore
    private List<Task> taskList;

    {
        taskList = new ArrayList<>();
    }

    public Group(int ownerId, String groupName) {
        this.ownerId = ownerId;
        this.groupName = groupName;
    }

    public Group(int groupId, int ownerId, String groupName) {
        this.groupId = groupId;
        this.ownerId = ownerId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
