package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDAO {

	public List<Reimbursement> findAll();
	
	public Reimbursement findById(int id);
	
	public List<Reimbursement> findByUser(int userId);
	
	public List<Reimbursement> findByStatus(String status);
	
	public boolean addReimbursement(Reimbursement r);
	
	public boolean updateReimbursement(Reimbursement r);
	
}
