package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.utils.HibernateUtil;

public class ReimbTypeDAO implements IReimbTypeDAO {

	@Override
	public ReimbType selectByType(String type) {
		Session ses=HibernateUtil.getSession();
		List<ReimbType> reimbTypeList = ses.createQuery("FROM ReimbType WHERE type ='"+type+"'", ReimbType.class).list();
		ReimbType rt= reimbTypeList.get(0);
		return rt;
	}

}
