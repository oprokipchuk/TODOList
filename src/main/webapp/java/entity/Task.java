package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    public static String TASK_NOT_STARTED = "not started";
    public static String TASK_IN_PROCESS = "in process";
    public static String TASK_DONE = "done";

    @JsonProperty("id")
    private int idTask;
    @JsonProperty("groupId")
    private int groupId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("description")
    private String description;

    public Task(int idTask, int groupId, String name, String status, String description) {
        this.idTask = idTask;
        this.groupId = groupId;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Task() {}

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
