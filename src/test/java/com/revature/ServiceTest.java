package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

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
	public static ReimbTypeService rTypeS;
	
	
	public static UserRole testRole;
	public static UserRole testRoleMan;
	public static User testUser;
	public static User testUserMan;
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
		rTypeS= new ReimbTypeService();
		rStatusS= new ReimbStatusService();
		
	}	
	
	@Before 
	public void startup() {
		//2== employee, 3== manager
		testRole= new UserRole(2, "Employee");
		testRoleMan= new UserRole(3, "Manager");
		//user we want add
		testUser= new User (18,"testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com", testRole);
		testUserMan= new User (20,"testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com", testRoleMan);
		
		testReimbStatus= new ReimbStatus (1,"Pending");
		testReimbType = new ReimbType(1, "Lodging");
		//reimb to add thus they are NOT using constructor with id
		testReimb= new Reimbursement (67.86,new Timestamp(System.currentTimeMillis()), null, "testDescription", us.findById(1), null, rStatusS.findByStatus("Pending"), rTypeS.findByType("Other"));
	}

	//this works
	@Test
	public void testLogin() {
		System.out.println("in login");
		
		User u= new User(15,"kc2009", "bestie","Kassandra", "Rodriguez", "kc2009@gmail.com", testRole);
		System.out.println(u);
		boolean loginBool= ls.login(u);
		assertTrue(loginBool);
	}

	//this worksss
//	@Test
//	public void addUserTest() {
//		boolean userAdd= us.addUser(testUser);
//		assertEquals(userAdd,true);
//		
//	}
//	
//	//this worrks too
//	@Test
//	public void addReimbTest() {
//		boolean reimbAdd= rs.addReimbursement(testReimb);
//		assertTrue(reimbAdd);
//		
//	}
	
	//**********this works too***************
	
	//works
	@Test 
	public void getReimbById() {
		//13 id testReimb
		Reimbursement r = rs.findById(15);
		System.out.println(r);
		//it doesn't like timestamp
		Reimbursement rTestAgainst =new Reimbursement (15,67.86,r.getSubmitted(), null, "testDescription", us.findById(1), null, rStatusS.findByStatus("Pending"), rTypeS.findByType("Other"));
		System.out.println(rTestAgainst);
		assertEquals(r,rTestAgainst);
	}

	
	
	
	
	/*This all works below*************/

	@Test
	public void findUserById() {
		System.out.println("In find by user ID");
		User u= us.findById(15);
		System.out.println(u.password);
		User testAgainst = new User(15,"kc2009", u.password,"Kassandra", "Rodriguez", "kc2009@gmail.com", testRole);
		assertEquals(u,testAgainst);
	}
	@Test
	public void findUserByUsername() {
		System.out.println("In find by username");
		User u= us.findByUsername("kc2009");
		System.out.println(u.password);
		User testAgainst = new User(15,"kc2009", u.password,"Kassandra", "Rodriguez", "kc2009@gmail.com", testRole);
		assertEquals(u,testAgainst);
	}
	
	public void findUserByCredentials() {
		System.out.println("In find by credentials");
		User u= us.findByUserPassword("kc2009", "bestie");
		System.out.println(u.password);
		User testAgainst = new User(15,"kc2009", u.password,"Kassandra", "Rodriguez", "kc2009@gmail.com", testRole);
		assertEquals(u,testAgainst);
	}
	
	@Test
	public void findReimbStatusByStatus() {
		System.out.println("in find reimb by status test");
		
		ReimbStatus rs= rStatusS.findByStatus("Pending");
		assertEquals(testReimbStatus,rs);
	}
	
	@Test
	public void findReimbTypeByType() {
		System.out.println("in find reimb by type test");
		
		ReimbType rt= rTypeS.findByType("Lodging");
		assertEquals(testReimbType,rt);
	}
	
	@Test 
	public void findReimbByUser() {
		User u = us.findById(15);
		List<Reimbursement> rList= rs.findByUser(u);
		assertTrue(rList!=null);
	}
	
	@Test 
	public void findAllReimb() {
		List<Reimbursement> rList= rs.findAll();
		assertTrue(rList!=null);
	}
	
	@Test 
	public void findReimbByStatus() {
		List<Reimbursement> rListStatus= rs.findByStatus(1);
		for(Reimbursement r : rListStatus) {
			ReimbStatus status= r.getStatus();
			assertEquals(status,testReimbStatus);
		}
		//assertTrue(rListStatus!=null);
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
		rTypeS= null;
		rStatusS=null;
	}
	
}
