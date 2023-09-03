package com.stackroute.AuthenticationService.rabbitmq;


import com.stackroute.AuthenticationService.entity.UserType;



public class UserDTO {


    public String getPassword() {
        return password;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager;
    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private UserType userType;

    public void setEmail(String email) {
        this.email = email;
    }

   private String email;
    private String password;

}