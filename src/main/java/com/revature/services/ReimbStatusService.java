package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbStatusDAO;
import com.revature.daos.ReimbStatusDAO;
import com.revature.models.ReimbStatus;

public class ReimbStatusService {

	private static final Logger log = LogManager.getLogger(UserService.class);
	private static IReimbStatusDAO rsDAO= new ReimbStatusDAO();

	public ReimbStatus findByStatus(String status) {
		log.info("Finding ReimbStatus with status: "+ status);
		return rsDAO.selectByStatus(status);
	}
}
