package com.sunbeam.daos;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Closeable {
	private Connection con;
	public CustomerDao() throws Exception {
		// create connection
		con = DbUtil.getConnection();
	}
	@Override
	public void close() {
		try {
			// close connection
			if (con != null)
				con.close();
		} catch (Exception e) {
		}
	}
	public Customer findById(int custId) throws Exception {
		String sql = "SELECT c.id, c.name, c.password, c.mobile, c.address, c.email, c.birth, c.enabled, c.role FROM customers c WHERE c.id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, custId);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					String address = rs.getString("address");
					String email = rs.getString("email");
					Date birth = rs.getDate("birth");
					int enabled = rs.getInt("enabled");
					String role = rs.getString("role");
					java.util.Date birthDate = new java.util.Date(birth.getTime());
					Customer c = new Customer(id, name, password, mobile, address, email, birthDate, enabled, role);
					return c;
				}
			} //rs.close();
		} //stmt.close();
		return null;		
	}
	public Customer findByEmail(String email) throws Exception {
		String sql = "SELECT c.id, c.name, c.password, c.mobile, c.address, c.email, c.birth, c.enabled, c.role FROM customers c WHERE c.email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					String address = rs.getString("address");
					email = rs.getString("email");
					Date birth = rs.getDate("birth");
					int enabled = rs.getInt("enabled");
					String role = rs.getString("role");
					java.util.Date birthDate = new java.util.Date(birth.getTime());
					Customer c = new Customer(id, name, password, mobile, address, email, birthDate, enabled, role);
					return c;
				}
			} //rs.close();
		} //stmt.close();
		return null;		
	}
	public int save(Customer cust) throws Exception {
		int cnt = -1;
		try {
			String custSql = "INSERT INTO customers(name,password,mobile,address,email,birth,enabled,role) VALUES(?,?,?,?,?,?,?,?)";
			try(PreparedStatement stmt=con.prepareStatement(custSql)) {
				stmt.setString(1, cust.getName());
				stmt.setString(2, cust.getPassword());
				stmt.setString(3, cust.getMobile());
				stmt.setString(4, cust.getAddress());
				stmt.setString(5, cust.getEmail());
				java.sql.Date birth = new Date(cust.getBirth().getTime());
				stmt.setDate(6, birth);
				stmt.setInt(7, cust.getEnabled());
				stmt.setString(8, cust.getRole());
				cnt = stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // re-throw exception
		}
		return cnt;
	}
	public int update(Customer cust) throws Exception {
		int cnt = -1;
		String custSql = "UPDATE customers SET name=?, password=?, mobile=?, address=?, email=?, birth=?, enabled=? WHERE id=?";
		try(PreparedStatement stmt=con.prepareStatement(custSql)) {
			stmt.setString(1, cust.getName());
			stmt.setString(2, cust.getPassword());
			stmt.setString(3, cust.getMobile());
			stmt.setString(4, cust.getAddress());
			stmt.setString(5, cust.getEmail());
			java.sql.Date birth = new Date(cust.getBirth().getTime());
			stmt.setDate(6, birth);
			stmt.setInt(7, cust.getEnabled());
			stmt.setInt(8, cust.getId());
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}
	public int deleteById (int custId) throws Exception {
		int cnt = -1;
		String custSql = "DELETE FROM customers WHERE id=?";
		try(PreparedStatement stmt=con.prepareStatement(custSql)) {
			stmt.setInt(1, custId);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}
}
