package com.doranco.multitiers.resources.exception.mappers;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider //indique au moteur jax-rs les classes auxquelles il doit s'interesser.
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {

	
	Logger logger = Logger.getLogger(NotAuthorizedExceptionMapper.class);

	@Override
	public Response toResponse(NotAuthorizedException exception) {

		logger.error("Accés à la ressource refusé", exception);
		
		return Response.status(Status.UNAUTHORIZED).header("Server Message", "Accés  à la ressource refusé").build();
	}
}
