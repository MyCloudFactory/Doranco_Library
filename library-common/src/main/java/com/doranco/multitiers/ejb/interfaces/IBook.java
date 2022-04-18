package com.doranco.multitiers.ejb.interfaces;

import javax.ejb.Remote;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.utils.Page;

@Remote
public interface IBook {

	public Book findById(long id) throws LibraryException;
	
	public Book findById(Long idBook, Long IdUser) throws LibraryException;

	public Page<Book> findBookByTitle(String str, int pageSize, int pageNumber) throws LibraryException;

	public Book createBook(Book book) throws LibraryException;

	public Book updateBook(Book book) throws LibraryException;

	public void deleteBookById(long id) throws LibraryException;

}
