package com.greencity.app.dto;

import java.util.List;

public class CollectionCenterDetailAddRequest {
	private String currentUserName;
	private String wastetype;
	private int payment;
	private String description;
//	private List<String> workingDays;

	
	public String getWastetype() {
		return wastetype;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public void setWastetype(String wastetype) {
		this.wastetype = wastetype;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<String> getWorkingDays() {
//		return workingDays;
//	}
//
//	public void setWorkingDays(List<String> workingDays) {
//		this.workingDays = workingDays;
//	}


}
