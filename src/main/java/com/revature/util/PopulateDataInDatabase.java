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
		
	}
	
private static void addERSReimbursementTypes() {
		
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		ERSReimbursementType lodging = new ERSReimbursementType("Lodging");
		ERSReimbursementType food = new ERSReimbursementType("Food");
		ERSReimbursementType travel = new ERSReimbursementType("Travel");
		ERSReimbursementType other = new ERSReimbursementType("Other");
		session.persist(lodging);
		session.persist(food);
		session.persist(travel);
		session.persist(other);
		
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
		
		User employee1 = new User("Colten", "Murray", "Colten.Murray@revature.net", "employee1", "password");
		User employee2 = new User("Bill", "Murray", "Bill.Murray@revature.net", "employee2", "password");
		User employee3 = new User("Jane", "Doe", "Jane.Doe@revature.net", "employee3", "password");
		User manager = new User("Bach", "Tran", "Bach.Tran@revature.net", "manager1", "password");
		session.persist(employee1);
		session.persist(employee2);
		session.persist(employee3);
		session.persist(manager);
		employee1.setUserRole(session.get(UserRole.class, 1));
		employee2.setUserRole(session.get(UserRole.class, 1));
		employee3.setUserRole(session.get(UserRole.class, 1));
		manager.setUserRole(session.get(UserRole.class, 2));
		
		tx.commit();
		
		
		session.close();
	}

}
