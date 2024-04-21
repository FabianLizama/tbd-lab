package org.tbd.fifth.group.volunteer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> createUser(UserModel user){
        try (Connection connection = sql2o.open()) {
            // Verificar si el usuario ya existe
            Integer count = connection.createQuery("SELECT COUNT(*) FROM \"userm\" WHERE email = :email")
                    .addParameter("email", user.getEmail())
                    .executeScalar(Integer.class);

            if (count != null && count > 0) {
                // Devolver un código de error 409 Conflict directamente
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con el mismo email.");
            }

            // Si no existe, proceder con la creación del usuario
            Integer userId = (Integer) connection.createQuery("INSERT INTO \"userm\" (type_user_id, name, password, email, phone) VALUES (:type_user_id, :name, :password, :email, :phone)", true)
                    .addParameter("type_user_id", user.getType_user_id())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .addParameter("phone", user.getPhone())
                    .executeUpdate().getKey();

            user.setUser_id(userId); // Establecer el ID del usuario
            String token = jwtMiddlewareServices.generateToken(user); // Generar token

            // Devolver el token con un código 200 OK
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // Manejar otras excepciones no esperadas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
    public ResponseEntity<Object> loginUser(String email, String password){
        try{
            UserModel user = getUserByEmail(email);
            if (user.getPassword().compareTo(password) == 0){
                String token = jwtMiddlewareServices.generateToken(user);

                return ResponseEntity.ok(token);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");

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
