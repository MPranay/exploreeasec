package com.stackroute.UserService.Model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Document
public class User {
@Id

    int userId ;
    String username;
    String name;
    String email;
    String password;
    Long contact;
    int age;
    String gender;
    byte[] profilePicture;
    double walletBalance;



    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Enumerated(EnumType.STRING)
    private UserType userType;
    public User() {
    }

    public User(int userId,
                String username,
                String name,
                String email,
                String password,
                Long contact,
                int age,
                String gender,
                byte[] profilePicture,
                Double walletBalance) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.walletBalance = walletBalance;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", walletBalance=" + walletBalance +
                '}';
    }


}
