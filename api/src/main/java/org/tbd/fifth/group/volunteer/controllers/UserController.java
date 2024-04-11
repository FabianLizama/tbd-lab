package org.tbd.fifth.group.volunteer.controllers;

import org.springframework.web.bind.annotation.*;
import org.tbd.fifth.group.volunteer.models.UserModel;
import org.tbd.fifth.group.volunteer.services.UserServices;

@CrossOrigin
@RestController
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/user")
    public UserModel createUser(@RequestBody UserModel user) {
        return userServices.createUser(user);
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestBody UserModel user) {
        return userServices.loginUser(user.getEmail(), user.getPassword());
    }

    @GetMapping("/user/{user_id}")
    public UserModel getUser(@PathVariable int user_id) {
        return userServices.getUser(user_id);
    }

    @DeleteMapping("/user/{user_id}")
    public UserModel deleteUser(@PathVariable int user_id) {
        return userServices.DeleteUser(user_id);
    }

}
