package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.sql.*;

@Entity
public class ERSReimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "REIMB_AMOUNT")
	private int reimbAmount;
	
	
	@Column(name = "REIMB_SUBMITTED")
	private Timestamp reimbSubmitted;
	
	@Column(name = "REIMB_RESOLVED")
	private Timestamp reimbResolved;
	
	@Column(name = "REIMB_DESCRIPTION")
	private String reimbDescription;
	
	@Column(name = "REIMB_RECEIPT")
	private Blob reimbReceipt;
	
	@ManyToOne
	@JoinColumn(name = "REIMB_STATUS_ID")
	private ERSReimbursementStatus status;
	
	@ManyToOne
	@JoinColumn(name = "REIMB_TYPE_ID")
	private ERSReimbursementType type;
	
	@ManyToOne
	@JoinColumn(name = "REIMB_AUTHOR")
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "REIMB_RESOLVER")
	private User resolver;
	////////////////////////////////////////////////////////////////////////////////////
	
	public ERSReimbursement() {
		super();
	}
	
	
	
	
	
	
	public ERSReimbursement(int id, int reimbAmount, String reimbDescription) {
		super();
		this.id = id;
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
	}


	public ERSReimbursement(int id, int reimbAmount, String reimbDescription, ERSReimbursementStatus status,
			ERSReimbursementType type) {
		super();
		this.id = id;
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
		this.status = status;
		this.type = type;
	}






	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}
	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}
	public Timestamp getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}
	public Blob getReimbReciept() {
		return reimbReceipt;
	}
	public void setReimbReciept(Blob reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}
	public ERSReimbursementStatus getStatus() {
		return status;
	}
	public void setStatus(ERSReimbursementStatus status) {
		this.status = status;
	}
	public ERSReimbursementType getType() {
		return type;
	}
	public void setType(ERSReimbursementType type) {
		this.type = type;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public User getResolver() {
		return resolver;
	}
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	

}
