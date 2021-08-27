package com.revature.dao;

import java.util.List;
import java.sql.*;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.EditReimbursementDTO;
import com.revature.util.SessionFactorySingleton;
import com.revature.model.*;

public class reimbursementDAO {

	public List<ERSReimbursement> getAllReimbursementsFromUserId(int id) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// Get ships by owner id
		List<ERSReimbursement> reimbursements = session.createQuery("SELECT s FROM ERSReimbursement s JOIN s.author u WHERE u.id = :userid").setParameter("userid", id).getResultList();
		tx.commit();
		
		session.close();
	
		return reimbursements;
	}

	public static ERSReimbursement addAccountToUser(int id, AddReimbursementDTO reimbursement) {
		
		// generate timestamp from current time
		Date date = new Date();
		Timestamp submitted = new Timestamp(date.getTime());
		
		// initialize dummy receipt
		Blob reciept = null;
		
		//start session
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// initialize database object
		ERSReimbursement addedReimbursement = new ERSReimbursement();
		
		// persist object
		session.persist(addedReimbursement);
		
		addedReimbursement.setAuthor(session.get(User.class, id) /* set author from id passed down from controller layer*/);
		
		addedReimbursement.setType(session.get(ERSReimbursementType.class, reimbursement.getType())/* set type from index gotten from DTO object*/);
		
		addedReimbursement.setReimbDescription(reimbursement.getReimbDescription() /* get description from DTO object*/);
		
		addedReimbursement.setReimbAmount(reimbursement.getReimbAmount() /* get amount from DTO object*/);
		
		addedReimbursement.setStatus( session.get(ERSReimbursementStatus.class, 1) /*sets status to pending by default*/ );
		
		addedReimbursement.setReimbSubmitted(submitted /* set submitted time to generated time stamp*/);
		
		// set unused properties to null for now
		addedReimbursement.setReimbResolved(null);
		addedReimbursement.setResolver(null);
		
		// set receipt to dummy receipt for now
		addedReimbursement.setReimbReciept(reciept);
		
		tx.commit();
		
		session.close();
		
		// return finished product to controller layer
		return addedReimbursement;
	}
	
	public List<ERSReimbursement> getAllReimbursementsFromStatus(int status){
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// Get reimbursements by user id
		List<ERSReimbursement> reimbursements = session.createQuery("SELECT s FROM ERSReimbursement s JOIN s.status u WHERE u.id = :statusid").setParameter("statusid", status).getResultList();
		tx.commit();
		
		session.close();
	
		return reimbursements;
	}

	public ERSReimbursement editReimbursement(User currentUser, int reimbId, EditReimbursementDTO reimbursement) {
		Date date = new Date();
		Timestamp resolved = new Timestamp(date.getTime());
		
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// gets the old reimbursement
		ERSReimbursement reimbursementToEdit = session.get(ERSReimbursement.class, reimbId);
		
		// change the info
		reimbursementToEdit.setResolver(currentUser);
		
		reimbursementToEdit.setStatus( session.get( ERSReimbursementStatus.class, reimbursement.getStatus() ) );
		
		reimbursementToEdit.setReimbResolved(resolved);
		
		//update the database
		session.saveOrUpdate(reimbursementToEdit);
		
		tx.commit();
		
		session.close();
		
		return reimbursementToEdit;
	}

	public List<ERSReimbursement> getAllReimbursements() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ERSReimbursement> reimbursements = session.createQuery("SELECT s FROM ERSReimbursement s").getResultList();
		
		tx.commit();
		session.close();
		
		return reimbursements;
	}
	
}
