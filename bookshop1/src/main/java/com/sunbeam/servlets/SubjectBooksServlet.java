package com.sunbeam.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.Book;
import com.sunbeam.daos.BookDao;
@WebServlet("/subbooks")
public class SubjectBooksServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException , IOException{
		String subject = req.getParameter("subject");
		List<Book> books = new ArrayList<>();
		try(BookDao dao = new BookDao()){
			books = dao.findBySubject(subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subject Books</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='addcart'>"); 
		for(Book b:books)
			out.printf("<input type='checkbox' name='book' value='%d'/> %s (%s) (%s)<br/>\r\n",
											b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		out.println("<input type='submit' value='Add Cart'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp ) throws ServletException, IOException{
		doGet(req,resp);
	}
}
