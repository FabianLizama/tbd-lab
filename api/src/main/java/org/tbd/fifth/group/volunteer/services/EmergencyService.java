package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.EmergencyModel;
import org.tbd.fifth.group.volunteer.repositories.EmergencyRepository;

import java.util.List;


@Repository
public class EmergencyService implements EmergencyRepository {

    @Autowired
    private Sql2o sql2o;

    @Autowired
    private JwtMiddlewareServices JWT;

    @Override
    /*    private int emergency_id;
    private int institution_id;

    private int coordinator_id;
    private String name;

    private String emergency_state;

     */


    public EmergencyModel createEmergency(EmergencyModel emergency){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"emergency\" (institution_id, coordinator_id, name, emergency_state) VALUES (:institution_id, :coordinator_id, :name, :emergency_state)", true)
                    .addParameter("institution_id", emergency.getInstitution_id())
                    .addParameter("coordinator_id", emergency.getCoordinator_id())
                    .addParameter("name", emergency.getName())
                    .addParameter("emergency_state", emergency.getEmergency_state())
                    .executeUpdate().getKey();
            return emergency;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EmergencyModel getEmergency(int emergency_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"emergency\" WHERE emergency_id = :emergency_id")
                    .addParameter("emergency_id", emergency_id)
                    .executeAndFetchFirst(EmergencyModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<EmergencyModel> getAllEmergencies(String token) {
        if (JWT.validateToken(token)) {
            try (Connection connection = sql2o.open()) {
                return connection.createQuery("SELECT * FROM \"emergency\"")
                        .executeAndFetch(EmergencyModel.class);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            return null;
        }

    }

}
