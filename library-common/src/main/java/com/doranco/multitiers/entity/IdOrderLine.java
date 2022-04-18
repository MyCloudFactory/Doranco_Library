package com.doranco.multitiers.entity;

import java.io.Serializable;


public class IdOrderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long book; //type de l'id de la class Book
	
	private long order; //type de l'id de la class Order
		
	public IdOrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getBook() {
		return book;
	}


	public void setBook(long book) {
		this.book = book;
	}


	public long getOrder() {
		return order;
	}


	public void setOrder(long order) {
		this.order = order;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (book ^ (book >>> 32));
		result = prime * result + (int) (order ^ (order >>> 32));
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
		IdOrderLine other = (IdOrderLine) obj;
		if (book != other.book)
			return false;
		if (order != other.order)
			return false;
		return true;
	}


}
