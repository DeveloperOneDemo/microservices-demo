package com.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	
	// i can configure my beans here beacuse this class can be used as a java based configuration file ( explore the @SpringBootApplication annotation on the top)
	@Bean
	@LoadBalanced // added because employee-service contacts department-service through api-gateway
	public RestTemplate sendRestTemplate() {
		return new RestTemplate();
	}
	
}
