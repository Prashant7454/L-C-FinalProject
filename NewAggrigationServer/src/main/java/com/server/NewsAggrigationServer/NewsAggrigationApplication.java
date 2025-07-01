package com.server.NewsAggrigationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.server.NewsAggrigationServer")
@EnableScheduling
public class NewsAggrigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAggrigationApplication.class, args);
	}

}
