package com.revature.util;

import java.sql.Blob;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.ERSReimbursement;
import com.revature.model.ERSReimbursementStatus;
import com.revature.model.ERSReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRole;

public class PopulateDataInDatabase {
	
	public static void main(String[] args) {
		
		addERSReimbursementTypes();
		addERSUserRoles();
		addERSReimbursementStatus();
		addExampleUsers();
		addExampleReimbursements();
	}
	
private static void addERSReimbursementTypes() {
		
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		ERSReimbursementType lodging = new ERSReimbursementType("Lodging");
		ERSReimbursementType food = new ERSReimbursementType("Food");
		ERSReimbursementType travel = new ERSReimbursementType("Travel");
		session.persist(lodging);
		session.persist(food);
		session.persist(travel);
		
		tx.commit();
		
		session.close();
	}
	
	private static void addERSUserRoles() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		UserRole employee = new UserRole("Employee");
		UserRole financialManager = new UserRole("Financial Manager");
		session.persist(employee);
		session.persist(financialManager);
		
		tx.commit();
		
		session.close();
	}
	
	private static void addERSReimbursementStatus() {
        SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		ERSReimbursementStatus pending = new ERSReimbursementStatus("Pending");
		ERSReimbursementStatus accepted = new ERSReimbursementStatus("Accepted");
		ERSReimbursementStatus denied = new ERSReimbursementStatus("Denied");
		session.persist(pending);
		session.persist(accepted);
		session.persist(denied);
		
		tx.commit();
		
		session.close();
	}
	
	private static void addExampleUsers() {
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		User employee = new User("Colten", "Murray", "Colten.Murray@revature.net", "employee1", "password");
		User manager = new User("Bach", "Tran", "Bach.Tran@revature.net", "manager1", "password");
		session.persist(employee);
		session.persist(manager);
		employee.setUserRole(session.get(UserRole.class, 1));
		manager.setUserRole(session.get(UserRole.class, 2));
		
		tx.commit();
		
		
		session.close();
	}
	
	private static void addExampleReimbursements(){
		
		Timestamp submitted = new Timestamp(2021, 8, 16, 12, 30, 52, 12);
		Timestamp resolved = new Timestamp(2021, 8, 17, 3, 32, 36, 37);
		Blob receipt = null;
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		ERSReimbursement reimbursement = new ERSReimbursement(submitted, resolved, "Example Reimbursement", receipt);
		session.persist(reimbursement);
		reimbursement.setAuthor(session.get(User.class, 1));
		reimbursement.setResolver(session.get(User.class, 2));
		reimbursement.setType(session.get(ERSReimbursementType.class, 1));
		reimbursement.setStatus(session.get(ERSReimbursementStatus.class, 1));
		
		ERSReimbursement reimbursementNotPending = new ERSReimbursement(submitted, resolved, "Example Reimbursement not pending", receipt);
		session.persist(reimbursementNotPending);
		reimbursementNotPending.setAuthor(session.get(User.class, 1));
		reimbursementNotPending.setResolver(session.get(User.class, 2));
		reimbursementNotPending.setType(session.get(ERSReimbursementType.class, 1));
		reimbursementNotPending.setStatus(session.get(ERSReimbursementStatus.class, 2));
		
		tx.commit();
		
		
		session.close();
	}

}
