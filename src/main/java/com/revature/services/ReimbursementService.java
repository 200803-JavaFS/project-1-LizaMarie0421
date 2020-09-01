package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	private static IReimbursementDAO rDao= new ReimbursementDAO();
	
	public List<Reimbursement> findAll(){
		log.info("Retrieving all reimbursements");
		return rDao.selectAll();
	}
	
	public Reimbursement findById(int id) {
		log.info("Finding Reimbursement with id "+ id);
		return rDao.selectById(id);
	}	
	
	public List<Reimbursement> findByUser(User u) {
		log.info("Retrieving all reimbursements tied to user with user_id="+ u.getId());
		return rDao.selectByAuthor(u);
	}
	
	public List<Reimbursement> findByStatus(int statusId){
		return rDao.selectByStatus(statusId);
	}
	
	public boolean addReimbursement(Reimbursement r) {
		log.info("Adding reimbursements: "+ r);
		return rDao.insertReimb(r);
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		log.info("Updating reimbursement: "+ r);
		return rDao.update(r);
		
	}
	
	
	
}
