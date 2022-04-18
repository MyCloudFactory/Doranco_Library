package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;
import java.util.Date;

public class ViewingVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2970756577651900944L;

	private long idBook;

	private Date startDate;

	private long duration;

	public ViewingVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
	
}
