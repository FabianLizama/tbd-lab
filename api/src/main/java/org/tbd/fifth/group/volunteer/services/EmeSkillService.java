package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.EmeSkillModel;
import org.tbd.fifth.group.volunteer.repositories.EmeSkillRepository;

import java.util.List;
import java.util.Map;

@Repository
public class EmeSkillService implements EmeSkillRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public EmeSkillModel createEmeSkill(EmeSkillModel emeSkill){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"eme_skill\" ( emergency_id, skill_id) VALUES ( :emergency_id, :skill_id)")
                    .addParameter("emergency_id", emeSkill.getEmergency_id())
                    .addParameter("skill_id", emeSkill.getSkill_id())
                    .executeUpdate();
            return emeSkill;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EmeSkillModel getEmeSkill(int eme_skill_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"eme_skill\" WHERE eme_skill_id = :eme_skill_id")
                    .addParameter("eme_skill_id", eme_skill_id)
                    .executeAndFetchFirst(EmeSkillModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    /*SELECT
    eme.name AS EmergencyName,
    ski.skill_name AS SkillName
FROM
    emergency eme
JOIN
    eme_skill emeski ON eme.emergency_id = emeski.emergency_id
JOIN
    skill ski ON emeski.skill_id = ski.skill_id
WHERE
    eme.emergency_id = 1
GROUP BY
    eme.name, ski.skill_name
ORDER BY
    ski.skill_name;*/
    @Override
    public List<Map<String, Object>> getEmeSkillsByEmergency(int emergency_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT eme.name AS EmergencyName, ski.skill_name AS SkillName FROM emergency eme JOIN eme_skill emeski ON eme.emergency_id = emeski.emergency_id JOIN skill ski ON emeski.skill_id = ski.skill_id WHERE eme.emergency_id = :emergency_id GROUP BY eme.name, ski.skill_name ORDER BY ski.skill_name")
                    .addParameter("emergency_id", emergency_id)
                    .executeAndFetchTable().asList();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
