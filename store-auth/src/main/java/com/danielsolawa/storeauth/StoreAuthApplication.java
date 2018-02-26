package com.danielsolawa.storeauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;

@EnableAsync
@SpringBootApplication
public class StoreAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreAuthApplication.class, args);
	}




}
