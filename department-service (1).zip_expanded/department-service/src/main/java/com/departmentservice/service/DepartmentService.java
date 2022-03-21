package com.departmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.departmentservice.dao.DepartmentRepository;
import com.departmentservice.entity.Department;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	public Department saveDepartment(Department department) {
		log.info("inside saveDepsrtment of DepartmentService");
		return departmentRepository.save(department);
	}
	
	public Department getDepartment(int deptId) {
		log.info("inside getDepartment of DepartmentService");
		return departmentRepository.findById(deptId).get();
	}
	
}
