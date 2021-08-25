package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ERSReimbursementStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "REIMB_STATUS")
	private String reimbStatus;
	
	public ERSReimbursementStatus(){
		super();
	}
	
	public ERSReimbursementStatus(String status) {
		this.reimbStatus = status;
	}

	public int getREIMB_STATUS_ID() {
		return id;
	}

	public void setREIMB_STATUS_ID(int rEIMB_STATUS_ID) {
		id = rEIMB_STATUS_ID;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	

}
