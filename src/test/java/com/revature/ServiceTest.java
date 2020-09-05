package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.LoginService;
import com.revature.services.ReimbStatusService;
import com.revature.services.ReimbTypeService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ServiceTest {
	//initialize services
	public static LoginService ls;
	public static UserService us;
	public static ReimbursementService rs;
	public static ReimbStatusService rStatusS;
	public static ReimbTypeService tStatusS;
	UserRole testRole;
	User testUser;
	ReimbStatus testReimbStatus;
	ReimbType testReimbType;
	Reimbursement testReimb;
	
	
	
	public ServiceTest() {
		super();
	}

	@BeforeClass
	public static void set() {
		System.out.println("In Before Class");
		ls = new LoginService();
		us = new UserService();
		rs = new ReimbursementService();
		
	}	
	
	@Before 
	public void startup() {
		testRole= new UserRole(1, "Employee");
		testUser= new User ("testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com", testRole);
		
		testReimbStatus= new ReimbStatus (1,"Pending");
		testReimbType = new ReimbType(1, "Lodging");
		testReimb= new Reimbursement (67.86,new Timestamp(System.currentTimeMillis()), null, "testDescription", testUser, null, testReimbStatus, testReimbType);
	}
	 //this does not worrrkkkk
//	@Test
//	public void testLogin() {
//		User u= us.findByUserPassword("lizzvj", "cashjoey");
//		boolean loginBool= ls.login(u);
//		assertTrue(loginBool);
//	}
		
	//this worksss
//	@Test
//	public void addUserTest() {
//		boolean userAdd= us.addUser(testUser);
//		assertEquals(userAdd,true);
//		
//	}
	
	//this worrks too
//	@Test
//	public void addReimbTest() {
//		boolean reimbAdd= rs.addReimbursement(testReimb);
//		assertTrue(reimbAdd);
//		
//	}
	
	@Test
	public void getUserbyId() {
		//id test ==17
		User u= us.findById(17);
		User testAgainst=new User (17,"testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com", testRole);

	}
	
}
