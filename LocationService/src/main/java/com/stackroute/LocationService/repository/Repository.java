package com.stackroute.LocationService.repository;


import com.stackroute.LocationService.domain.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Manager,String> {
    Manager findByEmail(String email);
    Manager findByManagerId(int managerId);
    Manager findByLocationId(int locationId);
}