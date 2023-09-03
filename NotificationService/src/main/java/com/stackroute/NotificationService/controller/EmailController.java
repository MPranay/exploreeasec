package com.stackroute.NotificationService.controller;

import com.stackroute.NotificationService.model.EmailDetails;
import com.stackroute.NotificationService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v7")
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details) {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }
}