package org.tbd.fifth.group.volunteer.repositories;


import org.tbd.fifth.group.volunteer.models.CoordinatorModel;

public interface CoordinatorRepository {

    public CoordinatorModel createCoordinator(CoordinatorModel coordinator);

    public CoordinatorModel getCoordinator(int coordinator_id);

    public int getId_Coordinator(int user_id);
}
