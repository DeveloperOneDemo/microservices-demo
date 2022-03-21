package com.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.departmentservice.entity.Department;
import com.departmentservice.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController // a combination of @Controller and @ResponseBody
@RequestMapping("/api/departments")
@Slf4j
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	// http://localhost:5001/api/departments/
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		log.info("inside saveDepartment of DepartmentController");
		return departmentService.saveDepartment(department);
	}
	
	// http://localhost:5001/api/departments/1
	@GetMapping("/{did}")
	public Department getDepartment(@PathVariable int did) {
		log.info("inside getDepartment of DepartmentController");
		return departmentService.getDepartment(did);
	}

}
