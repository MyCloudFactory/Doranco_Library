package com.doranco.multitiers.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Note;

@Stateless
@LocalBean
public class NoteDAO extends GenericDAO<Note> {

	public Note NotebyBookId(Book book) throws NoResultException {
		Note note = null;
		String jpql = "SELECT u FROM Note u WHERE u.book = :Tid";
		note = (Note) em.createQuery(jpql).setParameter("Tid", book).getSingleResult();

		return note;
	}
	
	public Note findByUseridAndNodeId(long userId, long bookId) {
		
		Note note = (Note) em.createQuery("SELECT n FROM Note n WHERE n.user.id= :userId AND n.book.id= :bookId")
				.setParameter("userId", userId).setParameter("bookId", bookId).getSingleResult();
		
		return note;
	}
	
	
	
}
