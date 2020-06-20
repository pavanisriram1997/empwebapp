package com.capgemini.empwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Date currentDate = new Date();
		PrintWriter out= resp.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<h1> Current System Date and Time is-"+currentDate+"</h1>");
		out.print("</body>");
		out.print("</html>");

	}// end of doGet

}// end of class
