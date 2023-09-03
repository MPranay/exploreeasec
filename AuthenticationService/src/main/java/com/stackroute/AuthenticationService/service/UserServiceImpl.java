package com.stackroute.AuthenticationService.service;

import com.stackroute.AuthenticationService.entity.UserType;
import com.stackroute.AuthenticationService.exception.UserException;
import com.stackroute.AuthenticationService.entity.User;
import com.stackroute.AuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) throws UserException {

        Optional<User> optUser = userRepository.findByEmail(user.getUsername());
        if (optUser.isPresent()) {
            throw new UserException("Email id already registered. Try with new Email.");
        }
        user.setUserType(UserType.USER);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

//        return userRepository.findById(user.getId()).get();

    @Override
    public User saveUserAsAdmin(User user) throws UserException {

        Optional<User> optUser = userRepository.findByEmail(user.getUsername());
        if(optUser.isPresent()){
            throw new UserException("Email id already registered. Try with new Email.");
        }
        user.setUserType(UserType.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    @Override
    public boolean loadUserbyUsername(String email) {

            Optional<User> optUser = this.userRepository.findByEmail(email);

        return optUser.isPresent();
    }

    @Override
    public User updateUser(User user) throws UserException {

        Optional<User> optUser = userRepository.findByEmail(user.getUsername());
        if(optUser.isEmpty()){
            throw new UserException("User not found. Please provide correct user details.");
        }
        userRepository.save(user);

        return userRepository.findByEmail(user.getEmail()).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
//
//    
//  @Override
//  public User deleteUserByEmail(String email) throws UserException {
//    Optional<User> optUser = userRepository.findByEmail(email);
//
//    if(optUser.isEmpty()){
//        throw new UserException("User does not exist");
//    }
//    User deletedUser = optUser.get();
//    userRepository.delete(optUser.get());
//    return deletedUser;
//}

    @Override
    public User getUserByEmailId(String email) throws UserException {
        Optional<User> optUser = userRepository.findByEmail(email);

        if(optUser.isEmpty()){
            throw new UserException("User does not exist");
        }
        User user = optUser.get();

        return user;
    }



}
