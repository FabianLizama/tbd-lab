package org.tbd.fifth.group.volunteer.repositories;


import org.tbd.fifth.group.volunteer.models.UserModel;

public interface UserRepository {

    public UserModel createUser(UserModel user);

    public UserModel getUser(int user_id);

    public UserModel DeleteUser(int user_id);

    public UserModel updateUser(UserModel user);

    public UserModel getUserByEmail(String email);

    public String loginUser(String email, String password);
}
