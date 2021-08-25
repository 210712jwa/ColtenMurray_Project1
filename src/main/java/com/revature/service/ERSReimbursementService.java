package com.revature.service;

import java.util.List;

import com.revature.dao.reimbursementDAO;
import com.revature.model.ERSReimbursement;
import com.revature.model.Ship;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.EditReimbursementDTO;

public class ERSReimbursementService {

	private reimbursementDAO reimbursementDao;
	
	public ERSReimbursementService() {
		this.reimbursementDao = new reimbursementDAO();
	}
	
	public List<ERSReimbursement> getAllReimbursementsFromUserId(String userId) {
		int id = Integer.parseInt(userId);
		
		List<ERSReimbursement> reimbursements = reimbursementDao.getAllReimbursementsFromUserId(id);
		
		return reimbursements;
	}

	public ERSReimbursement addAccountToUser(String userId, AddReimbursementDTO reimbursement) {
		int id = Integer.parseInt(userId);
		
		ERSReimbursement addedReimbursement = reimbursementDAO.addAccountToUser(id, reimbursement);
	
		return addedReimbursement;
	}

	public List<ERSReimbursement> getAllReimbursementsFromStatus(String status) {
		int statusid = Integer.parseInt(status);
		
		List<ERSReimbursement> reimbursements = reimbursementDao.getAllReimbursementsFromStatus(statusid);
		
		return reimbursements;
	}
	
	public ERSReimbursement editReimbursement(String userIdString, String reimbIdString, EditReimbursementDTO reimbursementInfo) {
		int userId = Integer.parseInt(userIdString);
		int reimbId = Integer.parseInt(reimbIdString);
		
		ERSReimbursement editedReimbursement = reimbursementDao.editReimbursement(userId, reimbId, reimbursementInfo);
		return editedReimbursement;
	}

	public List<ERSReimbursement> getAllReimbursements() {
		
		List<ERSReimbursement> reimbursements = reimbursementDao.getAllReimbursements();
		
		return reimbursements;
	}
	
}
