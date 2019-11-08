package com.meAjuda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableCaching
public class MeAjudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeAjudaApplication.class, args);
	}

}
