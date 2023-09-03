package com.stackroute.UserService.Controller;

import com.stackroute.UserService.Model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.UserService.Model.User;
import com.stackroute.UserService.Service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping("/api/v6/")

public class UserController {

    private ResponseEntity<?> responseEntity;
    private final UserService userService;
    @Autowired
    public UserController(UserService userService )
    {
        this.userService = userService;

    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestParam("profilePicture") MultipartFile profilePicture,
                                      @RequestParam("username") String username,
                                      @RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("contact") Long contact,
                                      @RequestParam("age") int age,
                                      @RequestParam("gender") String gender,
                                      @RequestParam("walletBalance") double walletBalance,
                                      @RequestParam("userType")UserType userType) throws IOException {

        try{
        byte[] imageData = null;
        imageData = profilePicture.getBytes();
        User user = new User();
        List<User> list =  userService.getAllUsers();
        int length=list.size();
        user.setUserId(length+1);
        user.setUsername(username);
        user.setAge(age);
        user.setContact(contact);
        user.setGender(gender);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setWalletBalance(walletBalance);
        user.setUserType(userType);
        user.setProfilePicture(imageData);
        System.out.print(length+1);

        User createdUser = userService.saveUser(user);
        if(createdUser==null)return responseEntity=new ResponseEntity<>("User Already Exist",HttpStatus.CONFLICT);
    } catch (IOException e){
            return new ResponseEntity<>("error while saving picture", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        return responseEntity = new ResponseEntity<>("User Created" , HttpStatus.CREATED);

    }


    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){

        List<User> list =  userService.getAllUsers();
        responseEntity = new ResponseEntity<>(list,HttpStatus.OK);
        return responseEntity;

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user!= null) {
            return responseEntity.ok(user);
        }
        return responseEntity.notFound().build();
    }

    @GetMapping("/users/getByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/users/update/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable int userId,
                                            @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture,
                                            @RequestParam("username") String username,
                                            @RequestParam("name") String name,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            @RequestParam("contact") Long contact,
                                            @RequestParam("age") int age,
                                            @RequestParam("gender") String gender,
                                            @RequestParam("walletBalance") double walletBalance,
                                            @RequestParam("userType")UserType userType){

        try{
            byte[] imageData = null;

            User user = new User();
            user.setUserId(userId);
            user.setUsername(username);
            user.setAge(age);
            user.setContact(contact);
            user.setGender(gender);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setWalletBalance(walletBalance);
            user.setUserType(userType);
            if(profilePicture!=null){
                imageData = profilePicture.getBytes();
                user.setProfilePicture(imageData);
            }
            User createdUser = userService.updateUserById(user, userId);
            if(createdUser==null)return responseEntity=new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        } catch (IOException e){
            return new ResponseEntity<>("error while saving picture", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        return responseEntity = new ResponseEntity<>("User Updated" , HttpStatus.OK);

    }
}
