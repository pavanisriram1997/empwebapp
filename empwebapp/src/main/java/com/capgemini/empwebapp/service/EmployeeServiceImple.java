package com.capgemini.empwebapp.service;

import java.util.List;

import com.capgemini.empwebapp.dao.EmployeeDAO;
import com.capgemini.empwebapp.dao.EmployeeDAOImple;
import com.capgemini.empwebapp.dto.EmployeeBeans;

public class EmployeeServiceImple implements EmployeeService {

	EmployeeDAO dao=new EmployeeDAOImple();
	
	@Override
	public EmployeeBeans getEmployeeDetailsById(int id) {
		// TODO Auto-generated method stub
		if(id!= 0) {
			return dao.getEmployeeDetailsById(id);
		}
		return null;
	}

	@Override
	public boolean addEmployeeDetails(EmployeeBeans beans) {
		// TODO Auto-generated method stub
		if(beans!=null) {
			return dao.addEmployeeDetails(beans);
		}
		return false;
	}

	@Override
	public boolean updateEmployee(EmployeeBeans bean) {
		// TODO Auto-generated method stub
		if(bean!=null) {
			return dao.updateEmployee(bean);
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		if(id!=0) {
			return dao.deleteEmployeeById(id);
		}
		return false;
	}

	@Override
	public List<EmployeeBeans> getAllEmployeeDetails() {
		// TODO Auto-generated method stub
		return dao.getAllEmployeeDetails();
	}

	@Override
	public EmployeeBeans login(int empId, String password) {
		
		if(empId < 0 || password == null || password.trim().isEmpty()) {
			return null;
		}
		return dao.login(empId,password);
	}

	
}
