package com.stackroute.UserService.Repository;
import com.stackroute.UserService.Model.User;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Integer> {

    User findByEmail(String email);
    User  findById(int userId);
}

