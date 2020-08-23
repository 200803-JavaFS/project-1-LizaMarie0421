package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.ReimbController;
import com.revature.controller.UserController;


public class MasterServlet extends HttpServlet{

	private static UserController uc = new UserController();
	private static ReimbController rc = new ReimbController();

	
	public MasterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		res.setContentType("application/json");
		res.setStatus(404);
		
		final String URI= req.getRequestURI().replace("/project1/", "");
		String[] portions = URI.split("/");
		

		System.out.println(Arrays.toString(portions));
		System.out.println("portions[0]:"+ portions[0]);
		//get first section of URI: Employee/FM/1
		try {
			System.out.println("inside try");
			switch(portions[0]) {
				case "login":
					if (req.getMethod().equals("POST")) {
						System.out.println("inside login");
						uc.login(req, res);
					}
			
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
