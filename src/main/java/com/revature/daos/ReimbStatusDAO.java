package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.ReimbStatus;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbStatusDAO implements IReimbStatusDAO {

	@Override
	public ReimbStatus selectByStatus(String status) {
		Session ses=HibernateUtil.getSession();
		List<ReimbStatus> reimbStatusList = ses.createQuery("FROM ReimbStatus WHERE status ='"+status+"'", ReimbStatus.class).list();
		ReimbStatus rs= reimbStatusList.get(0);
		return rs;
	}

}
