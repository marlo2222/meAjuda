package com.meAjuda.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
