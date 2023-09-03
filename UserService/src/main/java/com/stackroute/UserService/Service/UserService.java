package com.stackroute.UserService.Service;
import com.stackroute.UserService.Model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();



    User getUserById(int userId);

    User getUserByEmail(String email);

    User updateUserById(User user, int userId);
}
