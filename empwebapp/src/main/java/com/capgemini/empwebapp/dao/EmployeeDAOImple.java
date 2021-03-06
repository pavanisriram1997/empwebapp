package com.capgemini.empwebapp.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.empwebapp.dto.EmployeeBeans;

public class EmployeeDAOImple implements EmployeeDAO {

	EmployeeBeans bean = new EmployeeBeans();
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	@Override
	public EmployeeBeans getEmployeeDetailsById(int id) {
		
		try { 
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
				
			stmt = conn.prepareStatement("select * from employee where id = ?");
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();

			if(rs.next()) {
			bean.setId(rs.getInt("id"));
			bean.setName(rs.getString("name"));
			bean.setAge(rs.getInt("age"));
			bean.setSalary(rs.getInt("salary"));
			bean.setDesignation(rs.getString("designation"));
			bean.setPassword(rs.getString("password"));
			
			} else {
				System.out.println("Employee details not found");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
		}
		return bean;
	}
	

	@Override
	public boolean addEmployeeDetails(EmployeeBeans beans) {
		
		try { 
			//load the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//get db connection via Driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			//issue the sql query via connection
			stmt = conn.prepareStatement("insert into employee(id,name,age,salary,designation,password) values (?,?,?,?,?,?)");
			
			//set parameters
			stmt.setInt(1,bean.getId());
			stmt.setString(2,bean.getName());
			stmt.setInt(3,bean.getAge());
			stmt.setInt(4,bean.getSalary());
			stmt.setString(5,bean.getDesignation());
			stmt.setString(6, bean.getPassword());
			
			//process the result "returned by sql queries"
			int rowsaffected= stmt.executeUpdate();
			if(rowsaffected != 0) {
				return true;
			} else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return false;
	}

	@Override
	public boolean updateEmployee(EmployeeBeans bean) {
		
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//get db connection via Driver
 			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			//issue the sql query via connection
			stmt = conn.prepareStatement("update employee set name = ? where id = ?");
			
			//set parameters
			stmt.setString(1, bean.getName());
			stmt.setInt(2,bean.getId());
			
			//process the result "returned by sql queries"				
			int rowsaffected = stmt.executeUpdate();
							
			if(rowsaffected != 0) {
				return true;
			} else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		
		try { 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			stmt = conn.prepareStatement("delete from employee where id = ?");
			stmt.setInt(1,id);
			int status= stmt.executeUpdate();
			
			if(status != 0) {
				return true;
			
			} else {
				return false;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
		}
		return false;
	}

	@Override
	public List<EmployeeBeans> getAllEmployeeDetails() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try { 
			//load the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//get db connection via Driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?useSSL=false","root","root");
			
			//issue the sql query via connection
			stmt = conn.createStatement();
			
			//process the result "returned by sql queries"
			rs = stmt.executeQuery("select * from employee");
			List<EmployeeBeans> list = new ArrayList<EmployeeBeans>();
			while(rs.next()) {
				EmployeeBeans beans = new EmployeeBeans();
				beans.setId(rs.getInt("id"));
				beans.setName(rs.getString("name"));
				beans.setAge(rs.getInt("age"));
				beans.setSalary(rs.getInt("salary"));
				beans.setDesignation(rs.getString("designation"));
				beans.setPassword(rs.getString("password"));
				list.add(beans);
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//close connection
		finally {
			try {
				if(conn != null) {
				conn.close();
				} 
				if(stmt != null) {
				stmt.close();
				}
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return null;
	}


	@Override
	public EmployeeBeans login(int empId, String password) {
		
		EmployeeBeans bean = getEmployeeDetailsById(empId);
		if(!(bean !=null && bean.getPassword().equals(password))) {
			bean = null;
		}
		return null;
	}

	
	
}
