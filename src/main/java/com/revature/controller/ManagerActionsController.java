package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dto.EditReimbursementDTO;
import com.revature.model.ERSReimbursement;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.ERSReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ManagerActionsController implements Controller {
private AuthorizationService authService;
private ERSReimbursementService ERSReimbursementService;
	
	public ManagerActionsController() {
		this.authService = new AuthorizationService();
		this.ERSReimbursementService = new ERSReimbursementService();
	}
	
	private Handler filterRequestsByStatus = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		authService.guard(ctx); 
		User currentUser = (User) session.getAttribute("currentUser");
		String status = ctx.pathParam("status");
		
		List<ERSReimbursement> reimbursements = ERSReimbursementService.getAllReimbursementsFromStatus(status);
		
		ctx.json(reimbursements);
		
	};
	
	private Handler processRequest = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		authService.guard(ctx);
		
		String reimbId = ctx.pathParam("reimbid");
		User currentUser = (User) session.getAttribute("currentUser");
		
		EditReimbursementDTO reimbursementEditInfo = ctx.bodyAsClass(EditReimbursementDTO.class);
		ERSReimbursement editedReimbursement = ERSReimbursementService.editReimbursement(currentUser, reimbId, reimbursementEditInfo);
		ctx.json(editedReimbursement);
		
	};
	
	private Handler getAllRequests = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		authService.guard(ctx);
		
		
		List<ERSReimbursement> reimbursements = ERSReimbursementService.getAllReimbursements();
		
		ctx.json(reimbursements);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		
		app.get("/getAllRequests", getAllRequests);
		
		app.get("/filterRequestsByStatus/:status", filterRequestsByStatus);
		
		app.put("/processRequest/:reimbid", processRequest);
		
	}

}
