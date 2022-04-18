package com.doranco.multitiers.ejb.interfaces;

import javax.ejb.Remote;

import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.exceptions.LibraryException;

@Remote
public interface IViewing {

	public Viewing create(Viewing viewing) throws LibraryException;
	
	public Viewing update(Viewing viewing) throws LibraryException;
	
	public void delete(Viewing viewing) throws LibraryException;
	
	public Viewing find(long idUser, long idBook) throws LibraryException;
}
