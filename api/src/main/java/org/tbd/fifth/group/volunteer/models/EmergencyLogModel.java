package org.tbd.fifth.group.volunteer.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmergencyLogModel {
    private int emergency_log_id;
    private int coordinator_id;
    private int emergency_id;
    private String description;
    private LocalDate date_change;
    private LocalTime hour_change;

    public EmergencyLogModel(int emergency_log_id, int coordinator_id, int emergency_id, String description, LocalDate date_change, LocalTime hour_change) {
        this.emergency_log_id = emergency_log_id;
        this.coordinator_id = coordinator_id;
        this.emergency_id = emergency_id;
        this.description = description;
        this.date_change = date_change;
        this.hour_change = hour_change;
    }

    public int getEmergency_log_id() {
        return emergency_log_id;
    }

    public void setEmergency_log_id(int emergency_log_id) {
        this.emergency_log_id = emergency_log_id;
    }

    public int getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(int coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public int getEmergency_id() {
        return emergency_id;
    }

    public void setEmergency_id(int emergency_id) {
        this.emergency_id = emergency_id;
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
