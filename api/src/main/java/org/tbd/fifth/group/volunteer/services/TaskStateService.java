package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.TaskStateModel;
import org.tbd.fifth.group.volunteer.repositories.TaskStateRepository;

@Repository
public class TaskStateService implements TaskStateRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public TaskStateModel createTaskState(TaskStateModel taskState){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"task_state\" ( state, description) VALUES ( :state, :description)")
                    .addParameter("state", taskState.getState())
                    .addParameter("description", taskState.getDescription())
                    .executeUpdate();

            return taskState;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TaskStateModel getTaskState(int task_state_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"task_state\" WHERE task_state_id = :task_state_id")
                    .addParameter("task_state_id", task_state_id)
                    .executeAndFetchFirst(TaskStateModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TaskStateModel UpdateTaskState(TaskStateModel taskState){
        try(Connection connection = sql2o.open()){
            connection.createQuery("UPDATE \"task_state\" SET state = :state WHERE task_state_id = :task_state_id")
                    .addParameter("state", taskState.getState())
                    .addParameter("task_state_id", taskState.getTask_state_id())
                    .executeUpdate();
            return taskState;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
