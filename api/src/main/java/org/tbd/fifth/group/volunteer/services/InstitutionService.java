package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.InstitutionModel;
import org.tbd.fifth.group.volunteer.repositories.InstitutionRepository;

@Repository
public class InstitutionService implements InstitutionRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public InstitutionModel createInstitution(InstitutionModel institution){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"institution\" ( institution_name) VALUES ( :institution_name)")
                    .addParameter("institution_name", institution.getInstitution_name())
                    .executeUpdate();

            return institution;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public InstitutionModel getInstitution(int institution_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"institution\" WHERE institution_id = :institution_id")
                    .addParameter("institution_id", institution_id)
                    .executeAndFetchFirst(InstitutionModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public InstitutionModel getAllInstitutions(){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"institution\"")
                    .executeAndFetchFirst(InstitutionModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
