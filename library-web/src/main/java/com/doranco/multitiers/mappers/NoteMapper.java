package com.doranco.multitiers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.vuesmodeles.NoteVM;

@Mapper
public interface NoteMapper {

	NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

	@Mappings({ @Mapping(source = "book.id", target = "idBook"), @Mapping(source = "user.id", target = "idUser") })
	NoteVM entityToVM(Note entity);
	
	@Mappings({ @Mapping(source = "idBook", target = "book.id"), @Mapping(source = "idUser", target = "user.id"), @Mapping(target = "noteDate", ignore=true) })	
	Note VMToEntity(NoteVM note);
	
	List<NoteVM> entitiesToVMs(List<Note> entities); //methode de traitement d'ensemble qui ne fait que boucler sur la methode ci-dessus

}
