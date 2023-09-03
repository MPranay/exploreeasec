package com.stackroute.AuthenticationService.configuration;
import com.stackroute.AuthenticationService.entity.User;
import com.stackroute.AuthenticationService.entity.UserType;
import com.stackroute.AuthenticationService.rabbitmq.UserDTO;
import com.stackroute.AuthenticationService.repository.UserRepository;
import com.stackroute.AuthenticationService.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;




@Component
public class Consumer {

    public final UserServiceImpl userService;
    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    private static final Logger LOGGER= LoggerFactory.getLogger(Consumer.class);

    @Autowired
    public Consumer(UserServiceImpl userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @RabbitListener(queues = "user_queue")
    public void receiveMessageFromRabbitMq(UserDTO userDto) {
        String email = userDto.getEmail();
        String plainPassword = userDto.getPassword();
        UserType userType = userDto.getUserType();

        String hashedPassword = passwordEncoder.encode(plainPassword);


        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword); // Store the hashed password
        user.setUserType(userType);

        // Save the received message to the database
        userRepository.save(user);
        System.out.println("Received Login Credentials: Email-Id:" + email +",Password:"+plainPassword + ",UserType:"+userType);

        System.out.println("Saved user in MySQL");
    }

}