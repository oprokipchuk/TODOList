package json.container;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskEdited {

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("taskNum")
    private int taskNum;

    public TaskEdited() {

    }

    public TaskEdited(String name, String status, String description, int taskNum) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.taskNum = taskNum;
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

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        return "TaskEdited{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", taskNum=" + taskNum +
                '}';
    }
}
