package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.EmergencyLogModel;
import org.tbd.fifth.group.volunteer.repositories.EmergencyLogRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class EmergencyLogService implements EmergencyLogRepository {

    @Autowired
    private Sql2o sql2o;

  //  private int emergency_log_id;
    //private int coordinator_id;
    //private int emergency_id;
    //private String description;
    //private LocalDate date_change;
    //private LocalTime hour_change;

    @Override
    public EmergencyLogModel createEmergencyLog(EmergencyLogModel emergencyLog){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"emergency_log\" (emergency_log_id, coordinator_id, emergency_id, description, date_change, hour_change) VALUES (:emergency_log_id, :coordinator_id, :emergency_id, :description, :date_change, :hour_change)")
                    .addParameter("emergency_log_id", emergencyLog.getEmergency_log_id())
                    .addParameter("coordinator_id", emergencyLog.getCoordinator_id())
                    .addParameter("emergency_id", emergencyLog.getEmergency_id())
                    .addParameter("description", emergencyLog.getDescription())
                    .addParameter("date_change", emergencyLog.getDate_change())
                    .addParameter("hour_change", emergencyLog.getHour_change())
                    .executeUpdate();

            return emergencyLog;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EmergencyLogModel getEmergencyLog(int emergency_log_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"emergency_log\" WHERE emergency_log_id = :emergency_log_id")
                    .addParameter("emergency_log_id", emergency_log_id)
                    .executeAndFetchFirst(EmergencyLogModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
