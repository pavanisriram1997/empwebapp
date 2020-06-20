package com.capgemini.empwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.empwebapp.dao.EmployeeDAO;
import com.capgemini.empwebapp.dao.EmployeeDAOImple;
import com.capgemini.empwebapp.dto.EmployeeBeans;

@SuppressWarnings("serial")
@WebServlet("/SearchServlet")
public class SearchEmployeeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EmployeeDAO employee = new EmployeeDAOImple();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<h1>Employee Details</h1>");

		String sid = req.getParameter("id");

		int id = Integer.parseInt(sid);

		EmployeeBeans bean = employee.getEmployeeDetailsById(id);

		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>Name</th><th>Age</th><th>Designation</th><th>Salary</th><th>Password</th></tr>");
		if (bean != null) {
			out.print("<tr><td>" + bean.getId() + "</td><td>" + bean.getName() + "</td><td>" + bean.getAge() + "</td>"
					+ " <td>" + bean.getDesignation() + "</td><td>" + bean.getSalary() + "</td>" + "<td>"
					+ bean.getPassword() + "</td>");
		}
		out.print("</table>");

		RequestDispatcher res = req.getRequestDispatcher("/form.html");
		res.include(req, resp);

		out.close();
	}
}
