package com.revature.dto;

import java.sql.Blob;

import com.revature.model.ERSReimbursementType;
import com.revature.model.User;

public class AddReimbursementDTO {
	private int reimbAmount;
	private String reimbDescription;
	private int type;
	//private Blob receipt;
	
	public int getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public AddReimbursementDTO() {
		super();
	}
	

}
