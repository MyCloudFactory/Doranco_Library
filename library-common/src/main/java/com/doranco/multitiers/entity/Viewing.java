package com.doranco.multitiers.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="l_viewing")
@IdClass(IdView.class) //On precise la classe qui sert de cle composite
public class Viewing implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	
	@Id //Normalement les id n'acceptent que des types primitifs mais ici utilisation @Idclass
	@ManyToOne
	private User user;
	
	@Id //Normalement les id n'acceptent que des types primitifs mais ici utilisation @Idclass
	@ManyToOne
	private Book book;
	
	//nb d'heures
	@Min(1)
	@Max(4)
	private long duration;
	

	public Viewing() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startdate) {
		startDate = startdate;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Viewing other = (Viewing) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (duration != other.duration)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
	
	
}
