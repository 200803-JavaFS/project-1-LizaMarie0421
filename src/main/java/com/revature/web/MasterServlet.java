package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.LoginController;
import com.revature.controller.ReimbController;
import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserService;


public class MasterServlet extends HttpServlet{

	private static UserController uc = new UserController();
	private static ReimbController rc = new ReimbController();
	private static	LoginController lc= new LoginController();
	private static UserService us = new UserService();


	
	public MasterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		res.setContentType("application/json");
		res.setStatus(404);
		RequestDispatcher rd = null;
		
		final String URI= req.getRequestURI().replace("/project1/", "");
		String[] portions = URI.split("/");
		

		System.out.println(Arrays.toString(portions));
		System.out.println("portions[0]:"+ portions[0]);
		//get first section of URI: Employee/FM/1
		try {
			System.out.println("inside try");
			switch(portions[0]) {
				case "login":
					lc.login(req,res);
					break;
				case "success": 
					if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
						User u = (User) req.getSession().getAttribute("user");
						
						System.out.println("**************** User username: "+  u.username);
						
						u = us.findByUsername(u.username);
						UserRole ur= u.getUserRole();	
						System.out.println(ur);
						if(req.getMethod().equals("GET")) {
							uc.setUserRole(req, res, u);
						}
					}
					break;
				case "reimbursements":
					System.out.println("in reimbursements");
//					System.out.println("user with user ID:" + u.getId());
					//rc.getAllReimbursementsByAuthor(req,res, u);
					break;
				
//				case "employeeSuccess":
//					if (req.getMethod().equals("POST")) {
//						System.out.println("inside employee case");
//						uc.display(req, res);
//					}
			}
					
		}catch (NumberFormatException e ) {
			e.printStackTrace();
			res.getWriter().print("The id you provided is not an integer");
			res.setStatus(400);
		}
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req,res);
	}
	
}
