package com.doranco.multitiers.ejb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.exceptions.UserNotFoundException;
import com.doranco.multitiers.utils.Page;

@Remote
public interface IUser {

	
	public User suscribe(User user) throws LibraryException;
	
	public String connect(String username, String password, String string) throws LibraryException, UserNotFoundException;

	public List<User> getAll() throws LibraryException;
	
	public Page<User> find(int pageSize, int pageNumber) throws LibraryException;
	
	public User getUser(long id) throws LibraryException;
	
	public User setAsAdmin(long id) throws LibraryException;
	
}
