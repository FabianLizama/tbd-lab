package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.EmergencyModel;

import java.util.List;

public interface EmergencyRepository {

    public EmergencyModel createEmergency(EmergencyModel emergency);

    public EmergencyModel getEmergency(int emergency_id);

    public List<EmergencyModel> getAllEmergencies(String token);


}
