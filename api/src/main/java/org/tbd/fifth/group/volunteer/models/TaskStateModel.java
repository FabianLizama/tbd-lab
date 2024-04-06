package org.tbd.fifth.group.volunteer.models;

public class TaskStateModel {
    private int task_state_id;
    private String state;
    private String description;

    public TaskStateModel(int task_state_id, String state, String description) {
        this.task_state_id = task_state_id;
        this.state = state;
        this.description = description;
    }

    public int getTask_state_id() {
        return task_state_id;
    }

    public void setTask_state_id(int task_state_id) {
        this.task_state_id = task_state_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters y setters...
}
