package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.EmergencyModel;

import java.util.List;
import java.util.Map;

public interface EmergencyRepository {

    public EmergencyModel createEmergency(EmergencyModel emergency, String token);

    public EmergencyModel getEmergency(int emergency_id);

    public List<EmergencyModel> getAllEmergencies(String token);

    public List<EmergencyModel> getEmergenciesActives(String token);

    public List<Map<String, Object>> getEmergenciesView(String token);

}
