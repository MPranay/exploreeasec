package com.stackroute.LocationService.controller;

import com.stackroute.LocationService.domain.Manager;
import com.stackroute.LocationService.domain.LocationDTO;
import com.stackroute.LocationService.domain.UserType;
import com.stackroute.LocationService.exception.ManagerAlreadyExists;
import com.stackroute.LocationService.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.LocationService.exception.ManagerDoesNotExist;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v2/manager")
public class ManagerController {

    @Autowired
    public ManagerService managerService;

    public ResponseEntity<?> responseEntity;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllManagers(){
        List<Manager> managers= managerService.getAllManagers();
        responseEntity=new ResponseEntity<>(managers,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> getManagerByEmail(@PathVariable("email")String email){
        Manager managers= managerService.getManagerByEmail(email);
        responseEntity=new ResponseEntity<>(managers,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/getbyID/{managerId}")
    public ResponseEntity<?> getManagerByID(@PathVariable("managerId")int managerId){
        Manager managers= managerService.getManagerByID(managerId);
        responseEntity=new ResponseEntity<>(managers,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/location/getAll")
    public ResponseEntity<?> getAllLocationdetails(){
        List<LocationDTO> locationdetails= managerService.getAllLocationDetails();
        responseEntity=new ResponseEntity<>(locationdetails,HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/location/get/{locationId}")
    public ResponseEntity<?> getLocationById(@PathVariable("locationId") int locationId){
        LocationDTO locationdetails= managerService.getLocationById(locationId);
        responseEntity=new ResponseEntity<>(locationdetails,HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<?> register(@RequestParam("username") String username,
                                      @RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("contact") Long contact,
                                      @RequestParam("age") int age,
                                      @RequestParam("gender") Manager.Gender gender,
                                      @RequestParam("profilePicture") MultipartFile profilePicture,
                                      @RequestParam("userType") UserType userType,
                                      @RequestParam("location") String location,
                                      @RequestParam("locationId") int locationId) throws ManagerAlreadyExists {
        try{

            byte[] imageData = null;
            imageData = profilePicture.getBytes();

            Manager manager = new Manager();

            manager.setUsername(username);
            manager.setName(name);
            manager.setEmail(email);
            manager.setPassword(password);
            manager.setContact(contact);
            manager.setAge(age);
            manager.setGender(gender);
            manager.setProfilePicture(imageData);
            manager.setUserType(UserType.MANAGER);
            manager.setLocation(location);
            manager.setLocationId(locationId);
            managerService.register(manager);
            responseEntity = new ResponseEntity<>("Manager Added Successfully",HttpStatus.CREATED);
        }catch(ManagerAlreadyExists e){
            responseEntity=new ResponseEntity<>("Manager Already Exist",HttpStatus.CONFLICT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }


    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteManager(@PathVariable("email") String email){
        try{
            managerService.deleteManager(email);
            responseEntity=new ResponseEntity<>("Manager Deleted Successfully",HttpStatus.OK);
        }catch (ManagerDoesNotExist e) {

            responseEntity=new ResponseEntity<>("NO Manager By this Email",HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateManager(@PathVariable("email") String email,@RequestParam("username") String username,
                                           @RequestParam("name") String name,
                                           @RequestParam("contact") Long contact,
                                           @RequestParam("age") int age,
                                           @RequestParam("managerId") int managerId,

                                           @RequestParam("gender") Manager.Gender gender,
                                           @RequestParam(name="profilePicture", required = false) MultipartFile profilePicture){
        try{
            byte[] imageData = null;
            imageData = profilePicture.getBytes();
            Manager manager = new Manager();
            manager.setUsername(username);
            manager.setName(name);
            manager.setContact(contact);
            manager.setAge(age);
            manager.setGender(gender);

            manager.setProfilePicture(imageData);

            managerService.updateManager(email,manager);
            responseEntity = new ResponseEntity<>("Manager Updated Successfully",HttpStatus.OK);
        } catch(ManagerDoesNotExist nt){
            responseEntity=new ResponseEntity<>("No Manager found to Update",HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }
    @PutMapping("/update/location/{managerId}")
    public ResponseEntity<?> updateLocation(@PathVariable("managerId") int managerId,
                                            @RequestParam("locationDetails") String locationDetails,
                                            @RequestParam("availableCommutes") String availableCommutes,
                                            @RequestParam("locallyFamous") String locallyFamous,
                                            @RequestParam("ticketPrice") int ticketPrice,
                                            @RequestParam("touristAttractions") String touristAttractions,
                                            @RequestParam("locationImgs") MultipartFile[] locationImages
    ){
        try{
            Manager user = new Manager();
            user.setTouristAttractions(touristAttractions);
            user.setLocallyFamous(locallyFamous);
            user.setLocationDetails(locationDetails);
            user.setAvailableCommutes(availableCommutes);
            user.setTicketPrice(ticketPrice);
            List<byte[]> imageBytesList = new ArrayList<>();
            for (MultipartFile locationImage : locationImages) {
                byte[] imageBytes = locationImage.getBytes();
                imageBytesList.add(imageBytes);
                // Save imageBytes to the database or store them
            }
            user.setLocationImgs(imageBytesList);
            managerService.updateLocation(managerId, user);
            responseEntity = new ResponseEntity<>("Location details Updated Successfully",HttpStatus.OK);
        } catch(ManagerDoesNotExist nt){
            responseEntity=new ResponseEntity<>("No location with given pincode found to Update",HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

}
