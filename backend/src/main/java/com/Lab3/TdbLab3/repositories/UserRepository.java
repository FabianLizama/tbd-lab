package com.Lab3.TdbLab3.repositories;

import com.Lab3.TdbLab3.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findByEmailAndPassword(String email, String password);
}