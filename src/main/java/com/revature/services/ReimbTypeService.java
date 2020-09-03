package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbTypeDAO;
import com.revature.daos.ReimbTypeDAO;
import com.revature.models.ReimbType;

public class ReimbTypeService {

	private static final Logger log = LogManager.getLogger(UserService.class);
	private static IReimbTypeDAO rtDAO= new ReimbTypeDAO();

	public ReimbType findByType(String type) {
		log.info("Finding ReimbStatus with status: "+ type);
		return rtDAO.selectByType(type);
	}
}
