package com.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeservice.entity.Employee;
import com.employeeservice.pojo.MergedEmployeeDepartmentPojo;
import com.employeeservice.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	// http://localhost:5002/api/employees/
	@PostMapping("/")
	public Employee saveEmployee(@RequestBody Employee employee ) {
		log.info("inside saveEmployee of EmployeeController");
		return employeeService.saveEmployee(employee);
	}
	
	// http://localhost:5001/api/employees/1
	@GetMapping("/{eid}")
	public MergedEmployeeDepartmentPojo getEmployee(@PathVariable int eid) {
		return employeeService.getEmployee(eid);
	}
}

