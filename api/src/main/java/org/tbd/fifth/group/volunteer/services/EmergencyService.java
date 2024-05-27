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
                connection.createQuery("INSERT INTO \"emergency\" (institution_id, coordinator_id, name, emergency_state, longitude, latitude) VALUES (:institution_id, :coordinator_id, :name, :emergency_state, :longitude, :latitude)")
                        .addParameter("institution_id", emergency.getInstitution_id())
                        .addParameter("coordinator_id", emergency.getCoordinator_id())
                        .addParameter("name", emergency.getName())
                        .addParameter("emergency_state", emergency.getEmergency_state())
                        .addParameter("longitude", emergency.getLongitude())
                        .addParameter("latitude", emergency.getLatitude())
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
                return connection.createQuery("SELECT * FROM \"emergency\" WHERE emergency_state = 'Active'")
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
                String sql = "SELECT eme.emergency_id, eme.name, COUNT(DISTINCT ran.volunteer_id) AS quantity_Volunteers " +
                        "FROM \"emergency\" AS eme " +
                        "LEFT JOIN \"task\" AS tas ON tas.emergency_id = eme.emergency_id " +
                        "LEFT JOIN \"ranking\" AS ran ON ran.task_id = tas.task_id " +
                        "GROUP BY eme.emergency_id " +
                        "ORDER BY quantity_Volunteers ASC";
                return connection.createQuery(sql).executeAndFetchTable().asList();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }



    public List<Map<String,Object>> getEmergenciesActivesView(String token){
        if(JWT.validateToken(token)){
            try(Connection connection = sql2o.open()){
                String sql = "SELECT eme.emergency_id AS emergencyId, eme.name AS EmergencyName, inst.institution_name AS InstitutionName, usr.name AS CoordinatorName " +
                        "FROM emergency AS eme " +
                        "JOIN institution AS inst ON eme.institution_id = inst.institution_id " +
                        "JOIN coordinator AS coord ON eme.coordinator_id = coord.coordinator_id " +
                        "JOIN UserM AS usr ON coord.user_id = usr.User_id " +
                        "WHERE eme.emergency_state = 'Active'";
                return connection.createQuery(sql).executeAndFetchTable().asList();
            }catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }else{
            return null;
        }
    }

    public List<Map<String,Object>> getEmergenciesByRegion(String name, String token){
        if(JWT.validateToken(token)){
            try(Connection connection = sql2o.open()){
                String sql = "SELECT eme.* " +
                        "FROM \"emergency\" AS eme " +
                        "CROSS JOIN \"regions\" AS reg " +
                        "WHERE reg.name = " + "\'" + name + "\' " +
                        "AND ST_Intersects(ST_SetSRID(ST_MakePoint(eme.longitude,eme.latitude), 4326), reg.geom)";
                return connection.createQuery(sql).executeAndFetchTable().asList();
            }catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }else{
            return null;
        }
    }
}
