package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;

public class OrderLineVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4875200971668669002L;
	private long idBook;

	public OrderLineVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}
	
	
}
