package org.tbd.fifth.group.volunteer.models;


public class UserModel {

    private int user_id;

    private int type_user_id;

    private String name;

    private String password;

    private String email;

    private String phone;

    public UserModel() {
    }

    public UserModel(int user_id, int type_user_id, String name, String password, String email, String phone) {
        this.user_id = user_id;
        this.type_user_id = type_user_id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType_user_id() {
        return type_user_id;
    }

    public void setType_user_id(int type_user_id) {
        this.type_user_id = type_user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
