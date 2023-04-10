package com.greencity.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(unique = true)
	private String username;
	@NotEmpty
	@Column
	private String firstName;
	@NotEmpty
	@Column
	private String lastName;
	@NotEmpty
	@Column
	private String password;
	@NotEmpty
	@Column(length = 10, unique = true)
	private int contactNumber;
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
	private boolean active_or_disable;
	@NotEmpty
	@Column
	private boolean accountStatus;
	@NotEmpty
	@Column
	private boolean isAdmin;
	
	////////////////////////////// Relationship /////////////////////////////////
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Customer_Request> request;
	
	////////////////////////////////////////////////////////////////////////////

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public boolean isActive_or_disable() {
		return active_or_disable;
	}

	public void setActive_or_disable(boolean active_or_disable) {
		this.active_or_disable = active_or_disable;
	}

	public boolean isAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
