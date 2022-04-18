package com.doranco.multitiers.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.doranco.multitiers.exceptions.LibraryException;

@Remote
public interface Panier {

	public void addBook(String book) throws LibraryException;
	public void remobeBook(String book) throws LibraryException;
	
	public List<String> getBooks() throws LibraryException;
	
	
}
