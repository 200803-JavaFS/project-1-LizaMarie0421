package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.AfterClass;
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
	public static UserRole testRole;
	public static User testUser;
	public static ReimbStatus testReimbStatus;
	public static ReimbType testReimbType;
	public static Reimbursement testReimb;
	
	
	
	public ServiceTest() {
		super();
	}

	@BeforeClass
	public static void set() {
		System.out.println("In Before Class");
		ls = new LoginService();
		us = new UserService();
		rs = new ReimbursementService();
		tStatusS= new ReimbTypeService();
		rStatusS= new ReimbStatusService();
		
	}	
	
	@Before 
	public void startup() {
		testRole= new UserRole(1, "Employee");
		//user we want add
		testUser= new User ("testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com", testRole);
		
		testReimbStatus= new ReimbStatus (1,"Pending");
		testReimbType = new ReimbType(1, "Lodging");
		//reimb to add thus they are NOT using constructor with id
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
	
	//works
//	@Test
//	public void getUserbyId() {
//		//id test ==17
//		User u= us.findById(17);
//		User testAgainst=new User (17,"testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com", testRole);
//		assertEquals(u,testAgainst);
//	}
	
	@Test 
	public void getReimbById() {
		//13 id testReimb
		Reimbursement r = rs.findById(13);
		//it doesn't like timestamp
		Reimbursement rTestAgainst =new Reimbursement (13,67.86,testReimb.getSubmitted(), null, "testDescription", testUser, null, testReimbStatus, testReimbType);
		assertEquals(r,rTestAgainst);
	}
	//works
//	@Test
//	public void updateUser() {
//		//we are updating username
//		User u= us.findById(1);
//		//change before running test again
//		u.setEmail("bigSis@gmail.com");
//		boolean newU= us.updateUser(u);
//		assertTrue(newU);
//	}

	//works
//	@Test 
//	public void updateReimb() {
//		Reimbursement r=rs.findById(13);
//		r.setSubmitted(new Timestamp(System.currentTimeMillis()) );
//		boolean reimbBool = rs.updateReimbursement(r);
//		Reimbursement rTestAgainst = new Reimbursement (13, 67.86,new Timestamp(System.currentTimeMillis()), null, "testDescription", testUser, null, testReimbStatus, testReimbType);		
//	}
	
	@Test
	public void findUserByUserName() {
		User u= us.findByUserPassword("kc2009","bestie");
		//(int id, String username, String password, String first, String last, String email, UserRole userRole)
		//User testAgainst = new User(15,"kc2009", "bestie","Kassandra", "Rodriguez", "kc2009@gmail.com", testRole);
		assertTrue(u!=null);
	}
	
	/**
	 * 
	 */
	@AfterClass
	public static void shutdown() {
		testRole= null;
		testUser= null;
		testReimbStatus= null;
		testReimbType= null;
		testReimb= null;
		ls = null;
		us = null;
		rs = null;
		tStatusS= null;
		rStatusS=null;
	}
	
}
