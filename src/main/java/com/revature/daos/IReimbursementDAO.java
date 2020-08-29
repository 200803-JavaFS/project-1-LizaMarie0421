package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface IReimbursementDAO {

	//public void insert(Reimbursement r);
	public boolean insertReimb(Reimbursement r);
	//public void update(Reimbursement r);
	public boolean update(Reimbursement r);
	
	public Reimbursement selectById(int id);
	
	public List<Reimbursement> selectByAuthor(User u);
	
	public List<Reimbursement> selectByStatus(int statusId);
	
	public List<Reimbursement> selectAll();
}
