package com.sunbeam.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunbeam.daos.Customer;
import com.sunbeam.daos.CustomerDao;
import com.sunbeam.daos.DbUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("passwd");
		String mobile = req.getParameter("mobno");
		String DOB = req.getParameter("date");
		java.util.Date birthDate = null;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			birthDate = dateFormat.parse(DOB);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Customer c = new Customer();
		c.setName(name);
		c.setAddress(address);
		c.setEmail(email);
		c.setPassword(password);
		c.setMobile(mobile);
		c.setBirth(birthDate);
		c.setRole("ROLE_CUSTOMER");

		int cnt = -1;
		try (CustomerDao dao = new CustomerDao()) {
			cnt = dao.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cnt > 0) {
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("register.html");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
