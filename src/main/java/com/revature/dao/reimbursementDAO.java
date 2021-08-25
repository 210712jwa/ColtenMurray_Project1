package com.revature.dao;

import java.util.List;
import java.sql.*;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.EditReimbursementDTO;
import com.revature.model.ERSReimbursement;
import com.revature.model.Ship;
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
		Date date = new Date();
		Timestamp submitted = new Timestamp(date.getTime());
		Blob reciept = null;
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		ERSReimbursement addedReimbursement = new ERSReimbursement();
		session.persist(addedReimbursement);
		addedReimbursement.setAuthor(session.get(User.class, id));
		addedReimbursement.setType(session.get(ERSReimbursementType.class, reimbursement.getType()));
		addedReimbursement.setReimbDescription(reimbursement.getReimbDescription());
		addedReimbursement.setReimbAmount(reimbursement.getReimbAmount());
		addedReimbursement.setStatus(session.get(ERSReimbursementStatus.class, 1));
		addedReimbursement.setReimbSubmitted(submitted);
		addedReimbursement.setReimbResolved(null);
		addedReimbursement.setResolver(null);
		addedReimbursement.setReimbReciept(reciept);
		
		tx.commit();
		
		session.close();
		
		return addedReimbursement;
	}
	
	public List<ERSReimbursement> getAllReimbursementsFromStatus(int status){
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// Get ships by owner id
		List<ERSReimbursement> reimbursements = session.createQuery("SELECT s FROM ERSReimbursement s JOIN s.status u WHERE u.id = :statusid").setParameter("statusid", status).getResultList();
		tx.commit();
		
		session.close();
	
		return reimbursements;
		
	}

	public ERSReimbursement editReimbursement(int userId, int reimbId, EditReimbursementDTO reimbursement) {
		Date date = new Date();
		Timestamp resolved = new Timestamp(date.getTime());
		
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// gets the old reimbursement
		ERSReimbursement reimbursementToEdit = session.get(ERSReimbursement.class, reimbId);
		
		// change the info
		reimbursementToEdit.setResolver(session.get(User.class, userId));
		
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
		return reimbursements;
	}
	
}
