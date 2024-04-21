package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.EmergencyModel;
import org.tbd.fifth.group.volunteer.repositories.EmergencyRepository;

import java.util.List;
import java.util.Map;

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

    public EmergencyModel createEmergency(EmergencyModel emergency, String token){
        if(JWT.validateToken(token)){
            try(Connection connection = sql2o.open()){
                connection.createQuery("INSERT INTO \"emergency\" (institution_id, coordinator_id, name, emergency_state) VALUES (:institution_id, :coordinator_id, :name, :emergency_state)")
                        .addParameter("institution_id", emergency.getInstitution_id())
                        .addParameter("coordinator_id", emergency.getCoordinator_id())
                        .addParameter("name", emergency.getName())
                        .addParameter("emergency_state", emergency.getEmergency_state())
                        .executeUpdate();
            }catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
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

    @Override
    public List<EmergencyModel> getEmergenciesActives(String token) {
        if (JWT.validateToken(token)) {
            try (Connection connection = sql2o.open()) {
                return connection.createQuery("SELECT * FROM \"emergency\" WHERE emergency_state = 'active'")
                        .executeAndFetch(EmergencyModel.class);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public List<Map<String, Object>> getEmergenciesView(String token) {
        if (JWT.validateToken(token)) {
            try (Connection connection = sql2o.open()) {
                // Verificar que esté en funcionamiento.
                String sql = "SELECT eme.emergency_id, eme.name, COUNT(DISTINCT vol.volunteer_id) AS quantity " +
                                "FROM \"emergency\" AS eme " +
                                "LEFT JOIN \"eme_skill\" AS emeski ON emeski.emergency_id = eme.emergency_id " +
                                "LEFT JOIN \"skill\" AS ski ON ski.skill_id = emeski.skill_id " +
                                "LEFT JOIN \"vol_skill\" AS volski ON volski.skill_id = ski.skill_id " +
                                "LEFT JOIN \"volunteer\" AS vol ON vol.volunteer_id = volski.volunteer_id " +
                                "GROUP BY eme.emergency_id " +
                                "ORDER BY quantity ASC";
                return connection.createQuery(sql).executeAndFetchTable().asList();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }
}
