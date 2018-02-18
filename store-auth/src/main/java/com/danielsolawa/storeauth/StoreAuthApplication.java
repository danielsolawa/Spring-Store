package com.danielsolawa.storeauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class StoreAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreAuthApplication.class, args);
	}
}
