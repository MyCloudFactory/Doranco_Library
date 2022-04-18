package com.doranco.multitiers.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.Identifiant;
import com.doranco.multitiers.vuesmodeles.CredentialVM;

public interface CredentialMapper {

	CredentialMapper INSTANCE = Mappers.getMapper(CredentialMapper.class);

	@Mappings({@Mapping(source = "book.id", target = "idBook"), @Mapping(source = "user.id", target = "idUser")})	
	CredentialVM entityToVM(Identifiant identifiant);
}
