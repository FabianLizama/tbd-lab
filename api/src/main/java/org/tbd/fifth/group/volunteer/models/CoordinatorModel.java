package org.tbd.fifth.group.volunteer.models;

public class CoordinatorModel {
    private int coordinator_id;
    private int institution_id;
    private int user_id;

    public CoordinatorModel(int coordinator_id, int institution_id, int user_id) {
        this.coordinator_id = coordinator_id;
        this.institution_id = institution_id;
        this.user_id = user_id;
    }

    public int getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(int coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public int getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(int institution_id) {
        this.institution_id = institution_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
