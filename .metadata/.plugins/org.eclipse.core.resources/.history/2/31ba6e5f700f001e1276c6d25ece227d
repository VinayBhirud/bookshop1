package com.sunbeam.daos;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BookDao implements Closeable {
	private Connection con;
	public BookDao() throws Exception {
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
	public int save(Book b) throws Exception {
		int cnt = -1;
		String sql = "INSERT INTO books(name,author,subject,price) VALUES(?,?,?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, b.getName());
			stmt.setString(2, b.getAuthor());
			stmt.setString(3, b.getSubject());
			stmt.setDouble(4, b.getPrice());
			cnt = stmt.executeUpdate();
		} //stmt.close();
		return cnt;
	}
	public int update(Book b) throws Exception {
		int cnt = -1;
		String sql = "UPDATE books SET name=?, author=?, subject=?, price=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, b.getName());
			stmt.setString(2, b.getAuthor());
			stmt.setString(3, b.getSubject());
			stmt.setDouble(4, b.getPrice());
			stmt.setInt(5, b.getId());
			cnt = stmt.executeUpdate();
		} //stmt.close();
		return cnt;
	}
	public int deleteById(int bookId) throws Exception {
		int cnt = -1;
		String sql = "DELETE FROM books WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
			cnt = stmt.executeUpdate();
		} //stmt.close();
		return cnt;
	}
	public List<Book> findAll() throws Exception {
		List<Book> bookList = new ArrayList<Book>();
		String sql = "SELECT * FROM books";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					bookList.add(b);
				}
			} //rs.close();
		} //stmt.close();
		return bookList;
	}
	public List<Book> findBySubject(String  subject) throws Exception {
		List<Book> bookList = new ArrayList<Book>();
		String sql = "SELECT * FROM books WHERE subject=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, subject);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String author = rs.getString("author");
					subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					bookList.add(b);
				}
			} //rs.close();
		} //stmt.close();
		return bookList;
	}
	public List<Book> findByAuthor(String author) throws Exception {
		List<Book> bookList = new ArrayList<Book>();
		String sql = "SELECT * FROM books WHERE author=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, author);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					author = rs.getString("author");
					String subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					bookList.add(b);
				}
			} //rs.close();
		} //stmt.close();
		return bookList;
	}
	public Book findById(int bookId) throws Exception {
		String sql = "SELECT * FROM books WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, bookId);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String subject = rs.getString("subject");
					double price = rs.getDouble("price");
					Book b = new Book(id, name, author, subject, price);
					return b;
				}
			} //rs.close();
		} //stmt.close();
		return null;		
	}
	public Set<String> findAllSubjects() throws Exception {
		Set<String> list = new LinkedHashSet<String>();
		String sql = "SELECT DISTINCT subject FROM books";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					String subject = rs.getString("subject");
					list.add(subject);
				}
			} //rs.close();
		} //stmt.close();
		return list;
	}
}







