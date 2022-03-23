package com.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employeeservice.dao.EmployeeRepository;
import com.employeeservice.entity.Employee;
import com.employeeservice.pojo.DepartmentPojo;
import com.employeeservice.pojo.MergedEmployeeDepartmentPojo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	
	private static final String SERVICE_A = "serviceA";

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired // i can autowire RestTemplate here only if it is configured as a bean in the main/configuration class
	RestTemplate restTemplate;
	
	// we do not create object, so use @Autowired to tell the spring framework to create the object for you
	//RestTemplate restTemplate = new RestTemplate();
	
	public Employee saveEmployee(Employee employee) {
		log.info("inside saveEmployee of EmployeeService");
		return employeeRepository.save(employee);
	}
	// create the fallback method fallBackGetEmployee() with the same signature of the method itself with an extra exception argument
	@CircuitBreaker(name=SERVICE_A, fallbackMethod ="fallBackGetEmployee" )
	// i want to work with RestTemplate here because i have to consume an endpoint of department-service
	public MergedEmployeeDepartmentPojo getEmployee(int eid) {
		log.info("inside getEmployee of EmployeeService");
		Employee employee = employeeRepository.findById(eid).get();
		// now that we have the employee info with the department id, we can consume the get a department endpoint from department-service
		// for this we need RestTemplate
		// DepartmentPojo department = restTemplate.getForObject("http://localhost:5001/api/departments/"+employee.getDeptId(), DepartmentPojo.class);
		DepartmentPojo department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/api/departments/"+employee.getDeptId(), DepartmentPojo.class);
		
		MergedEmployeeDepartmentPojo medp = new MergedEmployeeDepartmentPojo(employee, department);
		return medp;
	}
	
	// create the fallback method fallBackGetEmployee() with the same signature of the method itself with an extra exception argument
	public MergedEmployeeDepartmentPojo fallBackGetEmployee(int eid, Exception exception) {
		// here goes the code for the fall back if the circuit breakers goes to open state
		// open state is reached when employee-service fails in consuming an endpoint of depart-service
		System.out.println("Department-service failed");
		return null;
		
		
	}
	
}
