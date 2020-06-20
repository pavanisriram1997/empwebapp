package com.capgemini.empwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.empwebapp.dto.EmployeeBeans;
import com.capgemini.empwebapp.service.EmployeeService;
import com.capgemini.empwebapp.service.EmployeeServiceImple;

@WebServlet("/EditEmployee")
public class UpdateEmployeeServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EmployeeBeans bean = new EmployeeBeans();
		EmployeeService service = new EmployeeServiceImple();

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		bean.setId(Integer.parseInt(id));
		bean.setName(name);

		boolean check = service.updateEmployee(bean);
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");

		if (check == true) {
			out.print("<h2 style = 'color:green'>Updated successfully.....</h2>");
		} else {
			out.print("<h2 style = 'color:red'>Updation not done!!!!!</h2>");
		}
		
		RequestDispatcher res = req.getRequestDispatcher("/form.html");
		res.include(req, resp);  
		
		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
