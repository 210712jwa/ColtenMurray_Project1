package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ERSReimbursementType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int REIMB_TYPE_ID;
	
	@Column(name = "REIMB_TYPE")
	private String reimbType;
	
	public ERSReimbursementType() {
		super();
	}
	
	public ERSReimbursementType(String reimbType) {
		super();
		this.reimbType = reimbType;
	}

	public int getREIMB_TYPE_ID() {
		return REIMB_TYPE_ID;
	}

	public void setREIMB_TYPE_ID(int rEIMB_TYPE_ID) {
		REIMB_TYPE_ID = rEIMB_TYPE_ID;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
	


}
