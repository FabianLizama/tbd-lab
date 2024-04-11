package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.SkillModel;
import org.tbd.fifth.group.volunteer.repositories.SkillRepository;

@Repository
public class SkillService implements SkillRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public SkillModel createSkill(SkillModel skill) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("INSERT INTO \"skill\" ( skill_name) VALUES (:skill_name)")
                    .addParameter("skill_name", skill.getSkill_name())
                    .executeUpdate();

            return skill;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public SkillModel getSkill(int skill_id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM \"skill\" WHERE skill_id = :skill_id")
                    .addParameter("skill_id", skill_id)
                    .executeAndFetchFirst(SkillModel.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}