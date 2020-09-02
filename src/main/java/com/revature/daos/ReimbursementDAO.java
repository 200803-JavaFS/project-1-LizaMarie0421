package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO implements IReimbursementDAO{

//	@Override
//	public void insert(Reimbursement r) {
//		Session ses = HibernateUtil.getSession();
//		Transaction tx= ses.beginTransaction();
//		ses.save(r);
//		tx.commit();
//		
//	}

	@Override
	public boolean insertReimb(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		try {
			ses.save(r);
			tx.commit();
			return true;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}
	@Override
	public boolean update(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		try {
			ses.merge(r);
			tx.commit();
			return true;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}

	@Override
	public Reimbursement selectById(int id) {
		Session ses=HibernateUtil.getSession();
		Reimbursement r=ses.get(Reimbursement.class,id);
		return r;
	}

	@Override
	public List<Reimbursement> selectByAuthor(User u) {
		Session ses=HibernateUtil.getSession();
		List<Reimbursement> reimbList = ses.createQuery("FROM Reimbursement WHERE author ='"+u.getId()+"'", Reimbursement.class).list();
		return reimbList;
	}

	@Override
	public List<Reimbursement> selectByStatus(int statusId) {
		Session ses=HibernateUtil.getSession();
		List<Reimbursement> reimbList = ses.createQuery("FROM Reimbursement WHERE status="+statusId, Reimbursement.class).list();
		return reimbList;
	}
//check if we need create builder with each list
	@Override
	public List<Reimbursement> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement").list();
		
		return list;
	}

	
	
	
}
