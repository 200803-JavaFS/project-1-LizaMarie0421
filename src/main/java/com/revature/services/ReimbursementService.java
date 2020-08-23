package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	private static IReimbursementDAO rDao= new ReimbursementDAO();
	
	public List<Reimbursement> findAll(){
		log.info("Retrieving all reimbursements");
		return rDao.findAll();
	}
	
	public Reimbursement findById(int id) {
		log.info("Finding Reimbursement with id "+ id);
		return rDao.findById(id);
	}	
	
	public List<Reimbursement> findByUser(int userId) {
		log.info("Retrieving all reimbursements tied to user with user_id="+ userId);
		return rDao.findByUser(userId);
	}
	
	public List<Reimbursement> findByStatus(String status){
		return rDao.findByStatus(status);
	}
	
	public boolean addReimbursement(Reimbursement r) {
		log.info("Adding reimbursements: "+ r);
		return rDao.addReimbursement(r);
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		log.info("Updating reimbursement: "+ r);
		return rDao.updateReimbursement(r);
		
	}
	
}
