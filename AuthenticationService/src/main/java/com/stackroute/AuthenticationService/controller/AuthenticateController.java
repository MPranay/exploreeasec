package com.stackroute.AuthenticationService.controller;


import com.stackroute.AuthenticationService.entity.JwtRequest;
import com.stackroute.AuthenticationService.entity.JwtResponse;

import com.stackroute.AuthenticationService.exception.UserException;
import com.stackroute.AuthenticationService.security.JwtUtil;
import com.stackroute.AuthenticationService.service.UserDetailsServiceImpl;
import com.stackroute.AuthenticationService.service.UserServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/login")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RabbitTemplate template;
    @PostMapping("/authenticate")
    public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws UserException {

        try{
            authenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
        } catch(UserException e){
            throw new UserException(e.getMessage());
        }

        boolean userDetails = this.userService.loadUserbyUsername(jwtRequest.getEmail());


        String token = jwtUtil.generateToken(jwtRequest);

        this.userService.getUserByEmailId(jwtRequest.getEmail());

        return new JwtResponse(token);

    }
    private void authenticate(String username, String password) throws UserException{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e){
            throw new UserException(e.getMessage());
        } catch (BadCredentialsException e){
            throw new UserException(e.getMessage());
        }
    }
}
