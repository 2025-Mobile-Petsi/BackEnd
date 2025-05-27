package com.example.petsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsiApplication.class, args);
	}

}
