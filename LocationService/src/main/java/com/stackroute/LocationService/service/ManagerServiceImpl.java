package com.stackroute.LocationService.service;


import com.stackroute.LocationService.config.Producer;
import com.stackroute.LocationService.domain.Manager;
import com.stackroute.LocationService.domain.LocationDTO;
import com.stackroute.LocationService.rabbitmq.domain.ManagerDTO;
import com.stackroute.LocationService.exception.ManagerAlreadyExists;
import com.stackroute.LocationService.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.LocationService.exception.ManagerDoesNotExist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Base64;
@Service
public class ManagerServiceImpl implements ManagerService {

    private Repository repository;
    @Autowired
    Producer producer;

    @Autowired
    public ManagerServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String register(Manager manager) throws ManagerAlreadyExists {
        ManagerDTO managerdto=new ManagerDTO();
        managerdto.setEmail(manager.getEmail());
        managerdto.setPassword(manager.getPassword());
        managerdto.setUserType(manager.getUserType());
        if (repository.findByEmail(manager.getEmail())!=null) {

            throw new ManagerAlreadyExists("Manager Already Exist for given email");
        } else if (repository.findByLocationId(manager.getLocationId())!=null) {
            throw new ManagerAlreadyExists("Manager Already Exist for given pincode");
        }else {
            List<Manager> x= repository.findAll();
            manager.setManagerId(x.size()+1);
            repository.save(manager);
            producer.sendMessageToRabbitMq(managerdto);
            return "Manager added Succesfully";
        }


    }

    @Override
    public List<Manager> getAllManagers(){
        return repository.findAll();
    }

    @Override
    public Manager getManagerByEmail(String email){
        return repository.findByEmail(email);
    }
    @Override
    public Manager getManagerByID(int managerId){
        return repository.findByManagerId(managerId);
    }

    @Override
    public String deleteManager(String email) throws ManagerDoesNotExist{
        Manager exist= repository.findByEmail(email);
        if(exist!=null){
            repository.delete(exist);
            return "Deleted Successfully";
        }
        throw new ManagerDoesNotExist("No Manager to delete");
    }

    @Override
    public String updateManager(String email, Manager updatedManager) throws ManagerDoesNotExist{
        Optional<Manager> exist= Optional.ofNullable(repository.findByEmail(email));
        if(exist.isPresent()){
            Manager existingManager =exist.get();
            existingManager.setUsername(updatedManager.getUsername());
            existingManager.setName(updatedManager.getName());
            existingManager.setContact(updatedManager.getContact());
            existingManager.setAge(updatedManager.getAge());
            existingManager.setGender(updatedManager.getGender());
            existingManager.setProfilePicture(updatedManager.getProfilePicture());


            repository.save(existingManager);


            return "Updated Successfully";
        }
        throw new ManagerDoesNotExist("No Manager to update");
    }

    @Override
    public String updateLocation(int managerId, Manager updatedManager) throws ManagerDoesNotExist{
        Optional<Manager> exist= Optional.ofNullable(repository.findByManagerId(managerId));
        if(exist.isPresent()){
            Manager existingManager =exist.get();

            existingManager.setLocationImgs(updatedManager.getLocationImgs());
            existingManager.setLocationDetails(updatedManager.getLocationDetails());
            existingManager.setAvailableCommutes(updatedManager.getAvailableCommutes());
            existingManager.setLocallyFamous(updatedManager.getLocallyFamous());
            existingManager.setTouristAttractions(updatedManager.getTouristAttractions());
            existingManager.setTicketPrice(updatedManager.getTicketPrice());
            repository.save(existingManager);


            return "Location details Updated Successfully";
        }
        throw new ManagerDoesNotExist("No location with given pincode found");
    }

    @Override
    public List<LocationDTO> getAllLocationDetails(){
        List<Manager> managers=repository.findAll();
        List<LocationDTO> locationDTOList= new ArrayList<>();

        for (Manager manager: managers){
            LocationDTO details= new LocationDTO();
            details.setLocationId(manager.getLocationId());
            details.setLocation(manager.getLocation());
            details.setLocationDetails(manager.getLocationDetails());
            List<String> imageUrls = new ArrayList<>();
            for (byte[] imageBytes : manager.getLocationImgs()) {
                String imageUrl = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
                imageUrls.add(imageUrl);
            }
            details.setLocationImags(imageUrls);
            details.setLocationImgs(manager.getLocationImgs());
            details.setTouristAttractions(manager.getTouristAttractions());
            details.setAvailableCommutes(manager.getAvailableCommutes());
            details.setLocallyFamous(manager.getLocallyFamous());
            details.setTicketPrice(manager.getTicketPrice());

            locationDTOList.add(details);
        }

        return locationDTOList;
    }

    @Override
    public LocationDTO getLocationById(int locationI){
        Manager manager=repository.findByLocationId(locationI);

        LocationDTO details= new LocationDTO();
        details.setLocationId(manager.getLocationId());
        details.setLocation(manager.getLocation());
        details.setLocationDetails(manager.getLocationDetails());
        List<String> imageUrls = new ArrayList<>();
        for (byte[] imageBytes : manager.getLocationImgs()) {
            String imageUrl = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
            imageUrls.add(imageUrl);
        }
        details.setLocationImags(imageUrls);
        details.setLocationImgs(manager.getLocationImgs());
        details.setTouristAttractions(manager.getTouristAttractions());
        details.setAvailableCommutes(manager.getAvailableCommutes());
        details.setLocallyFamous(manager.getLocallyFamous());
        details.setTicketPrice(manager.getTicketPrice());

        return details;
    }

}
