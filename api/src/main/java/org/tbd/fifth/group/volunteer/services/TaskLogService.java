package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.tbd.fifth.group.volunteer.models.TaskLogModel;
import org.tbd.fifth.group.volunteer.repositories.TaskLogRepository;
import org.sql2o.Sql2o;

@Repository
public class TaskLogService implements TaskLogRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public TaskLogModel createTaskLog(TaskLogModel taskLog){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"task_log\" (task_log_id, coordinator_id, task_id, description, date_change, hour_change) VALUES (:task_log_id, :coordinator_id, :task_id, :description, :date_change, :hour_change)")
                    .addParameter("task_log_id", taskLog.getTask_log_id())
                    .addParameter("coordinator_id", taskLog.getCoordinator_id())
                    .addParameter("task_id", taskLog.getTask_id())
                    .addParameter("description", taskLog.getDescription())
                    .addParameter("date_change", taskLog.getDate_change())
                    .addParameter("hour_change", taskLog.getHour_change())
                    .executeUpdate();

            return taskLog;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TaskLogModel getTaskLog(int task_log_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"task_log\" WHERE task_log_id = :task_log_id")
                    .addParameter("task_log_id", task_log_id)
                    .executeAndFetchFirst(TaskLogModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
