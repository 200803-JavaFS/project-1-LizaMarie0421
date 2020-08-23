package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IUserRoleDAO;
import com.revature.daos.UserRoleDAO;
import com.revature.models.UserRole;

public class UserRoleService {

	private static final Logger log = LogManager.getLogger(UserService.class);
	private static IUserRoleDAO urDao= new UserRoleDAO();
	
	public UserRole findById(int id) {
		log.info("Finding UseRole with user id "+ id);
		return urDao.findById(id);
	}
}
