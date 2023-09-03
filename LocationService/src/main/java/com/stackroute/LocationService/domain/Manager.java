package com.stackroute.LocationService.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document
public class Manager {
    @Id

    @Getter
    @Setter
    int managerId ;
    @Getter
    @Setter
    int locationId ;// to be input as pincode
    @Getter
    @Setter
    String username;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    String email;
    @Getter
    @Setter
    String password;
    @Getter
    @Setter
    String location;
    @Getter
    @Setter
    Long contact;
    @Getter
    @Setter
    int age;
    public enum Gender {
        Male,MALE,
        Female,FEMALE,
        Other,OTHER
    }

    public Gender gender;
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gende) {
        gender = gende;
    }




    @Getter
    @Setter
    byte[] profilePicture;
    @Getter
    @Setter
    private List<byte[]> locationImgs;
    @Getter
    @Setter
    private String locationDetails;

    @Getter
    @Setter
    private String touristAttractions;
    @Getter
    @Setter
    private String locallyFamous;
    @Getter
    @Setter
    private String availableCommutes;
    @Getter
    @Setter
    int ticketPrice ;


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private UserType userType;
    public Manager(){}
    public Manager(int managerId , String username, UserType userType, String name, String email, String password, String location, Long contact, int age, Gender genderT, String locationDetails, List<byte[]> locationImgs, byte[] profilePicture, String locallyFamous, String availableCommutes, String touristAttractions, int locationId , int ticketPrice){
        this.managerId=managerId;
        this.username=username;
        this.ticketPrice=ticketPrice;
        this.name=name;
        this.email=email;
        this.password=password;
        this.location=location;
        this.contact=contact;
        this.age=age;
        gender=genderT;
        this.locationDetails=locationDetails;
        this.locationImgs=locationImgs;
        this.profilePicture=profilePicture;
        this.userType=userType;
        this.availableCommutes=availableCommutes;
        this.locallyFamous=locallyFamous;
        this.touristAttractions=touristAttractions;
        this.locationId=locationId;

    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId='"+ managerId+ '\''+
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", contact='" + contact + '\'' +
                ", age='" + age +'\''+
                ", gender='" + email + '\'' +
                ", locationDetails='" + locationDetails + '\'' +
                ", touristAttractions='" + touristAttractions + '\''+
                ", locallyFamous='" + locallyFamous + '\''+
                ", availableCommutes='" + availableCommutes + '\''+
                ", locationImgs='" + locationImgs + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", userType='" +userType  + '\'' +
                ", locationId='"+locationId+'\''+
                ", ticketPrice='"+ticketPrice+'\''+
                '}';
    }
}
