package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendNewsNotification(String toEmail, String username, String newsTitle, String newsDescription, String categoryName) {
        log.info("Sending email notification to: {}, for news: {}, in category: {}", toEmail, newsTitle, categoryName);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("New " + categoryName + " News: " + newsTitle);
            
            String emailBody = String.format(
                "Hello %s,\n\n" +
                "A new news article has been published in the %s category that might interest you:\n\n" +
                "Title: %s\n" +
                "Description: %s\n\n" +
                "Stay updated with the latest news!\n\n" +
                "Best regards,\nNews Aggregation Team",
                username, categoryName, newsTitle, newsDescription
            );
            
            message.setText(emailBody);
            mailSender.send(message);
            
            System.out.println("Email notification sent to " + toEmail + " for news: " + newsTitle);
            log.info("Email notification sent successfully to: {}", toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email notification to " + toEmail + ": " + e.getMessage());
            log.error("Failed to send email notification to: {}. Error: {}", toEmail, e.getMessage(), e);
            throw e;
        }
    }

}