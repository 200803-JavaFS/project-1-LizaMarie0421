package com.revature;

import java.sql.Timestamp;
import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.daos.UserRoleDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;

public class Driver {
	public static UserDAO uDao = new UserDAO();
	public static UserRoleDAO urDao = new UserRoleDAO();
	public static ReimbursementDAO rDao = new ReimbursementDAO();
	
	public static void main(String[] args) {
		//insertValues();
//		UserRole ur2= new UserRole(2,"Employee");
//		User user2= new User(2,"og44", "iluvmichelle", "Barack", "Obama", "pres@gmail.com",ur2);
////		ReimbStatus rs=new ReimbStatus(1,"Pending");
////		ReimbStatus rs1=new ReimbStatus(2,"Approved");
////		ReimbStatus rs2=new ReimbStatus(3,"Denied");
////		Reimbursement reim= new Reimbursement(50.55, new Timestamp(System.currentTimeMillis()),null, "test",user2,null,rs, new ReimbType(3,"Food"));
////		Reimbursement reim2= new Reimbursement(150.55,new Timestamp(System.currentTimeMillis()),null, "test2",user2,null,rs1, new ReimbType(1,"Lodging"));
////		Reimbursement reim3= new Reimbursement(75.55,new Timestamp(System.currentTimeMillis()),null, "test3",user2,null,rs2, new ReimbType(2,"Travel"));
////		rDao.insertReimb(reim);
////		rDao.insertReimb(reim2);
////		rDao.insertReimb(reim3);
////		
////		//System.out.println( new Timestamp(System.currentTimeMillis()));
//		UserRole ur= new UserRole(2,"Employee");	
//		User user= new User(15,"kc2009", "bestie", "Kassandra", "Rodriguez", "kc2009@gmail.com",ur);
////		//uDao.insertUser(user);
////		uDao.insertUser(user);
//		ReimbStatus rs=new ReimbStatus(1,"Pending");
//		Reimbursement reim= new Reimbursement(700,new Timestamp(System.currentTimeMillis()),null, "kc trying to go to mx",user,null,rs, new ReimbType(2,"Travel"));
//		rDao.insertReimb(reim);
//		
//		User testHashUser = uDao.selectById(1);
//		StringBuilder sb = new StringBuilder();
//		int HashPass= testHashUser.getPassword().hashCode();
//		System.out.println("int: " + HashPass);
//		sb.append(HashPass);
//		String hashedPassword= sb.toString();
//		System.out.println("string: "+ hashedPassword);
//		testHashUser.setPassword(hashedPassword);
//		uDao.updateUser(testHashUser);
//		System.out.println(testHashUser);
//		
//		
		updatePass();
//		System.out.println("Printing all Users");
//		List<User> users= uDao.selectAll();
//		for(User u: users) {
//			System.out.println(u);
//			StringBuilder sb = new StringBuilder();
//			sb.append(u.getPassword().hashCode());
//			String hashPass = sb.toString();
//			u.setPassword(u.getPassword().hashCode());
//			System.out.println(u);
//			System.out.println(u.getPassword().hashCode());
//		}
//		
//		User kc= uDao.selectByUsername("lizzvj");
//		System.out.println("kc info: "+ kc);
//		System.out.println("Printing all Reimbursements=======================");
//		List<Reimbursement> reimbursements= rDao.selectAll();
//		for(Reimbursement r: reimbursements) {
//			System.out.println(r);
//		}
//		
//		System.out.println("Printing all Pending===================================");
//		List<Reimbursement> checkStatus = rDao.selectByStatus(1);
//		for(Reimbursement cs: checkStatus) {
//			System.out.println("this should be pending: "+ cs);
//		}
//		
//		System.out.println("Updated Reimbursement with id 1 status to pending======================");
//		updateReimb();
//		
//		System.out.println("Finding by username og44======================");
//		List<Reimbursement> listByAuthor  = rDao.selectByAuthor(user2);
//		for(Reimbursement ra: listByAuthor) {
//			System.out.println("this should be og44: "+ ra);
//		}
		
//		System.out.println("Finding by usernamekc 2009 and password bestie======================");
//		User userKC  = uDao.selectByCredentials("kc2009", "bestie");
//		System.out.println(userKC);
	}

	public static void insertValues() {
		UserRole ur= new UserRole(3,"Manager");
		UserRole ur2= new UserRole(2,"Employee");
		//urDao.insertUserRole(ur);
		
		User user= new User("lizzvj", "cashjoey", "Lizette", "Jimenez", "lizzvj@gmail.com",ur);
		User user2= new User("og44", "iluvmichelle", "Barack", "Obama", "pres@gmail.com",ur2);
		//uDao.insertUser(user);
		uDao.insertUser(user);
		uDao.insertUser(user2);
		
//		Reimbursement reim= new Reimbursement(50.55,timestamp, timestamp );
//		rDao.insert(reim);
		
	}
	
	public static void updateReimb() {
		Reimbursement r= rDao.selectById(1);
		System.out.println("reimb with id 1: " + r);
		ReimbStatus rStatus= new ReimbStatus(2,"Approved");
		r.setStatus(rStatus);
		rDao.update(r);
		System.out.println(rDao.selectById(1));
		System.out.println("what it should be updated to: " +r);
	}
	
	public static void updatePass() {
		System.out.println("Printing all Users");
		List<User> users= uDao.selectAll();
		for(User u: users) {
			StringBuilder sb = new StringBuilder();
			int HashPass= u.getPassword().hashCode();
			System.out.println("int: " + HashPass);
			sb.append(HashPass);
			String hashedPassword= sb.toString();
			System.out.println("string: "+ hashedPassword);
			u.setPassword(hashedPassword);
			uDao.updateUser(u);
			System.out.println(u);
			
		}
		
	}
}
