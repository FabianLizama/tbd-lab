package org.tbd.fifth.group.volunteer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VolunteerModel {
    private int volunteer_id;
    private boolean disponibility;

    private int user_id;

    // Constructor por defecto
    public VolunteerModel() {
    }

    // Constructor con parámetros
    public VolunteerModel(int volunteer_id, boolean disponibility, int user_id) {
        this.volunteer_id = volunteer_id;
        this.disponibility = disponibility;
        this.user_id = user_id;
    }

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(int volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
