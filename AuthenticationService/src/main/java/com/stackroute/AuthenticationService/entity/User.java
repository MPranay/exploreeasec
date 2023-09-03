package com.stackroute.AuthenticationService.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
public class User implements UserDetails {

    @Id

    @Email
    @NotNull(message = "Email should not be null")
    private String email;

    @NotBlank(message = "Password should not be blank.")
    @NotNull(message = "Password should not be null.")
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User() {
    }

    public User( String email, String password, UserType userType) {

        this.email = email;
        this.password = password;
        this.userType = userType;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userType.name());

        return Collections.singletonList(authority);

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return this.email;
    }
    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
