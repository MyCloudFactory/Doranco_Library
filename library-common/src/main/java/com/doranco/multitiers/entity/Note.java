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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="l_note")
@IdClass(IdNote.class) //On precise la classe qui sert de cle composite
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //Normalement les id n'acceptent que des types primitifs mais ici utilisation @Idclass
	@ManyToOne
	@NotNull
	private User user;
		
	@Id //Normalement les id n'acceptent que des types primitifs mais  ici utilisation @Idclass
	@ManyToOne
	@NotNull
	private Book book;
	
	@NotNull
	private Date noteDate;
	
	@NotNull
	@Min(0)
	@Max(5)
	private int value;
	
	@Size(min=0, max=240)
	private String comment;
	
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
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
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((noteDate == null) ? 0 : noteDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + value;
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
		Note other = (Note) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (noteDate == null) {
			if (other.noteDate != null)
				return false;
		} else if (!noteDate.equals(other.noteDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [user=" + user + ", book=" + book + ", noteDate=" + noteDate + ", value=" + value + ", comment="
				+ comment + "]";
	}

		
	
}
