package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.reimbursementDAO;

import com.revature.model.ERSReimbursement;
import com.revature.model.Ship;
import com.revature.model.User;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.EditReimbursementDTO;
import com.revature.exception.BadParameterException;

public class ERSReimbursementService {

	private reimbursementDAO reimbursementDao;
	
	public ERSReimbursementService() {
		this.reimbursementDao = new reimbursementDAO();
	}
	public ERSReimbursementService(reimbursementDAO reimbursementDao) {
		this.reimbursementDao = reimbursementDao;
	}
	
	public List<ERSReimbursement> getAllReimbursementsFromUserId(String userId) throws BadParameterException {
		List<ERSReimbursement> reimbursements;
		try {
			int id = Integer.parseInt(userId);
			
			reimbursements = reimbursementDao.getAllReimbursementsFromUserId(id);
			
		} catch (NumberFormatException e) {
			throw new BadParameterException(userId + " was passed in by the user as the id, " + "but it is not an int");
		}
		
		
		return reimbursements;
	}

	public ERSReimbursement addAccountToUser(String userId, AddReimbursementDTO reimbursement) throws BadParameterException {
		ERSReimbursement addedReimbursement;
		try {
			int id = Integer.parseInt(userId);
			
			addedReimbursement = reimbursementDAO.addAccountToUser(id, reimbursement);
			
		} catch (NumberFormatException e) {
			throw new BadParameterException(userId + " was passed in by the user as the id, " + "but it is not an int");
		}
		
	
		return addedReimbursement;
	}

	public List<ERSReimbursement> getAllReimbursementsFromStatus(String status) throws BadParameterException {
		
		List<ERSReimbursement> reimbursements;
		try {
			int statusid = Integer.parseInt(status);
			
			reimbursements = reimbursementDao.getAllReimbursementsFromStatus(statusid);
			
		} catch (NumberFormatException e) {
			throw new BadParameterException(status + " was passed in by the user as the id, " + "but it is not an int");
		}
		
		
		return reimbursements;
	}
	
	public ERSReimbursement editReimbursement(User currentUser, String reimbIdString, EditReimbursementDTO reimbursementInfo) throws BadParameterException {
		ERSReimbursement editedReimbursement;
		try {
			int reimbId = Integer.parseInt(reimbIdString);
			
			editedReimbursement = reimbursementDao.editReimbursement(currentUser, reimbId, reimbursementInfo);
			
		} catch (NumberFormatException e) {
			throw new BadParameterException(reimbIdString + " was passed in by the user as the id, " + "but it is not an int");
		}
		
		
		return editedReimbursement;
	}

	public List<ERSReimbursement> getAllReimbursements() {
		
		List<ERSReimbursement> reimbursements = reimbursementDao.getAllReimbursements();
		
		return reimbursements;
	}
	
}
