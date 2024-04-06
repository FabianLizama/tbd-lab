package org.tbd.fifth.group.volunteer.models;

public class CoordinatorModel {
    private int coordinator_id;
    private int institution_id;
    private String name;

    public CoordinatorModel(int coordinator_id, int institution_id, String name) {
        this.coordinator_id = coordinator_id;
        this.institution_id = institution_id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
