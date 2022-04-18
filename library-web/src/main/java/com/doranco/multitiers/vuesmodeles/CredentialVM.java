package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;

public class CredentialVM implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -18272989839793913L;

	private String username;
	
	private String pwd;

	public CredentialVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
}
