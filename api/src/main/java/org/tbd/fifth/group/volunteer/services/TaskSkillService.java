package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.tbd.fifth.group.volunteer.models.TaskSkillModel;
import org.tbd.fifth.group.volunteer.repositories.TaskSkillRepository;
import org.sql2o.Sql2o;

@Repository
public class TaskSkillService implements TaskSkillRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public TaskSkillModel createTaskSkill(TaskSkillModel taskSkill){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"task_skill\" ( eme_skill_id) VALUES (:eme_skill_id)")
                    .addParameter("eme_skill_id", taskSkill.getEme_skill_id())
                    .executeUpdate();

            return taskSkill;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TaskSkillModel getTaskSkill(int task_skill_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"task_skill\" WHERE task_skill_id = :task_skill_id")
                    .addParameter("task_skill_id", task_skill_id)
                    .executeAndFetchFirst(TaskSkillModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
