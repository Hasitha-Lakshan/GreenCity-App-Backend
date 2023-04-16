package com.greencity.app.dto;

import java.util.Date;

public class PickupRequestSummaryResponse {

	private int requestId;
	private String customerName;
	private String collectionCenterName;
	private String wasteType;
	private Date createdDate;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCollectionCenterName() {
		return collectionCenterName;
	}

	public void setCollectionCenterName(String collectionCenterName) {
		this.collectionCenterName = collectionCenterName;
	}

	public String getWasteType() {
		return wasteType;
	}

	public void setWasteType(String wasteType) {
		this.wasteType = wasteType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
