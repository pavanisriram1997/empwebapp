package com.capgemini.empwebapp.service;

import java.util.List;

import com.capgemini.empwebapp.dto.EmployeeBeans;

public interface EmployeeService {
	
	public EmployeeBeans getEmployeeDetailsById(int id);
	public boolean  addEmployeeDetails(EmployeeBeans beans);
	public boolean updateEmployee(EmployeeBeans bean);
	public boolean deleteEmployeeById(int id);
	public List<EmployeeBeans> getAllEmployeeDetails();
	public EmployeeBeans login(int empId,String password);
}
