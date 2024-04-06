package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tbd.fifth.group.volunteer.models.VolunteerModel;
import org.tbd.fifth.group.volunteer.repositories.VolunteerRepository;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import java.sql.Connection;
import java.util.List;

@Repository
public class VolunteerService implements VolunteerRepository {

    @Autowired
    private sql2o sql2o;

    @Override
    public VolunteerModel createVolunteer(VolunteerModel volunteer) {
        try (Connection conn = sql2o.open()) {
            int insertedId = (int) conn.createQuery("INSERT INTO volunteer (name, email, phone, address, description) VALUES (:name, :email, :phone, :address, :description)", true)
                    .bind(volunteer)
                    .executeUpdate()
                    .getKey();
            volunteer.setId(insertedId);
            return volunteer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
