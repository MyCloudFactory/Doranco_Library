package com.doranco.multitiers.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "l_user")

public class User extends Identifiant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstName;
	@NotNull // Clause/contrainte de saisie
	private String lastName;
	@NotNull
	private String userName;
	@NotNull
	private String password;

	private boolean isAdmin = false;
	
	private boolean isSuperAdmin = false;

	/*
	 * @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) private List<Order>
	 * orders;
	 * 
	 * @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) private List<Note>
	 * notes;
	 * 
	 * @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) private List<Viewing>
	 * viewings;
	 */

	public User() {
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	
	/*
	 * public List<Note> getNotes() { return notes; }
	 * 
	 * public void setNotes(List<Note> notes) { this.notes = notes; }
	 * 
	 * public List<Order> getOrder() { return orders; }
	 * 
	 * public void setOrder(List<Order> order) { this.orders = order; }
	 * 
	 * public List<Viewing> getViewings() { return viewings; }
	 * 
	 * public void setViewings(List<Viewing> viewings) { this.viewings = viewings; }
	 */

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", isAdmin=" + isAdmin + ", isSuperAdmin=" + isSuperAdmin + "]";
	}

}
