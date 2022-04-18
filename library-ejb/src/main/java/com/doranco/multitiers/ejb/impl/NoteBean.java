package com.doranco.multitiers.ejb.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.doranco.multitiers.dao.BookDAO;
import com.doranco.multitiers.dao.NoteDAO;
import com.doranco.multitiers.dao.UserDAO;
import com.doranco.multitiers.ejb.interfaces.INote;
import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.exceptions.EntityNotFoundException;
import com.doranco.multitiers.exceptions.LibraryException;

@Stateless
public class NoteBean implements INote {

	Logger logger = Logger.getLogger(NoteBean.class);

	@EJB
	NoteDAO noteDAO;
	@EJB
	BookDAO bookDAO;
	@EJB
	UserDAO userDAO;
	

	@Override
	public Note NotebyBookId(long id) throws LibraryException {
		Note note = null;
		try {

			note = noteDAO.NotebyBookId(bookDAO.findBookbyId(id));

			logger.info("contenu note: " + note.toString());

		} catch (Exception e) {
			logger.error("Recherche Note : Id donné ne correspond à aucun livre", e);
			throw new LibraryException();
		}

		if (note == null)
			throw new EntityNotFoundException();

		return note;
	}

	@Override
	public Note createNote(Note note) throws LibraryException {
		
		try {

		//recuperation du livre correspondant à noter
		Book book = bookDAO.findBookbyId(note.getBook().getId());
		note.setBook(book);
		
		//recuperation de l'utilisateur noteur
		User user = userDAO.findUserbyid(note.getUser().getId());
		note.setUser(user);
		
		note.setNoteDate(new Date());
		
		note = noteDAO.create(note);
		
		return note;
		
		} catch (Exception e) {
			logger.error("probléme survenu lors de la création d'une note", e);
			throw new LibraryException();
		}

	}

	@Override
	public Note updateNote(Note note) throws LibraryException {
		try {
			note = noteDAO.update(note);

		} catch (Exception e) {
			logger.error("probléme survenu lors de l'update d'une note", e);
			throw new LibraryException();
		}

		return note;

	}

	@Override
	public void deleteNote(long userId, long bookId) throws LibraryException {
		
		Note note = noteDAO.findByUseridAndNodeId(userId, bookId);
		
		try {
			noteDAO.delete(note);

		} catch (Exception e) {
			logger.error("probléme survenu lors du delete d'une note", e);
			throw new LibraryException();
		}

	}

}
