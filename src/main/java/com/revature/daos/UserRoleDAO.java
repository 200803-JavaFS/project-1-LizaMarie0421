package com.revature.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.UserRole;
import com.revature.utils.HibernateUtil;

public class UserRoleDAO implements IUserRoleDAO {

	@Override
	public UserRole selectById(int id) {
		Session ses=HibernateUtil.getSession();
		UserRole ur=ses.get(UserRole.class,id);
		return ur;
	}

//	@Override
//	public void insert(UserRole ur) {
//		Session ses = HibernateUtil.getSession();
//		Transaction tx= ses.beginTransaction();
//		ses.save(ur);
//		tx.commit();
//		
//	}

	@Override
	public boolean insertUserRole(UserRole ur) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.save(ur);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
