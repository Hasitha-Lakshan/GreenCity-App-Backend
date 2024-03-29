package com.greencity.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pickup_requests")
public class PickupRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	@Column(length = 300)
	private String note;
	@NotEmpty
	@Column
	private float quantity;
	@NotEmpty
	@Column
	private int totalPayment;
	@NotEmpty
	@Column
	private Date createdDate;
	@Column
	private Date completedDate;
	@Column
	private Date acceptedDate;
	@Column
	private Date canceledDate;
	@NotEmpty
	@Column(columnDefinition = "enum('INPROGRESS','ACTIVE','COMPLETED','CANCELED')")
	private String requestStatus;
	@NotEmpty
	@Column
	private String collectionAddressLine1;
	@NotEmpty
	@Column
	private String collectionAddressLine2;
	@Column
	private String collectionAddressLine3;
	@NotEmpty
	@Column
	private String collectionLocation;

	// Relationships

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "centerId")
	private CollectionCenter collectionCenter;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public Date getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(Date canceledDate) {
		this.canceledDate = canceledDate;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CollectionCenter getCollectionCenter() {
		return collectionCenter;
	}

	public void setCollectionCenter(CollectionCenter collectionCenter) {
		this.collectionCenter = collectionCenter;
	}

}
