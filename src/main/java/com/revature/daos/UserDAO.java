package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO{

	public UserDAO() {
		super();
	}
	@Override
	public boolean insertUser(User u) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.save(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		try {
			ses.merge(u);
			tx.commit();
			return true;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}

	@Override
	public User selectById(int id) {
		Session ses=HibernateUtil.getSession();
		User u=ses.get(User.class,id);
		return u;
	}
	
	@Override
	public User selectByUsername(String username) {
		Session ses=HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User u WHERE u.username ='"+username+"'", User.class).list();
		User u= userList.get(0);
		return u;
	}

	@Override
	public User selectByCredentials(String username, String password) {
		Session ses=HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User WHERE username ='"+username+ "' AND password='"+password+"'", User.class).list();
		User u= userList.get(0);
		return u;
	}

	@Override
	public List<User> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<User> list = ses.createQuery("FROM User").list();
		
		return list;
	}
	@Override
	public User selectByUserRole(UserRole ur) {
		Session ses=HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User WHERE userRoleId="+ur, User.class).list();
		User u= userList.get(0);
		return u;
	}
	
	
}
