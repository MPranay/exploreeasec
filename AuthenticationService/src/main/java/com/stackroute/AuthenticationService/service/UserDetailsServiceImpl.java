package com.stackroute.AuthenticationService.service;
import com.stackroute.AuthenticationService.entity.User;
import com.stackroute.AuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optUser = this.userRepository.findByEmail(username);

        if(optUser.isEmpty()){
            throw  new UsernameNotFoundException("User not found with the given credentials.");
        }
        return optUser.get();
    }
}
