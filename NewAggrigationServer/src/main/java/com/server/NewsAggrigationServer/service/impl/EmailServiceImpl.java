package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendNewsNotification(String toEmail, String username, String newsTitle, String newsDescription, String categoryName) {
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
        } catch (Exception e) {
            System.err.println("Failed to send email notification to " + toEmail + ": " + e.getMessage());
        }
    }
} 