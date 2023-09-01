package com.switz.loanentity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;

@Builder
@Entity

public class Loan {
	
	@Id
	
	@GeneratedValue(strategy= GenerationType.AUTO)
	
	public Long Userid;
	public Long Bookid;
	public Date issueDate;
	public Date dueDate;
	public Long lateFee;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

