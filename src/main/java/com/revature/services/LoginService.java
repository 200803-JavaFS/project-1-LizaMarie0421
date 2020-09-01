package com.revature.services;

import com.revature.models.User;

public class LoginService {
	private static UserService us= new UserService();
	public boolean login(User u) {
		User uN = us.findByUserPassword(u.username,u.password);
		//uN.hashCode();
		//u.password.hashCode();
		if (u.username.equals(uN.getUsername()) && u.password.equals(uN.getPassword())) {
			
			return true;
		}
		return false;
	}
}
