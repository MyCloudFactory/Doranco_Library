package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;

public class BookVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6848315382194532879L;

	private long id;
	
	private String ISBN;
	
	private String title;
	
	private byte[] content;

	public BookVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long idBook) {
		this.id = idBook;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
