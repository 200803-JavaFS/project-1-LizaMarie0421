package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbController {

	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om= new ObjectMapper();
	private static UserService us= new UserService();
	
	public void getAllReimbursementsByAuthor(HttpServletRequest req, HttpServletResponse res, User u) throws IOException {
		List<Reimbursement> allReimbByUser = rs.findByUser(u);
		//print list basically
		res.getWriter().println(om.writeValueAsString(allReimbByUser));
		res.setStatus(200);
	
	
	}


}
