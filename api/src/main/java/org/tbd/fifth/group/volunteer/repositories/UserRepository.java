package org.tbd.fifth.group.volunteer.repositories;


import org.springframework.http.ResponseEntity;
import org.tbd.fifth.group.volunteer.models.UserModel;

public interface UserRepository {

    public ResponseEntity<Object> createUser(UserModel user);

    public UserModel getUser(int user_id);

    public UserModel DeleteUser(int user_id);

    public UserModel updateUser(UserModel user);

    public UserModel getUserByEmail(String email);

    public ResponseEntity<Object> loginUser(String email, String password);
}
