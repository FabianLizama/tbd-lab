package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.TaskModel;
import org.tbd.fifth.group.volunteer.repositories.TaskRepository;

import java.util.List;

@Repository
public class TaskService implements TaskRepository{

    @Autowired
    private Sql2o sql2o;

    @Autowired
    private JwtMiddlewareServices JWT;

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

    @Override
    public List<TaskModel> getAllTasks(String token){
        if(JWT.validateToken(token)){
            try(Connection connection = sql2o.open()){
                return connection.createQuery("SELECT * FROM \"task\"")
                        .executeAndFetch(TaskModel.class);
            }catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public List<TaskModel> getTasksByEmergencyId(int emergency_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"task\" WHERE emergency_id = :emergency_id")
                    .addParameter("emergency_id", emergency_id)
                    .executeAndFetch(TaskModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int countActiveTasksByEmergencyId(int id){
        int activeTasks = 0;
        try(Connection connection = sql2o.open()){
            activeTasks = connection
                    .createQuery("SELECT COUNT(*) FROM \"task\" WHERE emergency_id = :id AND task_state_id = '1'")
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        }
        return activeTasks;
    }

    @Override
    public int countAllActiveTasks(){
        int allActiveTasks = 0;
        try(Connection connection = sql2o.open()){
            allActiveTasks = connection
                    .createQuery("SELECT COUNT(*) FROM \"task\" WHERE task_state_id = '1'")
                    .executeScalar(Integer.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        return allActiveTasks;
    }

    @Override
    public boolean updateTask(TaskModel task){
        try(Connection connection = sql2o.open()){
            connection.createQuery("UPDATE \"task\" SET emergency_id = :emergency_id, task_state_id = :task_state_id, task_name = :task_name WHERE task_id = :task_id")
                    .addParameter("emergency_id", task.getEmergency_id())
                    .addParameter("task_state_id", task.getTask_state_id())
                    .addParameter("task_name", task.getTask_name())
                    .addParameter("task_id", task.getTask_id())
                    .executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteTask(int task_id){
        try(Connection connection = sql2o.open()){
            connection.createQuery("DELETE FROM \"task\" WHERE task_id = :task_id")
                    .addParameter("task_id", task_id)
                    .executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAllTasks(){
        try(Connection connection = sql2o.open()){
            connection.createQuery("DELETE FROM \"task\"")
                    .executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<TaskModel> getTaskView(String token) {
        if (JWT.validateToken(token)) {
            try (Connection connection = sql2o.open()) {
                // Verificar que esté en funcionamiento.
                return connection.createQuery("SELECT tas.task_id, tas.task_name, COUNT(vol.volunteer_id) AS quantity " +
                                "FROM \"task\" AS tas " +
                                "JOIN \"task_skill\" AS taskill ON taskill.task_skill_id = tas.task_skill_id " +
                                "JOIN \"eme_skill\" AS emeski ON emeski.eme_skill_id = taskill.eme_skill_id " +
                                "JOIN \"skill\" AS ski ON ski.skill_id = emeski.skill_id " +
                                "JOIN \"vol_skill\" AS volski ON volski.skill_id = ski.skill_id " +
                                "JOIN \"volunteer\" AS vol ON vol.volunteer_id = volski.volunteer_id " +
                                "GROUP BY tas.task_id " +
                                "ORDER BY quantity ASC")
                        .executeAndFetch(TaskModel.class);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
}
