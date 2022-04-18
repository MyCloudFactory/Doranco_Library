package com.doranco.multitiers.mappers;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.utils.Page;
import com.doranco.multitiers.vuesmodeles.BookVM;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-10T14:14:28+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookVM entityToVM(Book entity) {
        if ( entity == null ) {
            return null;
        }

        BookVM bookVM = new BookVM();

        bookVM.setId( entity.getId() );
        bookVM.setISBN( entity.getISBN() );
        bookVM.setTitle( entity.getTitle() );
        byte[] content = entity.getContent();
        if ( content != null ) {
            bookVM.setContent( Arrays.copyOf( content, content.length ) );
        }

        return bookVM;
    }

    @Override
    public Book VMToEntity(BookVM book) {
        if ( book == null ) {
            return null;
        }

        Book book1 = new Book();

        book1.setISBN( book.getISBN() );
        book1.setTitle( book.getTitle() );
        byte[] content = book.getContent();
        if ( content != null ) {
            book1.setContent( Arrays.copyOf( content, content.length ) );
        }

        return book1;
    }

    @Override
    public Page<BookVM> entitiesPageToVMsPage(Page<Book> page) {
        if ( page == null ) {
            return null;
        }

        Page<BookVM> page1 = new Page<BookVM>();

        page1.setPAGESIZE( page.getPAGESIZE() );
        page1.setPageNumber( page.getPageNumber() );
        page1.setContent( entitiesToVMs( page.getContent() ) );
        page1.setTotalCount( page.getTotalCount() );

        return page1;
    }

    @Override
    public List<BookVM> entitiesToVMs(List<Book> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BookVM> list = new ArrayList<BookVM>( entities.size() );
        for ( Book book : entities ) {
            list.add( entityToVM( book ) );
        }

        return list;
    }
}
