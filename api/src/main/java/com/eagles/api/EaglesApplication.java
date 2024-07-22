package com.eagles.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EaglesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaglesApplication.class, args);
	}

}
