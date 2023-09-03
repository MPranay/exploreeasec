package com.stackroute.LocationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Does not exist")
public class ManagerDoesNotExist extends Throwable{
    public ManagerDoesNotExist(String message) {super(message);}
}
