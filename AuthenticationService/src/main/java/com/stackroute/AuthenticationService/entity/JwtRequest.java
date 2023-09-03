package com.stackroute.AuthenticationService.entity;

public class JwtRequest {

    String email;
    String password;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    UserType userType;

    public JwtRequest() {
    }

    public JwtRequest(String email, String password, UserType userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
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
}
