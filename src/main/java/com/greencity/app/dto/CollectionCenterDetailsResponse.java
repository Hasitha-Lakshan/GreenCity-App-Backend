package com.greencity.app.dto;

public class CollectionCenterDetailsResponse {
	private  int centerId;
	private String username;
	private String centerName;
	private int contactNumber;
	private String email;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String location;
	private String wastetype;
	private int payment;
	private String description;
//	private List<String> workingDays;
	private boolean moreDetailStatus;
	private boolean status;
	
	
	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWastetype() {
		return wastetype;
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

	public boolean getMoreDetailStatus() {
		return moreDetailStatus;
	}

	public void setMoreDetailStatus(boolean moreDetailStatus) {
		this.moreDetailStatus = moreDetailStatus;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
