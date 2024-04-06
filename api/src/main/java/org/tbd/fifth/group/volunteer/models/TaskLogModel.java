package org.tbd.fifth.group.volunteer.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskLogModel {
    private int task_log_id;
    private int coordinator_id;
    private int task_id;
    private String description;
    private LocalDate date_change;
    private LocalTime hour_change;

    public TaskLogModel(int task_log_id, int coordinator_id, int task_id, String description, LocalDate date_change, LocalTime hour_change) {
        this.task_log_id = task_log_id;
        this.coordinator_id = coordinator_id;
        this.task_id = task_id;
        this.description = description;
        this.date_change = date_change;
        this.hour_change = hour_change;
    }

    public int getTask_log_id() {
        return task_log_id;
    }

    public void setTask_log_id(int task_log_id) {
        this.task_log_id = task_log_id;
    }

    public int getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(int coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_change() {
        return date_change;
    }

    public void setDate_change(LocalDate date_change) {
        this.date_change = date_change;
    }

    public LocalTime getHour_change() {
        return hour_change;
    }

    public void setHour_change(LocalTime hour_change) {
        this.hour_change = hour_change;
    }
}