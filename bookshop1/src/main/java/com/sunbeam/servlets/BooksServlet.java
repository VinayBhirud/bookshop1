package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.Book;
import com.sunbeam.daos.BookDao;
import com.sunbeam.daos.Customer;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get all books from db
		List<Book> books = new ArrayList<>();
		try(BookDao dao = new BookDao()) {
			books = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// display all books
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cart</title>");
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

		// get cart from session		
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<th>Id</th>");
		out.println("<th>Name</th>");
		out.println("<th>Author</th>");
		out.println("<th>Subject</th>");
		out.println("<th>Price</th>");
		out.println("<th>Action</th>");
		out.println("</thead>");
		out.println("<tbody>");
		//render table rows and columns (as per books in list)
		for (Book b : books) {
			out.println("<tr>");
			out.printf("<td>%d</td>", b.getId());
			out.printf("<td>%s</td>", b.getName());
			out.printf("<td>%s</td>", b.getAuthor());
			out.printf("<td>%s</td>", b.getSubject());
			out.printf("<td>%.2f</td>", b.getPrice());
			out.println("<td>");
			out.printf("<a href='editbook?id=%d'><img src='images/edit.png' alt='Edit' width='20' height='20'/></a>\r\n", b.getId());
			out.printf("<a href='delbook?id=%d'><img src='images/delete.jpg' alt='Delete' width='20' height='20'/></a>\r\n", b.getId());
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<a href='newbook.html'>Add New Book</a>");
		out.println("<a href='logout'>Sign Out</a>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}	
}
