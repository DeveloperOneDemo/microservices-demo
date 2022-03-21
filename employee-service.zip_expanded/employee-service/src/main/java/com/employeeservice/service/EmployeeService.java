package com.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employeeservice.dao.EmployeeRepository;
import com.employeeservice.entity.Employee;
import com.employeeservice.pojo.DepartmentPojo;
import com.employeeservice.pojo.MergedEmployeeDepartmentPojo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	
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
	
	// i want to work with RestTemplate here because i have to consume an endpoint of department-service
	public MergedEmployeeDepartmentPojo getEmployee(int eid) {
		log.info("inside getEmployee of EmployeeService");
		Employee employee = employeeRepository.findById(eid).get();
		// now that we have the employee info with the department id, we can consume the get a department endpoint from department-service
		// for this we need RestTemplate
		DepartmentPojo department = restTemplate.getForObject("http://localhost:5001/api/departments/"+employee.getDeptId(), DepartmentPojo.class);
		
		MergedEmployeeDepartmentPojo medp = new MergedEmployeeDepartmentPojo(employee, department);
		return medp;
	}
}
