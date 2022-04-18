package com.doranco.multitiers.resources.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.doranco.multitiers.exceptions.UserNotFoundException;

@Provider
public class UserNotFoundMapper implements ExceptionMapper<UserNotFoundException> {

	Logger logger = Logger.getLogger(UserNotFoundMapper.class);
	
	@Override
	public Response toResponse(UserNotFoundException exception) {
		logger.error("Impossible de retrouver un user avec ces identifiants", exception);
		
		return Response.status(Status.UNAUTHORIZED).header("Server Message", "Nom d'utilisateur ou mot de passe incorrect").build();
	}

}
;