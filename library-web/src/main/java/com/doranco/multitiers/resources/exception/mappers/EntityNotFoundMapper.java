package com.doranco.multitiers.resources.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import com.doranco.multitiers.exceptions.EntityNotFoundException;

public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

	Logger logger = Logger.getLogger(EntityNotFoundMapper.class);

	@Override
	public Response toResponse(EntityNotFoundException exception) {

		logger.error("Impossible de recuperer l'entité avec l'id passé en param", exception);
		
		return Response.status(Status.NOT_FOUND).header("Server Message", "Impossible de retrouver l'élément recherché").build();
	}

}
