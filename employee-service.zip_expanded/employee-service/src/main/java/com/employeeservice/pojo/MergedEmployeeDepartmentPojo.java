package com.employeeservice.pojo;

import com.employeeservice.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MergedEmployeeDepartmentPojo {
	private Employee employee;
	private DepartmentPojo department;
}
