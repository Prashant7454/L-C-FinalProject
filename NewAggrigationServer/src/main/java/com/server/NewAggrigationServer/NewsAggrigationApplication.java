package com.server.NewAggrigationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.server.NewAggrigationServer")
public class NewsAggrigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAggrigationApplication.class, args);
	}

}
