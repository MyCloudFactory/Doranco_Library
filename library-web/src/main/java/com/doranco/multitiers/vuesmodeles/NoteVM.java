package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;
import java.util.Date;

public class NoteVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2427605082496516935L;

	private long idUser;

	private long idBook;

	private int value;
	
	private String comment;
	
	private Date noteDate;
	

	public NoteVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}
	
	
	

}
