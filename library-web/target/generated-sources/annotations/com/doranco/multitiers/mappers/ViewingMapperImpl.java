package com.doranco.multitiers.mappers;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.vuesmodeles.ViewingVM;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-10T14:14:28+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class ViewingMapperImpl implements ViewingMapper {

    @Override
    public ViewingVM entityToVM(Viewing entity) {
        if ( entity == null ) {
            return null;
        }

        ViewingVM viewingVM = new ViewingVM();

        long id = entityBookId( entity );
        viewingVM.setIdBook( id );
        viewingVM.setStartDate( entity.getStartDate() );
        viewingVM.setDuration( entity.getDuration() );

        return viewingVM;
    }

    @Override
    public Viewing vmToEntity(ViewingVM vm) {
        if ( vm == null ) {
            return null;
        }

        Viewing viewing = new Viewing();

        viewing.setStartDate( vm.getStartDate() );
        viewing.setDuration( vm.getDuration() );

        return viewing;
    }

    @Override
    public List<ViewingVM> entitiesToVMs(List<Viewing> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ViewingVM> list = new ArrayList<ViewingVM>( entities.size() );
        for ( Viewing viewing : entities ) {
            list.add( entityToVM( viewing ) );
        }

        return list;
    }

    private long entityBookId(Viewing viewing) {
        if ( viewing == null ) {
            return 0L;
        }
        Book book = viewing.getBook();
        if ( book == null ) {
            return 0L;
        }
        long id = book.getId();
        return id;
    }
}
