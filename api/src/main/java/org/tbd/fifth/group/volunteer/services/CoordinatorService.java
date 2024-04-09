package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.CoordinatorModel;
import org.tbd.fifth.group.volunteer.repositories.CoordinatorRepository;

@Repository
public class CoordinatorService implements CoordinatorRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    // coordinator_id, institution, name
    public CoordinatorModel createCoordinator(CoordinatorModel coordinator){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"coordinator\" (coordinator_id, institution, name) VALUES (:coordinator_id, :institution, :name)")
                    .addParameter("coordinator_id", coordinator.getCoordinator_id())
                    .addParameter("institution", coordinator.getInstitution_id())
                    .addParameter("name", coordinator.getName())
                    .executeUpdate();

            return coordinator;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public CoordinatorModel getCoordinator(int coordinator_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"coordinator\" WHERE coordinator_id = :coordinator_id")
                    .addParameter("coordinator_id", coordinator_id)
                    .executeAndFetchFirst(CoordinatorModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
