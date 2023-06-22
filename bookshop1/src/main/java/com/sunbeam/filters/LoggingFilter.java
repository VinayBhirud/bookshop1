package com.sunbeam.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
		System.out.println("LoggingFilter.init() called. ");
		
	}
	
	private void destory() {
		Filter.super.destroy();
		System.out.println("LoggingFilter.destroy() called.");

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String method = req.getMethod();
		System.out.printf("Before : %s - %s\r\n", method,uri);
		
		chain.doFilter(req, response);
		System.out.printf("After: %s - %s\r\n",method,uri);
		
	}
}
