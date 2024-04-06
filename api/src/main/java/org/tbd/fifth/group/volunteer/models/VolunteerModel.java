package org.tbd.fifth.group.volunteer.models;

public class VolunteerModel {
    private int volunteer_id;
    private boolean disponibility;

    private String name;

    public VolunteerModel(int id_volunteer, boolean disponibility, String name) {
        this.volunteer_id = id_volunteer;
        this.disponibility = disponibility;
        this.name = name;
    }

    public int getId_volunteer() {
        return volunteer_id;
    }

    public void setId_volunteer(int id_volunteer) {
        this.volunteer_id = id_volunteer;
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
