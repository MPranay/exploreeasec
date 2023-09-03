package com.stackroute.NotificationService.service;

import com.stackroute.NotificationService.model.EmailDetails;
import freemarker.template.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration config;

    @Value("${spring.mail.username}") private String sender;
    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

//    @Override
//    public String sendMailWithAttachment(EmailDetails details, Map<String, Object> model) {
//        MimeMessage mimeMessage
//                = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            // Setting multipart as true for attachments to
//            // be send
//            mimeMessageHelper
//                    = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//
//            Template t = config.getTemplate("email-template.ftl");
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//            System.out.println("After Html");
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(html,true);
//            mimeMessageHelper.setSubject(
//                    details.getSubject());
//
////             Adding the attachment
////            FileSystemResource file
////                    = new FileSystemResource(
////                    new File(details.getAttachment()));
////
////            mimeMessageHelper.addAttachment(
////                    file.getFilename(), file);
////
////             Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//
//        // Catch block to handle MessagingException
//        catch (MessagingException | IOException | TemplateException e) {
//
//            // Display message when exception occurred
//            System.out.println(e.getMessage());
//            return "Error while sending mail!!!";
//        }
//    }
}

