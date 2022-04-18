package com.doranco.multitiers.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.vuesmodeles.ViewingVM;

@Mapper
public interface ViewingMapper {
	
	ViewingMapper INSTANCE  = Mappers.getMapper(ViewingMapper.class);
	
	@Mappings({@Mapping(source="book.id", target="idBook"), @Mapping(source="user.id", target="idUser")})
	ViewingVM entityToVM(Viewing entity);
	
	@InheritInverseConfiguration //permet de recuperer l'annotation ci-dessus et d'inverser target et source
	Viewing vmToEntity(ViewingVM vm);
	
	List<ViewingVM> entitiesToVMs(List<Viewing> entities);

}
