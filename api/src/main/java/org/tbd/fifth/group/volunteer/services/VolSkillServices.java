package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.VolSkillModel;
import org.tbd.fifth.group.volunteer.repositories.VolSkillRepository;

@Repository
public class VolSkillServices implements VolSkillRepository {

    @Autowired
    private Sql2o sql2o;

    @Override



    public VolSkillModel createVolSkill(VolSkillModel volSkill){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"vol_skill\" (vol_skill_id, volunteer_id, skill_id) VALUES (:vol_skill_id, :volunteer_id, :skill_id)")
                    .addParameter("vol_skill_id", volSkill.getVol_skill_id())
                    .addParameter("volunteer_id", volSkill.getVolunteer_id())
                    .addParameter("skill_id", volSkill.getSkill_id())
                    .executeUpdate();

            return volSkill;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public VolSkillModel getVolSkill(int vol_skill_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"vol_skill\" WHERE vol_skill_id = :vol_skill_id")
                    .addParameter("vol_skill_id", vol_skill_id)
                    .executeAndFetchFirst(VolSkillModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
