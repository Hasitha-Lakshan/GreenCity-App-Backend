package com.greencity.app.dto;

public class SummaryListRequest {
	private String username;
	private String userRole;
	private String listType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	@Override
	public String toString() {
		return "SummaryListRequest [username=" + username + ", userRole=" + userRole + ", listType=" + listType + "]";
	}

}
