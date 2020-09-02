package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbController {

	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om= new ObjectMapper();
	private static UserService us= new UserService();
	
	public void getAllReimbursementsByAuthor(HttpServletResponse res, int id) throws IOException {
		User u=us.findById(id);
		List<Reimbursement> allReimb= rs.findByUser(u);

		if(allReimb.isEmpty()) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimb);
			res.getWriter().println(json);
			
		}
	
	}

	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		List<Reimbursement> allReimb=  rs.findAll();
		res.setStatus(200);
		String json=om.writeValueAsString(allReimb);
		res.getWriter().println(json);
		
	}

	public void updateStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader= req.getReader();
		StringBuilder s=new StringBuilder();
		String line = reader.readLine();
		while(line!=null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		ReimbursementDTO rdto= om.readValue(body, ReimbursementDTO.class);
		System.out.println("body that is taken in from java: "+ rdto);
		
		
		int reimbId= rdto.getId();
		Reimbursement r = rs.findById(reimbId);
		
		String status= rdto.getStatus();
		System.out.println("new status:" +status);
		
		ReimbStatus rStatus = null;
		if (status.equals("Approved")) {
			rStatus= new ReimbStatus(2,"Approved");
		}else if (status.equals("Denied")) {
			rStatus= new ReimbStatus(3,"Denied");
		}
		
		int resolverId= rdto.getAuthorId();
		System.out.println("resolver id: "+ resolverId);
		
		
		r.setStatus(rStatus);
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		User resolver= us.findById(resolverId);
		System.out.println("resolver: " + resolver);
		r.setResolver(resolver);
		System.out.println(r);
		
		if (rs.updateReimbursement(r)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement was updated");
		}else {
			res.setStatus(403);
		}
		
	}

	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		String line= reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();								
		}
		String body = new String(s);
		
		System.out.println(body);
		
		
		ReimbursementDTO rdto= om.readValue(body, ReimbursementDTO.class);
		System.out.println("body that is taken in from java: "+ rdto);
		
		double amount= rdto.getAmount();
		Timestamp ts= new Timestamp(System.currentTimeMillis());
		String description = rdto.getDescription();
		User author = us.findById(rdto.getAuthorId());
		ReimbStatus rnewStatus= new ReimbStatus(1, "Pending");
		
		String type = rdto.getType();
		System.out.println(type);
		
		ReimbType rt=null;
		if (type.equals("Lodging")) {
			rt=new ReimbType(1, "Lodging");
		}else if (type.equals("Travel")) {
			rt=new ReimbType(2, "Travel");
		}else if (type.equals("Food")) {
			rt=new ReimbType(3, "Food");
		}else if (type.equals("Other")) {
			rt=new ReimbType(4, "Other");
		}
		//create new reimbursement with constructor
		Reimbursement addedReimb=new Reimbursement(amount, ts,null,description, author, null, rnewStatus, rt);
		System.out.println(addedReimb);

		if(rs.addReimbursement(addedReimb)) {
			//add to database
			res.setStatus(200);
			res.getWriter().println("Reimbursement was created");
		}else {
			res.setStatus(403);
		}
		
	}


}
