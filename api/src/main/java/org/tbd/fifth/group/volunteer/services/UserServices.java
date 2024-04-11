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

    @Autowired
    private JwtMiddlewareServices jwtMiddlewareServices;

    @Override
    public UserModel createUser(UserModel user){
        try(Connection connection = sql2o.open()){
            Integer userId = (Integer) connection.createQuery("INSERT INTO \"userm\" (type_user_id, name, password, email, phone) VALUES (:type_user_id, :name, :password, :email, :phone)", true)
                    .addParameter("type_user_id", user.getType_user_id())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .addParameter("phone", user.getPhone())
                    .executeUpdate().getKey();

            user.setUser_id(userId); // Asegúrate de establecer el ID del usuario
            String token = jwtMiddlewareServices.generateToken(user); // Generar token

            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public UserModel getUser(int user_id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"userm\" WHERE user_id = :user_id")
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
            return connection.createQuery("DELETE FROM \"userm\" WHERE user_id = :user_id")
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
            connection.createQuery("UPDATE \"userm\" SET type_user_id = :type_user_id, name = :name, password = :password, email = :email, phone = :phone WHERE user_id = :user_id")
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
    public String loginUser(String email, String password){
        try{
            UserModel user = getUserByEmail(email);
            if (user.getPassword().compareTo(password) == 0){
                return jwtMiddlewareServices.generateToken(user);
            }
            return "Contraseña incorrecta";

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public UserModel getUserByEmail(String email){
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM \"userm\" WHERE email = :email")
                    .addParameter("email", email)
                    .executeAndFetchFirst(UserModel.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
