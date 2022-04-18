package com.doranco.multitiers.ejb.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import com.doranco.multitiers.ejb.interfaces.Panier;
import com.doranco.multitiers.exceptions.LibraryException;

@Stateless
public class PanierBean implements Panier {
	
	List<String> books = new ArrayList<String>(Arrays.asList("Twighlight","Harry potter","Ghost empire","games of thrones"));

	@Override
	public void addBook(String book) throws LibraryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remobeBook(String book) throws LibraryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getBooks() throws LibraryException {
		
		List<String> results = null;
		
		try {
			//va aller recuperer les livres de la BD
			results = books;
		
		} catch (Exception e) {
			//loguer la stack trace dans le fichier de log
			e.printStackTrace();
			throw new LibraryException("Probléme survenu dans la récupération des livres");			
		}
		
		return results;
	}
	
}
