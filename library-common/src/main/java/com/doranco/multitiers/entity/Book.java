package com.doranco.multitiers.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "l_book")
public class Book extends Identifiant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	// @Pattern(regexp = "^\\d{9}[\\d[X]$") //10 characteres de 0 à 9 avec X à la
	// fin
	private String ISBN;
	@NotNull
	private String title;
	
	@Lob //Pour enregistrer le contenu binaire d'un fichier en BD 
	@NotNull
	private byte[] content; //tableau d'octet

	/*
	 * @OneToMany(mappedBy="book", fetch=FetchType.LAZY) private List<Note> notes;
	 * 
	 * @OneToMany(mappedBy="book", fetch=FetchType.LAZY) private List<Viewing>
	 * viewings;
	 */

	public Book() {

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

	/*
	 * public List<Note> getNotes() { return notes; }
	 * 
	 * public void setNotes(List<Note> notes) { this.notes = notes; }
	 */

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", title=" + title + "]";
	}
	
	

}
