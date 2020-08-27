package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserRoleService;
import com.revature.services.UserService;

public class LoginController {
	
	private static UserService us= new UserService();
	private static ObjectMapper om= new ObjectMapper();
	private static UserRoleService urs = new UserRoleService();

	
	RequestDispatcher rd= null;

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		User u= new User();
		u.username = req.getParameter("username");
		u.password = req.getParameter("password");
		//u = us.findByUserPassword(u.username,u.password);
		
		if(req.getMethod().equals("GET")) {
						
			if(us.login(u)) {
				HttpSession ses = req.getSession();
				
				ses.setAttribute("user", u);
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Login Successful");
			} else {
				HttpSession ses = req.getSession(false);
				
				if (ses!=null) {
					
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login failed");
			}
		} else if (req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
				System.out.println("sb appended:" +sb);
			}
			
			String body = new String(sb);
			System.out.println(body);
			//User u= om.readValue(body, User.class);
			
			
			if(us.login(u)) {
				HttpSession ses = req.getSession();
				System.out.println("in if");
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Login Successful");
				System.out.println("success");
				//if not does not work
				res.setContentType("text/html");
				
				User newU= us.findByUserPassword(u.username, u.password);
				//if statement checking for userRoleType

				UserRole ur= urs.findById(newU.getId());
				System.out.println(ur);
				if (ur.getRole().equals("Employee")) {
					System.out.println("inside login as employee");
					req.getRequestDispatcher("employeeSuccess.html").forward(req,res);
				}
				else if (ur.getRole().equals("Manager")) {
					System.out.println("inside login as manager");
					req.getRequestDispatcher("managerSuccess.html").forward(req,res);
				}
                //req.getRequestDispatcher("employeeSuccess.html").forward(req,res);

			} else {
				HttpSession ses = req.getSession(false);
				if (ses!=null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login failed");
			}
		}
	}
	//another method for logout
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession ses = req.getSession(false);
		
		if(ses!=null) {
			User u= (User) ses.getAttribute("user");
			ses.invalidate();
			res.setStatus(200);
			res.getWriter().println(u.username + " has logged out successfully");
		} else {
			res.setStatus(400);
			res.getWriter().println("you must be logged in to logout!");

		}
		
	}
	
	
}
