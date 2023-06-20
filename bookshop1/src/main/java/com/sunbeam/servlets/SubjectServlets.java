package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.BookDao;

@WebServlet("/subjects")
public class SubjectServlets extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Set<String> subjects = new LinkedHashSet<>();
		try(BookDao dao = new BookDao()){
			subjects = dao.findAllSubjects();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subjects</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='subbooks'>");
		for(String subject:subjects)
				out.printf("<input type='radio' name='subject' value='%s'/> %s <br/>\r\n",
																			subject,subject);
		out.println("<input type='submit' value='Show Books'/>");
		out.println("</form>");
		out.println("<a href='showcart'>Show Cart</a>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
