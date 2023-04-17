package com.greencity.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CollectionCenters")
public class CollectionCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int centerId;
	@Column(unique = true)
	private String username;
	@NotEmpty
	@Column
	private String centertName;
	@NotEmpty
	@Column
	private String password;
	@NotEmpty
	@Column(length = 10, unique = true)
	private String contactNumber;
	@NotEmpty
	@Column(unique = true)
	private String email;
	@NotEmpty
	@Column
	private String addressLine1;
	@NotEmpty
	@Column
	private String addressLine2;
	@Column
	private String addressLine3;
	@NotEmpty
	@Column
	private String location;
	@NotEmpty
	@Column(length = 300)
	private String description;
	@NotEmpty
	@Column
	private String wasteType;
	@NotEmpty
	@Column
	private int payment;
	@NotEmpty
	@Column
	private boolean active;

	@ElementCollection
	@CollectionTable(name = "working_days", joinColumns = @JoinColumn(name = "center_id"))
	@Column(name = "day")
	private List<String> workingDays;

	@NotEmpty
	@Column
	private boolean accountStatus;

	// Relationships

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "collectionCenter", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PickupRequest> requests;

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

	public String getCentertName() {
		return centertName;
	}

	public void setCentertName(String centertName) {
		this.centertName = centertName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWasteType() {
		return wasteType;
	}

	public void setWasteType(String wasteType) {
		this.wasteType = wasteType;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<String> getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(List<String> workingDays) {
		this.workingDays = workingDays;
	}

	public boolean isAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public List<PickupRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<PickupRequest> requests) {
		this.requests = requests;
	}

}
