package com.OnlineStore.onlineStore.model;

import java.sql.Timestamp;
import java.time.Instant;

public class Customer {

	private int cust_Id;
	private String username;
	private String mobile;
	private String email;
	private String address;
	private String status = "A";
	private String password;
	private boolean loginStatus;

	public Customer() {
		super();
	}

	public Customer(int cust_Id, String username, String mobile, String email, String address, String status,
			String password, boolean loginStatus) {
		super();
		this.cust_Id = cust_Id;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.status = status;
		this.password = password;
		this.loginStatus = loginStatus;
	}

	public int getCust_Id() {
		return cust_Id;
	}

	public void setCust_Id(int cust_Id) {
		this.cust_Id = cust_Id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

}
