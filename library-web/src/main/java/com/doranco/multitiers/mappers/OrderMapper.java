package com.doranco.multitiers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.Order;
import com.doranco.multitiers.vuesmodeles.OrderVM;

@Mapper(uses=OrderLineMapper.class) //dans orderline on a une liste d'orderlineVM
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	@Mapping(source = "user.id", target = "idUser")
	OrderVM entityToVm(Order entity);

//	@Mapping(source = "book.id", target = "idBook")
//	OrderLineVM entityToVm(OrderLine entity);
	
	@Mappings({ @Mapping(source = "idUser", target = "user.id"), @Mapping(target = "id", ignore=true) })
	Order vmToEntity(OrderVM vm);
	
	List<OrderVM> entitiesToVMs(List<Order> entities);

	List<Order> vmsToEntities(List<OrderVM> vms);
	
}
