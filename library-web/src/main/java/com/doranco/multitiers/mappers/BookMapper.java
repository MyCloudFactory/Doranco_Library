package com.doranco.multitiers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.utils.Page;
import com.doranco.multitiers.vuesmodeles.BookVM;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	BookVM entityToVM(Book entity); //pas besoin d'annotations car les nom des proprietes  de book et bookVM sont identiques
	
	@Mapping(target="id", ignore=true)
	Book VMToEntity(BookVM book);
	
	Page<BookVM> entitiesPageToVMsPage(Page<Book> page);
	
	List<BookVM> entitiesToVMs(List<Book> entities); // permet juste de traiter des ensembles de book qui ne fait que boucler sur la methode au-dessus
}

