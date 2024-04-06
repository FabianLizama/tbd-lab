package org.tbd.fifth.group.volunteer.models;

public class TaskModel {
    private int task_id;
    private int emergency_id;
    private int task_state_id;
    private String task_name;

    public TaskModel(int task_id, int emergency_id, int task_state_id, String task_name) {
        this.task_id = task_id;
        this.emergency_id = emergency_id;
        this.task_state_id = task_state_id;
        this.task_name = task_name;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getEmergency_id() {
        return emergency_id;
    }

    public void setEmergency_id(int emergency_id) {
        this.emergency_id = emergency_id;
    }

    public int getTask_state_id() {
        return task_state_id;
    }

    public void setTask_state_id(int task_state_id) {
        this.task_state_id = task_state_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    // Getters y setters...
}
