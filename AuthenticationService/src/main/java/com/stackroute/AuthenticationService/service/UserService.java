package com.stackroute.AuthenticationService.service;

import com.stackroute.AuthenticationService.exception.UserException;
import com.stackroute.AuthenticationService.entity.User;

import java.util.List;

public interface UserService {

    User updateUser(User user) throws UserException;
    User saveUser(User user) throws UserException;
    User saveUserAsAdmin(User user)throws UserException;

    boolean loadUserbyUsername(String email);
    List<User> getAllUsers();

//        User deleteUserByEmail(String email) throws UserException;
    User getUserByEmailId(String email) throws UserException;
}
