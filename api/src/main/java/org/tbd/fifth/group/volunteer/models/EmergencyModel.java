package org.tbd.fifth.group.volunteer.models;

public class EmergencyModel {
    private int emergency_id;
    private int institution_id;
    private String name;

    public EmergencyModel(int emergency_id, int institution_id, String name) {
        this.emergency_id = emergency_id;
        this.institution_id = institution_id;
        this.name = name;
    }

    public int getEmergency_id() {
        return emergency_id;
    }

    public void setEmergency_id(int emergency_id) {
        this.emergency_id = emergency_id;
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
