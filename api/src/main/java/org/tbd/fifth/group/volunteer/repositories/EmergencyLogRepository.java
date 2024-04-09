package org.tbd.fifth.group.volunteer.repositories;
import org.tbd.fifth.group.volunteer.models.EmergencyLogModel;

public interface EmergencyLogRepository {
    public EmergencyLogModel createEmergencyLog(EmergencyLogModel emergencyLog);

    public EmergencyLogModel getEmergencyLog(int emergency_log_id);

}
