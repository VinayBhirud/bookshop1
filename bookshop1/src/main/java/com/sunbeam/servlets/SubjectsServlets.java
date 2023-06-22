package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.BookDao;
import com.sunbeam.daos.Customer;

@WebServlet("/subjects")
public class SubjectsServlets extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Set<String> subjects = new LinkedHashSet<>();
		try(BookDao dao = new BookDao()) {
			subjects = dao.findAllSubjects();
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subjects</title>");
		out.println("</head>");
		out.println("<body>");

		// get app title from web.xml (context-param) and display it
		ServletContext ctx = req.getServletContext();
		String title = ctx.getInitParameter("app.title");
		out.printf("<h3>%s</h3>\r\n", title);

		// get cookie from client and get value of uname cookie and use to greet user
		String uname = "Unknown";
		Cookie[] arr = req.getCookies();
		if(arr != null) {
			for(Cookie c:arr) {
				if(c.getName().equals("uname")) {
					uname = c.getValue();
					break;
				}
			}
		}
		out.printf("Hello, %s <hr/>\r\n", uname);
		
		// get cust info from session and display it
		HttpSession session = req.getSession();
		Customer cust = (Customer) session.getAttribute("cust");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		out.printf("Email: %s, Mobile: %s, Address: %s, Birth: %s<hr/>\r\n",
			cust.getEmail(), cust.getMobile(), cust.getAddress(), sdf.format(cust.getBirth()));
		
		out.println("<form method='post' action='subbooks'>");
		for(String subject:subjects)
			out.printf("<input type='radio' name='subject' value='%s'/> %s <br/>\r\n", 
																		subject, subject);
		out.println("<input type='submit' value='Show Books'/>");
		out.println("</form>");
		out.println("<a href='showcart'>Show Cart</a>");
		
		// get new books count added into cart from addcart servlet
		Integer newBookCount = (Integer) req.getAttribute("newbookcount");
		if(newBookCount != null)
			out.printf("<br/> %d more books added into cart.", newBookCount);
		
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
