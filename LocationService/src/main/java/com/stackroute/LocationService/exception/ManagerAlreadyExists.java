package com.stackroute.LocationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Invalid credentials")
public class ManagerAlreadyExists extends Throwable{
    public ManagerAlreadyExists(String message){super (message);}
}
