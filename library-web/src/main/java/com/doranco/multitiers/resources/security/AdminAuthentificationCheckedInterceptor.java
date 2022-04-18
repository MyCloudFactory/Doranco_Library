package com.doranco.multitiers.resources.security;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;

import com.doranco.multitiers.LibraryConstants;
import com.doranco.multitiers.utils.KeyGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AdminAuthentificationCheckedInterceptor extends AuthentificationCheckedInterceptor {

	Logger logger = Logger.getLogger(AdminAuthentificationCheckedInterceptor.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		super.filter(requestContext);

		// Recuperation des headers de la requête
		String autorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		String token = autorizationHeader.substring("Bearer".length()).trim();

		Key key;

		try {
			key = KeyGenerator.getInstance().getKey();

			Claims jwtBody = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // on a defini une cle
																								// pour
																								// les verifications
																								// futures
																								// qui est stockee dans
																								// le
																								// singleton
																								// keygenerator
																								// puis verif via le
																								// parseClaimsJws


			@SuppressWarnings("unchecked")
			List<String> roles = (List<String>) jwtBody.get(LibraryConstants.JWT_ROLE_KEY);

			if (roles.equals(LibraryConstants.ADMIN_ROLE) && !roles.contains(LibraryConstants.SUPER_ADMIN_ROLE)) {

				logger.info("Role invalide");
				throw new NotAuthorizedException("la liste de rôle ne contient pas le role administrateur");
			}
		} catch (Exception e) {
			logger.error("erreur lors de la validation du token", e);

		}

		logger.info("token valide :" + token);
	}
}
