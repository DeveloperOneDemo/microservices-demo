package com.departmentservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.departmentservice.entity.Department;

//@Component
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
