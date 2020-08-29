package com.revature.daos;

import java.util.List;

import com.revature.models.User;
import com.revature.models.UserRole;

public interface IUserDAO {
	
//	public void insert(User u);
//	public void update(User u);
	public boolean insertUser(User u);
	public boolean updateUser(User u);
	
	public User selectById(int id);
	
	public User selectByUsername(String username);
	
	public User selectByCredentials(String username, String password);
	
	public User selectByUserRole(UserRole ur);
	
	public List<User> selectAll();
	
}
