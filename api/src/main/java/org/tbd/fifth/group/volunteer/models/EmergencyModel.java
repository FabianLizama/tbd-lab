package org.tbd.fifth.group.volunteer.models;

public class EmergencyModel {
    private int emergency_id;
    private int institution_id;

    private int coordinator_id;
    private String name;

    private String emergency_state;

    private double  longitude;
    private double latitude;

    public EmergencyModel(int emergency_id, int institution_id, String name, String emergency_state, int coordinator_id, double longitude, double latitude) {
        this.emergency_id = emergency_id;
        this.institution_id = institution_id;
        this.name = name;
        this.emergency_state = emergency_state;
        this.coordinator_id = coordinator_id;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public int getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(int coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public String getEmergency_state() {
        return emergency_state;
    }

    public void setEmergency_state(String emergency_state) {
        this.emergency_state = emergency_state;
    }

    public double getLongitude() {
        return this.longitude;
    }
    public double getLatitude() {
        return this.latitude;
    }
    public void setLongitude(double length) {
        this.longitude = length;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
