package com.server.NewsAggrigationServer.service;

public interface EmailService {
    void sendNewsNotification(String toEmail, String username, String newsTitle, String newsDescription, String categoryName);
} 