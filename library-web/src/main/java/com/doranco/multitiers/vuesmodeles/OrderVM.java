package com.doranco.multitiers.vuesmodeles;

import java.io.Serializable;
import java.util.List;

public class OrderVM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -523605003363608597L;

	private long id;

	private long idUser;

	private List<OrderLineVM> lines;

	public OrderVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public List<OrderLineVM> getLines() {
		return lines;
	}

	public void setLines(List<OrderLineVM> lines) {
		this.lines = lines;
	}

	
}
