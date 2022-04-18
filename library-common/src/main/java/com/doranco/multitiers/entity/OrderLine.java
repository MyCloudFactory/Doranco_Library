package com.doranco.multitiers.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="l_orderline")
@IdClass(IdOrderLine.class) //On precise la classe qui sert de cle composite
public class OrderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id //Normalement les id n'acceptent que des types primitifs mais  ici utilisation @Idclass
	@ManyToOne
	private Order order;
	
	@Id //Normalement les id n'acceptent que des types primitifs mais  ici utilisation @Idclass
	@ManyToOne
	private Book book;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderLine other = (OrderLine) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
	

	
	
	
	
}
