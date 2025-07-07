package com.server.NewsAggrigationServer.util;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {
    @Test
    void testRestTemplateBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        assertNotNull(restTemplate);
        context.close();
    }
} 