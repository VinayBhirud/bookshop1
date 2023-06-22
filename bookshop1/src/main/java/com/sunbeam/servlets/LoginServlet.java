package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.Customer;
import com.sunbeam.daos.CustomerDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("passwd");
		try(CustomerDao dao = new CustomerDao()) {
			Customer dbCust = dao.findByEmail(email);
			if(dbCust != null && dbCust.getPassword().equals(password)) {
				// store user name in a cookie and send to client
				Cookie c = new Cookie("uname", dbCust.getName());
				c.setMaxAge(3600);
				resp.addCookie(c);
				
				// store customer into session
				HttpSession session = req.getSession();
				session.setAttribute("cust", dbCust);
				
				// store an empty shopping cart into the session
				List<Integer> cart = new ArrayList<Integer>();
				session.setAttribute("cart", cart);
				
				// go to subjects or books servlet
				if(dbCust.getRole().equals("ROLE_CUSTOMER"))
					resp.sendRedirect("subjects");
				else if(dbCust.getRole().equals("ROLE_ADMIN"))
					resp.sendRedirect("books");
				else
					resp.sendRedirect("index.html");
			}
			else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Failed</title>");
				out.println("</head>");
				out.println("<body>");
				out.printf("Sorry, %s<br/>\r\n", email);
				out.println("Invalid email or password. <br/><br/>");
				out.println("<a href='index.html'>Login Again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}





