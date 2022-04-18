package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;
import java.util.List;

public class UserVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 216654465504427504L;

	private long id;
	
	private String firstName;

	private String lastName;

	private String userName;
	
	private boolean isAdmin;
		
	private  String password;

	public UserVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
