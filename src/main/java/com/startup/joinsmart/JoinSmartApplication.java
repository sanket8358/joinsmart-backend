package com.startup.joinsmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class JoinSmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoinSmartApplication.class, args);
	}

}