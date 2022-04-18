package com.doranco.multitiers.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="l_order")
public class Order extends Identifiant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	private User user;
	
	@Transient //Non pris en compte pour la sauvegarde en BD(non persisté)
	private List<OrderLine> lines;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
	

}
