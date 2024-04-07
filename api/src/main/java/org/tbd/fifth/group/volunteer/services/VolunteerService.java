package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.VolunteerModel;
import org.tbd.fifth.group.volunteer.repositories.VolunteerRepository;


@Repository
public class VolunteerService implements VolunteerRepository {


    private final Sql2o sql2o;

    @Autowired
    public VolunteerService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    // volunteer_id, disponibility, name
    public VolunteerModel createVolunteer(VolunteerModel volunteer) {
        String sql = "INSERT INTO volunteer (volunteer_id, disponibility, name) VALUES (:volunteer_id, :disponibility, :name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(volunteer)
                    .executeUpdate()
                    .getKey();
            volunteer.setId_volunteer(id);
            return volunteer;
        }
    }
}
