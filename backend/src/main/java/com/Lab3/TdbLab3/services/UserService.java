package com.Lab3.TdbLab3.services;

import com.Lab3.TdbLab3.models.UserModel;
import com.Lab3.TdbLab3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping()
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("login/{email}/{password}")
    public ResponseEntity<?> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        UserModel user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        try {
            UserModel savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }
}