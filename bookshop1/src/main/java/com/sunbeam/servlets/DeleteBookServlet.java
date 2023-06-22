package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.BookDao;

@WebServlet("/delbook")
public class DeleteBookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookId = req.getParameter("id");
		if(bookId != null) {
			int id = Integer.parseInt(bookId);
			try(BookDao dao = new BookDao()) {
				dao.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resp.sendRedirect("books");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
