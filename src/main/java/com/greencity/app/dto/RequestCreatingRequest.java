package com.greencity.app.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RequestCreatingRequest {

	private int collectionCenterId;
	private String customerUsername;
	private String collectionAddressLine1;
	private String collectionAddressLine2;
	private String collectionAddressLine3;
	private String collectionLocation;
	private int contactNumber;
	private String note;
	private float quantity;
	private int totalPayment;
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	private Date createdDate;

	public int getCollectionCenterId() {
		return collectionCenterId;
	}

	public void setCollectionCenterId(int collectionCenterId) {
		this.collectionCenterId = collectionCenterId;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCollectionAddressLine1() {
		return collectionAddressLine1;
	}

	public void setCollectionAddressLine1(String collectionAddressLine1) {
		this.collectionAddressLine1 = collectionAddressLine1;
	}

	public String getCollectionAddressLine2() {
		return collectionAddressLine2;
	}

	public void setCollectionAddressLine2(String collectionAddressLine2) {
		this.collectionAddressLine2 = collectionAddressLine2;
	}

	public String getCollectionAddressLine3() {
		return collectionAddressLine3;
	}

	public void setCollectionAddressLine3(String collectionAddressLine3) {
		this.collectionAddressLine3 = collectionAddressLine3;
	}

	public String getCollectionLocation() {
		return collectionLocation;
	}

	public void setCollectionLocation(String collectionLocation) {
		this.collectionLocation = collectionLocation;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
