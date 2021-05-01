package com.javabugs.logmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LogmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogmanagerApplication.class, args);
	}

}
