package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.TaskModel;
import org.tbd.fifth.group.volunteer.repositories.TaskRepository;

@Repository
public class TaskService implements TaskRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public TaskModel createTask(TaskModel task){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"task\" ( emergency_id, task_state_id, task_name) VALUES ( :emergency_id, :task_state_id, :task_name)")
                    .addParameter("emergency_id", task.getEmergency_id())
                    .addParameter("task_state_id", task.getTask_state_id())
                    .addParameter("task_name", task.getTask_name())
                    .executeUpdate();

            return task;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TaskModel getTask(int task_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"task\" WHERE task_id = :task_id")
                    .addParameter("task_id", task_id)
                    .executeAndFetchFirst(TaskModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
