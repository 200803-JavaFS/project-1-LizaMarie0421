package com.revature.services;

import com.revature.models.User;

public class LoginService {
	private static UserService us= new UserService();
	public boolean login(User u) {
		StringBuilder sb = new StringBuilder();
		int HashPass=u.password.hashCode();
		sb.append(HashPass);
		String hashedPassword= sb.toString();
		
		User uN = us.findByUserPassword(u.username,hashedPassword);
		//System.out.println(u.password);
		System.out.println("hashed: "+ hashedPassword);
		System.out.println("idfk: "+uN.getPassword());
		
		
		if (u.username.equals(uN.getUsername()) && hashedPassword.equals(uN.getPassword())) {
			
			return true;
		}
		return false;
	}
}
