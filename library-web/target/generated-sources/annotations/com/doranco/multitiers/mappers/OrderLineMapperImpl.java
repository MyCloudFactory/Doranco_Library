package com.doranco.multitiers.mappers;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.OrderLine;
import com.doranco.multitiers.vuesmodeles.OrderLineVM;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-10T14:14:28+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class OrderLineMapperImpl implements OrderLineMapper {

    @Override
    public OrderLine vmToEntity(OrderLineVM vm) {
        if ( vm == null ) {
            return null;
        }

        OrderLine orderLine = new OrderLine();

        orderLine.setBook( orderLineVMToBook( vm ) );

        return orderLine;
    }

    @Override
    public OrderLineVM entityToVM(OrderLine entity) {
        if ( entity == null ) {
            return null;
        }

        OrderLineVM orderLineVM = new OrderLineVM();

        long id = entityBookId( entity );
        orderLineVM.setIdBook( id );

        return orderLineVM;
    }

    @Override
    public List<OrderLineVM> entitiesToVMs(List<OrderLine> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrderLineVM> list = new ArrayList<OrderLineVM>( entities.size() );
        for ( OrderLine orderLine : entities ) {
            list.add( entityToVM( orderLine ) );
        }

        return list;
    }

    protected Book orderLineVMToBook(OrderLineVM orderLineVM) {
        if ( orderLineVM == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( orderLineVM.getIdBook() );

        return book;
    }

    private long entityBookId(OrderLine orderLine) {
        if ( orderLine == null ) {
            return 0L;
        }
        Book book = orderLine.getBook();
        if ( book == null ) {
            return 0L;
        }
        long id = book.getId();
        return id;
    }
}
