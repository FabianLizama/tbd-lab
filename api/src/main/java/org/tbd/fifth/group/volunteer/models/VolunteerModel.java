package org.tbd.fifth.group.volunteer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VolunteerModel {
    private int volunteer_id;
    private boolean disponibility;
    private String name;

    // Constructor por defecto
    public VolunteerModel() {
    }

    // Constructor con parámetros
    public VolunteerModel(int volunteer_id, boolean disponibility, String name) {
        this.volunteer_id = volunteer_id;
        this.disponibility = disponibility;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
