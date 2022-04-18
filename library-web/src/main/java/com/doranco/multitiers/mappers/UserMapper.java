package com.doranco.multitiers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.utils.Page;
import com.doranco.multitiers.vuesmodeles.UserVM;

@Mapper(uses= {NoteMapper.class,OrderMapper.class,ViewingMapper.class})
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target="password", ignore=true) // on ne veut pas transmettre le password lors du mapping entity to VM
	UserVM entityToVM(User user);
	
	List<UserVM> entitiesToVMs(List<User> user);
	
	Page<UserVM> entitiesPageToVMsPage(Page<User> page);
	
	@Mapping(target="id", ignore=true)
	User VMToEntity(UserVM user);
}

