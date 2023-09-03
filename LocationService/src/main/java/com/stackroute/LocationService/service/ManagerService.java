package com.stackroute.LocationService.service;


import com.stackroute.LocationService.domain.Manager;
import com.stackroute.LocationService.domain.LocationDTO;
import com.stackroute.LocationService.exception.ManagerAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.LocationService.exception.ManagerDoesNotExist;
import java.util.List;


public interface ManagerService {
    @Autowired
    String register(Manager manager) throws ManagerAlreadyExists;
    List<Manager> getAllManagers();
    String deleteManager(String email) throws ManagerDoesNotExist ;
    String updateManager(String email, Manager manager) throws ManagerDoesNotExist;
    String updateLocation(int managerId, Manager manager) throws ManagerDoesNotExist;
    List<LocationDTO> getAllLocationDetails() ;
    LocationDTO getLocationById(int locationId);
    Manager getManagerByEmail(String email);
    Manager getManagerByID(int managerId);
}

