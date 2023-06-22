package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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

@WebServlet("/editbook")
public class EditBookServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Book</title>");
		out.println("</head>");
		out.println("body");
		
		ServletContext ctx = req.getServletContext();
		String title = ctx.getInitParameter("app.title");
		out.printf("<h3>%s</h3>\r\n",title);
		
		String uname = "unknown";
		Cookie[] arr = req.getCookies();
		if(arr != null) {
			for(Cookie c:arr) {
				if(c.getName().equals("uname")) {
					uname = c.getValue();
					break;
				}
			}
		}
		out.printf("hello, %s <hr/>\r\n",uname);
		
		// get cust info from session and display it
				HttpSession session = req.getSession();
				Customer cust = (Customer) session.getAttribute("cust");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				out.printf("Email: %s, Mobile: %s, Address: %s, Birth: %s<hr/>\r\n",
					cust.getEmail(), cust.getMobile(), cust.getAddress(), sdf.format(cust.getBirth()));
		
				int id = 0;
				String bookId = req.getParameter("id");
				if(bookId != null)
					id = Integer.parseInt(bookId);
				
				Book b = new Book();
				try(BookDao dao = new BookDao()) {
					b = dao.findById(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				out.println("<form method='post' action='editbook'>");
				out.printf("<input type='hidden' name='id' value='%s'/>\r\n", b.getId());
				out.printf("Title: <input type='text' name='name' value='%s'/><br/><br/>\r\n", b.getName());
				out.printf("Author: <input type='text' name='author' value='%s'/><br/><br/>\r\n", b.getAuthor());
				out.printf("Subject: <input type='text' name='subject' value='%s'/><br/><br/>\r\n", b.getSubject());
				out.printf("Price: <input type='text' name='price' value='%s'/><br/><br/>\r\n", b.getPrice());
				out.println("<input type='submit' value='Update'/>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");		
			}
			
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				String bookId = req.getParameter("id");
				String name = req.getParameter("name");
				String author = req.getParameter("author");
				String subject = req.getParameter("subject");
				String price = req.getParameter("price");
				try(BookDao dao = new BookDao()) {
					Book b = new Book(Integer.parseInt(bookId), name, author, subject, Double.parseDouble(price));
					dao.update(b);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				resp.sendRedirect("books");
			}
		}
