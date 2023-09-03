package com.stackroute.LocationService.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LocationDTO {


    @Getter
    @Setter
    private int locationId ;
    @Getter
    @Setter
    private List<byte[]> locationImgs;
    @Getter
    @Setter
    int ticketPrice ;
    @Getter
    @Setter
    private List<String> locationImags;

    @Getter
    @Setter
    private String locationDetails;
    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    private String touristAttractions;
    @Getter
    @Setter
    private String locallyFamous;
    @Getter
    @Setter
    private String availableCommutes;


    public LocationDTO(){}

    public LocationDTO(int locationId, String location ,String locationDetails,List<byte[]> locationImgs,String locallyFamous,String availableCommutes,String touristAttractions,int ticketPrice){
        this.locationId=locationId;
        this.ticketPrice=ticketPrice;
        this.location=location;
        this.locationDetails=locationDetails;
        this.locationImgs=locationImgs;
        this.availableCommutes=availableCommutes;
        this.locallyFamous=locallyFamous;
        this.touristAttractions=touristAttractions;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "locationId='"+ locationId+ '\''+
                ", location='" + location + '\'' +
                ", locationDetails='" + locationDetails + '\'' +
                ", touristAttractions='" + touristAttractions + '\''+
                ", locallyFamous='" + locallyFamous + '\''+
                ", availableCommutes='" + availableCommutes + '\''+
                ", locationImgs='" + locationImgs + '\'' +
                ", ticketPrice='"+ticketPrice+'\''+
                '}';
    }
}