package org.tbd.fifth.group.volunteer.repositories;
import org.springframework.stereotype.Repository;
import org.tbd.fifth.group.volunteer.models.VolunteerModel;

import java.util.List;


public interface VolunteerRepository {
    public VolunteerModel createVolunteer(VolunteerModel volunteer);

    public VolunteerModel getVolunteer(int volunteer_id);

    public List<VolunteerModel> listVolunteers();

    public VolunteerModel updateVolunteer(VolunteerModel volunteer);

    public VolunteerModel deleteVolunteer(int volunteer_id);

}
