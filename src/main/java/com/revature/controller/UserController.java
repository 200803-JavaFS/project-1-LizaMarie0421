package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class UserController {

	private static UserService us = new UserService();
	private static ObjectMapper om= new ObjectMapper();
	private static ReimbursementService rs = new ReimbursementService();

	public void setUserRole(HttpServletRequest req, HttpServletResponse res, User u) throws IOException {
		//Hibernate.initialize(u.getUserRole());
		System.out.println("in method to sent user role as object");		
		if(u==null) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = om.writeValueAsString(u);
			res.getWriter().println(json);
		}
		
	}

}
