package com.doranco.multitiers.ejb.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.doranco.multitiers.dao.BookDAO;
import com.doranco.multitiers.dao.ViewingDAO;
import com.doranco.multitiers.ejb.interfaces.IBook;
import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.exceptions.EntityNotFoundException;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.exceptions.ViewingNotFoundException;
import com.doranco.multitiers.utils.Page;

@Stateless
public class BookBean implements IBook {

	Logger logger = Logger.getLogger(BookBean.class);

	@EJB
	BookDAO bookDAO;

	@EJB
	ViewingDAO viewingDAO;

	@Override
	public Book createBook(Book book) throws LibraryException {

		try {
			book = bookDAO.create(book);

		} catch (Exception e) {
			logger.error("probléme survenu lors de l'inscription d'un livre", e);
			throw new LibraryException();
		}

		return book;
	}

	@Override
	public Book updateBook(Book book) throws LibraryException {

		try {
			book = bookDAO.update(book);

		} catch (Exception e) {
			logger.error("probléme survenu lors de la modification d'un livre", e);
			throw new LibraryException();
		}

		return book;
	}

	@Override
	public void deleteBookById(long id) throws LibraryException {

		try {
			Book book = findById(id);
			bookDAO.delete(book);

		} catch (Exception e) {
			logger.error("probléme survenu lors de l'effacement d'un livre", e);
			throw new LibraryException();
		}

	}

	@Override
	public Book findById(long id) throws LibraryException {

		Book book = null;
		try {
			book = bookDAO.findBookbyId(id);

			if (book == null)
				throw new EntityNotFoundException();

		} catch (Exception e) {
			logger.error("Id donné ne correspond à aucun livre", e);
			throw new LibraryException();
		}
		return book;
	}

	@Override
	public Book findById(Long idBook, Long idUser) throws LibraryException {

		Book book = null;
		Viewing viewing;

		if (idUser == null) {
			try {
				book = bookDAO.findBookbyId(idBook);

				if (idBook == null)
					book = bookDAO.findBookbyId(idBook);

			} catch (Exception e) {
				logger.error("Impossible de creer un livre", e);
				throw new LibraryException();
			}
			return book;

		} else {
			try {
				viewing = viewingDAO.findByUserIdAndBookId(idUser, idBook);

				if (viewing.getDuration() * 3600 * 1000 < (new Date().getTime() - viewing.getStartDate().getTime())) {

					ViewingNotFoundException vne = new ViewingNotFoundException();

					logger.error("date d'expiration dépassée", vne);

					throw vne;
				}
			}

			catch (EJBTransactionRolledbackException nre) {
				ViewingNotFoundException vne = new ViewingNotFoundException();
				logger.error("Impossible de trouver un tel accord de consultation", vne);
				throw vne;
			}

			book = viewing.getBook();
			return book;
		}
	}

	
	public Page<Book> findBookByTitle(String str, int pageSize, int pageNumber) throws LibraryException {

		Page<Book> books = null;

		try {
			books = bookDAO.findBookbyTitle(str, pageSize, pageNumber);

		} catch (Exception e) {
			logger.error("Impossible de trouver ce livre", e);
			throw new LibraryException();
		}
		return books;
	}

}
