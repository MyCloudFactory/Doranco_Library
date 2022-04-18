package com.doranco.multitiers.resources.security;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;

import com.doranco.multitiers.utils.KeyGenerator;

import io.jsonwebtoken.Jwts;

//permet d'agir sur les réponses avant qu'elles ne soient envoyées au client
public class AuthentificationCheckedInterceptor implements ContainerRequestFilter {

	Logger logger = Logger.getLogger(AuthentificationCheckedInterceptor.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Recuperation des headers de la requête
		String autorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (autorizationHeader == null || !autorizationHeader.startsWith("Bearer ")) {

			// logger.error("Authorization header invalide");
			System.out.println("Authorization header invalide");

			throw new NotAuthorizedException("L'entete authorization devrait etre fournie ");
		}

		String token = autorizationHeader.substring("Bearer".length()).trim();

		Key key;

		try {
			key = KeyGenerator.getInstance().getKey();
			Jwts.parser().setSigningKey(key).parseClaimsJws(token); // on a defini une cle pour les verifications
																	// futures qui est stockee dans le singleton
																	// keygenerator puis verif via le parseClaimsJws
		} catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException | CertificateException e) {
			// logger.error("erreur lors de la validation du token", e);
			System.out.println("\"erreur lors de la validation du token:");
			e.printStackTrace();
		}

		logger.info("token valide :" + token);

	}

}
