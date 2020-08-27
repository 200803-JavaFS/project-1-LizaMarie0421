package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class UserController {

	private static UserService us = new UserService();
	private static UserRoleService urs = new UserRoleService();
	private static ObjectMapper om= new ObjectMapper();

//	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		System.out.println("inside login user controller");
//		//"this guy" is from html, not implemented yet
//		String username= req.getParameter("username");
//		String password= req.getParameter("password");
//		
//		User u = us.findByUserPassword(username, password);
//		RequestDispatcher rd = null;
//		
//		PrintWriter out = res.getWriter();
//		
//		
//		if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
//			UserRole ur= urs.findById(u.getId());
//			if (ur.getRole().equals("Employee")) {
//				System.out.println("inside login as employee");
//				rd =  req.getRequestDispatcher("employeeSuccess.html");
//			}
//			else if (ur.getRole().equals("Manager")) {
//				System.out.println("inside login as manager");
//				rd =  req.getRequestDispatcher("managerSuccess");
//			}
//			
//			//now we forward
//			rd.forward(req, res);
//		} else {
//			rd =  req.getRequestDispatcher("index.html");
//			rd.include(req, res);
//			out.print("<span style= 'color:red; text-align:center'> Invalid Username/Password</span>");
//		}
//	}
	
	public void display(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("in display do something");
		
		RequestDispatcher rd = null;
		
		rd =  req.getRequestDispatcher("employeeSuccess.html");
	}
	
}
