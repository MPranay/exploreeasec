package com.stackroute.NotificationService.service;

import com.stackroute.NotificationService.model.EmailDetails;



public interface EmailService {
    String sendSimpleMail(EmailDetails details);
//    String sendMailWithAttachment(EmailDetails details, Map<String,Object> model);
}