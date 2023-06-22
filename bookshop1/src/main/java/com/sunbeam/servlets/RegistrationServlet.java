package com.sunbeam.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.Customer;
import com.sunbeam.daos.CustomerDao;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name");
			String address = req.getParameter("address");
			String email = req.getParameter("email");
			String mobile = req.getParameter("mobile");
			String birthDate = req.getParameter("birth");	// yyyy-MM-dd
			String password = req.getParameter("passwd");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = sdf.parse(birthDate);
			Customer c = new Customer(0, name, password, mobile, address, email, birth, 1, "ROLE_CUSTOMER");

			try(CustomerDao dao = new CustomerDao()) {
				int cnt = dao.save(c);
				if(cnt == 1) {
					resp.sendRedirect("index.html");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("register.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
