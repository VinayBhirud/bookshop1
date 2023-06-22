package com.sunbeam.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get cart from the session
		HttpSession session = req.getSession();
		List<Integer> cart = (List<Integer>) session.getAttribute("cart");
		
		String[] bookIds = req.getParameterValues("book");
		for (String bookId : bookIds) {
			int id = Integer.parseInt(bookId);
			System.out.println("Book selected: " + id);
			// add book id into cart
			cart.add(id);
		}
		
		// send num of more books added in cart to subjects servlet
		req.setAttribute("newbookcount", bookIds.length);
		
		// Go to subjects servlet
		ServletContext ctx = req.getServletContext();
		RequestDispatcher rd = ctx.getRequestDispatcher("/subjects");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
