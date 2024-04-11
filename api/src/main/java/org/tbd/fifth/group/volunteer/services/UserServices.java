package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.tbd.fifth.group.volunteer.models.UserModel;
import org.tbd.fifth.group.volunteer.repositories.UserRepository;

@Repository
public class UserServices implements UserRepository {

    @Autowired
    private Sql2o sql2o;



    @Override
    public UserModel createUser(UserModel user){
        try(Connection connection = sql2o.open()){
            connection.createQuery("INSERT INTO \"user\" (type_user_id, name, password, email, phone) VALUES (:type_user_id, :name, :password, :email, :phone)", true)
                    .addParameter("type_user_id", user.getType_user_id())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .addParameter("phone", user.getPhone())
                    .executeUpdate().getKey();
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel getUser(int user_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"user\" WHERE user_id = :user_id")
                    .addParameter("user_id", user_id)
                    .executeAndFetchFirst(UserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel DeleteUser(int user_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("DELETE FROM \"user\" WHERE user_id = :user_id")
                    .addParameter("user_id", user_id)
                    .executeAndFetchFirst(UserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel updateUser(UserModel user){
        try(Connection connection = sql2o.open()){
            connection.createQuery("UPDATE \"user\" SET type_user_id = :type_user_id, name = :name, password = :password, email = :email, phone = :phone WHERE user_id = :user_id")
                    .addParameter("type_user_id", user.getType_user_id())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .addParameter("phone", user.getPhone())
                    .addParameter("user_id", user.getUser_id())
                    .executeUpdate();
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel loginUser(UserModel user){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"user\" WHERE email = :email AND password = :password")
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .executeAndFetchFirst(UserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
