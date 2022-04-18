package com.doranco.multitiers.entity;

import java.io.Serializable;


public class IdNote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long user; //type de l'id de la class User

	private long book; //type de l'id de la class Book
	

	public IdNote() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getUser() {
		return user;
	}


	public void setUser(long user) {
		this.user = user;
	}


	public long getBook() {
		return book;
	}


	public void setBook(long book) {
		this.book = book;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (book ^ (book >>> 32));
		result = prime * result + (int) (user ^ (user >>> 32));
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
		IdNote other = (IdNote) obj;
		if (book != other.book)
			return false;
		if (user != other.user)
			return false;
		return true;
	}



	

}
