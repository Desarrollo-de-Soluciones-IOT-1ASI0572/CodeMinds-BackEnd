package com.codeminds.edugo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EdugoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdugoBackendApplication.class, args);
	}

}
