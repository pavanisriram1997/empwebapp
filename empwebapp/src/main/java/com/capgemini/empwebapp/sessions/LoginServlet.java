package com.capgemini.empwebapp.sessions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.empwebapp.dto.EmployeeBeans;
import com.capgemini.empwebapp.service.EmployeeService;
import com.capgemini.empwebapp.service.EmployeeServiceImple;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

private EmployeeService service= new EmployeeServiceImple();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String empId= req.getParameter("id");
		int id=Integer.parseInt(empId);
		
		String empPass= req.getParameter("password");
		
		EmployeeBeans employeeBeans = service.login(id,empPass);
					

			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.print("<html>");
			out.print("<body>");
			
			if(employeeBeans != null) {
				
				HttpSession httpSession = req.getSession(true);
				httpSession.setAttribute("loggedInEmployeeInfo", employeeBeans);
				out.print("<h2 style='color :navy' > WELCOME !!"+ employeeBeans.getName()+"</h2>");
				RequestDispatcher dispatcher= req.getRequestDispatcher("/form.html");
				dispatcher.include(req,resp);
				
			}else {
				out.print("<h1 style='color :red' > Invaild Credentials !!</h1>");
				RequestDispatcher dispatcher= req.getRequestDispatcher("/login.html");
				dispatcher.include(req,resp);
			}
			
			out.print("<html>");
			out.print("<body>");
			
			
		}
}
