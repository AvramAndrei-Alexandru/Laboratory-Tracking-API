package com.andrei.laboratory_tracking_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.logging.Level;

@SpringBootApplication
public class LaboratoryTrackingApplication {




	public static void main(String[] args) {
		SpringApplication.run(LaboratoryTrackingApplication.class, args);
	}

}
