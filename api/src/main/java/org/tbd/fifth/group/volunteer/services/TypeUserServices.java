package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.TypeUserModel;
import org.tbd.fifth.group.volunteer.repositories.TypeUserRepository;

import java.util.List;

@Repository
public class TypeUserServices implements TypeUserRepository {

    @Autowired
    private Sql2o sql2o;

    /*    private int type_user_id;

    private String type_user_name;

    private boolean CRUD_emergencies;

    private boolean CRUD_tasks;

    private boolean CRUD_tasks_states;

    private boolean asign_volunteers;

    private TypeUserModel() {
    }

     */

    @Override
    public List<TypeUserModel> getAllTypeUsers(){
        try(Connection connection = sql2o.open()){
            return connection
                    .createQuery("SELECT * FROM \"type_user\"")
                    .executeAndFetch(TypeUserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String createTypeUser(TypeUserModel typeUserModel){
        try(Connection connection = sql2o.open()){
            Integer insertedId = (Integer) connection
                    .createQuery("INSERT INTO \"type_user\" (type_user_name, CRUD_emergencies, CRUD_tasks, CRUD_tasks_states, asign_volunteers) values (:type_user_name, :CRUD_emergencies, :CRUD_tasks, :CRUD_tasks_states, :asign_volunteers)",
                            true)
                    .addParameter("type_user_name", typeUserModel.getType_user_name())
                    .addParameter("CRUD_emergencies", typeUserModel.isCRUD_emergencies())
                    .addParameter("CRUD_tasks", typeUserModel.isCRUD_tasks())
                    .addParameter("CRUD_tasks_states", typeUserModel.isCRUD_tasks_states())
                    .addParameter("asign_volunteers", typeUserModel.isAsign_volunteers())
                    .executeUpdate()
                    .getKey();
            typeUserModel.setType_user_id(insertedId);
            return "Tipo de usuario creado";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TypeUserModel findByTypeUserName(String typeUserName){
        try(Connection connection = sql2o.open()){
            return connection
                    .createQuery("SELECT * FROM \"type_user\" WHERE type_user_name =:type_user_name")
                    .addParameter("type_user_name", typeUserName)
                    .executeAndFetchFirst(TypeUserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteTypeUser(TypeUserModel typeUserModel){
        try(Connection connection = sql2o.open()){
            connection.createQuery("DELETE FROM \"type_user\" WHERE type_user_id = :type_user_id")
                    .addParameter("type_user_id", typeUserModel.getType_user_id())
                    .executeUpdate();
            return "Tipo de usuario eliminado";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String updateTypeUser(TypeUserModel typeUserModel){
        try(Connection connection = sql2o.open()){
            connection.createQuery("UPDATE \"type_user\" SET type_user_name = :type_user_name, CRUD_emergencies = :CRUD_emergencies, CRUD_tasks = :CRUD_tasks, CRUD_tasks_states = :CRUD_tasks_states, asign_volunteers = :asign_volunteers WHERE type_user_id = :type_user_id")
                    .addParameter("type_user_id", typeUserModel.getType_user_id())
                    .addParameter("type_user_name", typeUserModel.getType_user_name())
                    .addParameter("CRUD_emergencies", typeUserModel.isCRUD_emergencies())
                    .addParameter("CRUD_tasks", typeUserModel.isCRUD_tasks())
                    .addParameter("CRUD_tasks_states", typeUserModel.isCRUD_tasks_states())
                    .addParameter("asign_volunteers", typeUserModel.isAsign_volunteers())
                    .executeUpdate();
            return "Tipo de usuario actualizado";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TypeUserModel findByTypeUserId(int typeUserId){
        try(Connection connection = sql2o.open()){
            return connection
                    .createQuery("SELECT * FROM \"type_user\" WHERE type_user_id =:type_user_id")
                    .addParameter("type_user_id", typeUserId)
                    .executeAndFetchFirst(TypeUserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
