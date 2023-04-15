package com.greencity.app.dto;

public class PickupRequestRequest {

	private float quantity;
	private String customerContactNumber;
	private String customerAddressLine1;
	private String customerAddressLine2;
	private String customerAddressLine3;
	private String customerLocation;
	private String note;
	private int totalPayment;
	private String customerUsername;
	private String collectionCenterUsername;

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public String getCustomerAddressLine1() {
		return customerAddressLine1;
	}

	public void setCustomerAddressLine1(String customerAddressLine1) {
		this.customerAddressLine1 = customerAddressLine1;
	}

	public String getCustomerAddressLine2() {
		return customerAddressLine2;
	}

	public void setCustomerAddressLine2(String customerAddressLine2) {
		this.customerAddressLine2 = customerAddressLine2;
	}

	public String getCustomerAddressLine3() {
		return customerAddressLine3;
	}

	public void setCustomerAddressLine3(String customerAddressLine3) {
		this.customerAddressLine3 = customerAddressLine3;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCollectionCenterUsername() {
		return collectionCenterUsername;
	}

	public void setCollectionCenterUsername(String collectionCenterUsername) {
		this.collectionCenterUsername = collectionCenterUsername;
	}

}
