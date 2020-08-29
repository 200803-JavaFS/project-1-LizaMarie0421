package com.revature.daos;

import com.revature.models.User;
import com.revature.models.UserRole;

public interface IUserRoleDAO {

	public boolean insertUserRole(UserRole ur);
	public UserRole selectById(int id);
}
