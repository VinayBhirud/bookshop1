package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO: delete user cart and other details
		HttpSession session = req.getSession();
		session.invalidate();
		
		// destroy uname cookie from client
		Cookie c = new Cookie("uname", "");
		c.setMaxAge(-1);
		resp.addCookie(c);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Logout</title>");
		out.println("</head>");
		out.println("<body>");
		// get app title from web.xml (context-param) and display it
		ServletContext ctx = req.getServletContext();
		String title = ctx.getInitParameter("app.title");
		out.printf("<h3>%s</h3>\r\n", title);

		out.println("Thank you for using our online bookshop.<br/><br/>");
		out.println("<a href='index.html'>Login Again</a>");
		out.println("</body>");
		out.println("</html>");		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
