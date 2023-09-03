package com.stackroute.productwebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Priyanshu Singh
 */
@org.springframework.stereotype.Controller
public class Controller
{

    @GetMapping("/")
    public String getWebapp(){
        return "index.html";
    }
}
