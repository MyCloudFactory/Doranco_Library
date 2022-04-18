package com.doranco.multitiers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.OrderLine;
import com.doranco.multitiers.vuesmodeles.OrderLineVM;

@Mapper
public interface OrderLineMapper {
	OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

	@Mapping(source = "idBook", target = "book.id")
	OrderLine vmToEntity(OrderLineVM vm);
	
	@Mapping(source = "book.id", target = "idBook")
	OrderLineVM entityToVM(OrderLine entity);
	
	List<OrderLineVM> entitiesToVMs(List<OrderLine> entities);
	
	

}
