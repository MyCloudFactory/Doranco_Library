package com.doranco.multitiers.ejb.interfaces;

import javax.ejb.Remote;

import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.exceptions.LibraryException;

@Remote
public interface INote {

	public Note NotebyBookId(long id) throws LibraryException;

	public Note createNote(Note note) throws LibraryException;

	public Note updateNote(Note note) throws LibraryException;

	public void deleteNote(long userId, long bookId) throws LibraryException;
	
}
