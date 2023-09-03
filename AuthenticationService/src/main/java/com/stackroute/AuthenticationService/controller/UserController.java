package com.stackroute.AuthenticationService.controller;
import com.stackroute.AuthenticationService.entity.NotificationDetails;
import com.stackroute.AuthenticationService.exception.UserException;

import com.stackroute.AuthenticationService.entity.User;
import com.stackroute.AuthenticationService.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RabbitTemplate template;
    @PostMapping("/save/user")
    User saveUser(@Valid @RequestBody User user) throws UserException {
        User savedUser = this.userServiceImpl.saveUser(user);

        NotificationDetails msg = new NotificationDetails();
        msg.setRecipient(savedUser.getUsername());
        msg.setSubject("Registration Successful");
        msg.setMsgBody("Hi "+user.getEmail()+", your account has been successfully registered as user account.");

//        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.NOTIFICATION_ROUTING_KEY,msg);
//        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.SELLER_SERVICE_ROUTING_KEY,registeredSeller);
        return savedUser;
    }
    @PostMapping("/save/admin")
    User saveUserAsAdmin (@Valid @RequestBody User user) throws UserException {
        User savedUser = this.userServiceImpl.saveUserAsAdmin(user);

        NotificationDetails msg = new NotificationDetails();
        msg.setRecipient(savedUser.getUsername());
        msg.setSubject("Registration Successful");
        msg.setMsgBody("Hi "+user.getEmail()+", your account has been successfully registered as user account.");

//        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.NOTIFICATION_ROUTING_KEY,msg);
//        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.SELLER_SERVICE_ROUTING_KEY,registeredSeller);
        return savedUser;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("users")
    List<User> getAllUser(){
       return this.userServiceImpl.getAllUsers();
    }


//    @DeleteMapping("user/{email}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    User deleteUserByEmail(@PathVariable String email) throws UserException {
//       return this.userServiceImpl.deleteUserByEmail(email);
//    }


}
