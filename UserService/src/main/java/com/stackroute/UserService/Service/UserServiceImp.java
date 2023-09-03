package com.stackroute.UserService.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.UserService.Model.User;
import com.stackroute.UserService.Repository.UserRepository;
import com.stackroute.UserService.rabbitmq.UserDTO;
import com.stackroute.UserService.Config.Producer;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    Producer producer;

    @Override
    public User saveUser(User user) {


        UserDTO userdto = new UserDTO();
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());
        userdto.setUserType(user.getUserType());


        User userObj = userRepository.findByEmail(user.getEmail());

        if (userObj == null) {

            userRepository.save(user);
            System.out.println("saved user in mongo");
            producer.sendMessageToRabbitMq(userdto);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(int userId) {
        User user;
        user = userRepository.findById(userId);
        return user;

    }
    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUserById(User user, int userId){
        User updated = userRepository.findById(userId);
        if(updated!=null){
            updated.setUserType(user.getUserType());
            updated.setUsername(user.getUsername());
            if(user.getProfilePicture() != null)
                updated.setProfilePicture(user.getProfilePicture());
            updated.setAge(user.getAge());
            updated.setPassword(user.getPassword());
            updated.setEmail(user.getEmail());
            updated.setGender(user.getGender());
            updated.setContact(user.getContact());
            updated.setWalletBalance(user.getWalletBalance());
            updated.setUserId(user.getUserId());

            UserDTO userdto = new UserDTO();
            userdto.setEmail(user.getEmail());
            userdto.setPassword(user.getPassword());
            userdto.setUserType(user.getUserType());
            userRepository.save(updated);
            producer.sendMessageToRabbitMq(userdto);
            return updated;

        }
        return null;
    }

    







    }







