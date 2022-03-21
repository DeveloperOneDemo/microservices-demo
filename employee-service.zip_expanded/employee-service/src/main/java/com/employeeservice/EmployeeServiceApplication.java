package com.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	
	// i can configure my beans here beacuse this class can be used as a java based configuration file ( explore the @SpringBootApplication annotation on the top)
	@Bean
	public RestTemplate sendRestTemplate() {
		return new RestTemplate();
	}
	
}