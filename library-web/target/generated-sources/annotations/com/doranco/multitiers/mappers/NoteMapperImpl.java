package com.doranco.multitiers.mappers;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.vuesmodeles.NoteVM;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-10T14:14:28+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteVM entityToVM(Note entity) {
        if ( entity == null ) {
            return null;
        }

        NoteVM noteVM = new NoteVM();

        long id = entityUserId( entity );
        noteVM.setIdUser( id );
        long id1 = entityBookId( entity );
        noteVM.setIdBook( id1 );
        noteVM.setValue( entity.getValue() );
        noteVM.setComment( entity.getComment() );
        noteVM.setNoteDate( entity.getNoteDate() );

        return noteVM;
    }

    @Override
    public Note VMToEntity(NoteVM note) {
        if ( note == null ) {
            return null;
        }

        Note note1 = new Note();

        note1.setBook( noteVMToBook( note ) );
        note1.setUser( noteVMToUser( note ) );
        note1.setValue( note.getValue() );
        note1.setComment( note.getComment() );

        return note1;
    }

    @Override
    public List<NoteVM> entitiesToVMs(List<Note> entities) {
        if ( entities == null ) {
            return null;
        }

        List<NoteVM> list = new ArrayList<NoteVM>( entities.size() );
        for ( Note note : entities ) {
            list.add( entityToVM( note ) );
        }

        return list;
    }

    private long entityUserId(Note note) {
        if ( note == null ) {
            return 0L;
        }
        User user = note.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private long entityBookId(Note note) {
        if ( note == null ) {
            return 0L;
        }
        Book book = note.getBook();
        if ( book == null ) {
            return 0L;
        }
        long id = book.getId();
        return id;
    }

    protected Book noteVMToBook(NoteVM noteVM) {
        if ( noteVM == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( noteVM.getIdBook() );

        return book;
    }

    protected User noteVMToUser(NoteVM noteVM) {
        if ( noteVM == null ) {
            return null;
        }

        User user = new User();

        user.setId( noteVM.getIdUser() );

        return user;
    }
}
