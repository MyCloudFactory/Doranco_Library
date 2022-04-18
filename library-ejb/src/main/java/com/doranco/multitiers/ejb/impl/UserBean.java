package com.doranco.multitiers.ejb.impl;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.doranco.multitiers.LibraryConstants;
import com.doranco.multitiers.dao.UserDAO;
import com.doranco.multitiers.ejb.interfaces.IUser;
import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.exceptions.EntityNotFoundException;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.exceptions.UserNotFoundException;
import com.doranco.multitiers.utils.KeyGenerator;
import com.doranco.multitiers.utils.Page;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Stateless
public class UserBean implements IUser {

	Logger logger = Logger.getLogger(UserBean.class);

	@EJB
	UserDAO userDAO;

	@Override
	public User suscribe(User user) throws LibraryException {

		try {

			user = userDAO.create(user);

		} catch (Exception e) {
			logger.error("probléme survenu lors de l'inscription d'un utilisateur", e);
			throw new LibraryException();
		}

		return user;
	}

	@Override
	public String connect(String username, String password, String uri) throws LibraryException, UserNotFoundException {

		String token = null;
		User user = null;

		try {
			user = userDAO.findByUserNameAndPassword(username, password);

			token = issueToken(username, uri, user.isAdmin(), user.isSuperAdmin());

		} catch (EJBTransactionRolledbackException nre) {
			UserNotFoundException une = new UserNotFoundException(nre);
			logger.error("Impossible de trouver un tel utilisateur", une);
			throw une;

		} catch (Exception e) {
			logger.error("Probléme lors de la connection d'un utilisateur", e);
			throw new LibraryException();
		}

		return token;
	}

	@Override
	public List<User> getAll() throws LibraryException {

		List<User> listUser = null;

		try {
			listUser = userDAO.findAll();

		} catch (Exception e) {
			logger.error("Probléme lors de la recuperation de la liste de user", e);
			throw new LibraryException();
		}

		return listUser;
	}

	@Override
	public Page<User> find(int pageSize, int pageNumber) throws LibraryException {

		Page<User> users = null;

		try {
			users = userDAO.find(pageSize, pageNumber);

		} catch (Exception e) {
			logger.error("Probléme lors de la recuperation de la liste de user", e);
			throw new LibraryException();
		}

		return users;
	}

	private String issueToken(String login, String uriInfo, boolean setAdminClaim, boolean setSuperAdminClaim)
			throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException,
			IOException {

		logger.debug("URIINFO ==> " + uriInfo);

		Key key = KeyGenerator.getInstance().getKey();

		JwtBuilder jwtBuilder = Jwts.builder().setSubject(login).setIssuer(uriInfo) // uriInfo uri de la ressource qui
																					// delivre
				// le token

				.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L))); // setexpiration:
																										// temps max de connection
		List<String> roles = new ArrayList<String>();
		
		if (setAdminClaim)
			roles.add(LibraryConstants.ADMIN_ROLE);

		if (setSuperAdminClaim)
			roles.add(LibraryConstants.SUPER_ADMIN_ROLE);
		
			jwtBuilder.claim(LibraryConstants.JWT_ROLE_KEY, roles);

		String jwtToken = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

		logger.info("Generation du token : " + jwtToken + " - " + key);

		return jwtToken;
	}

	private Date toDate(LocalDateTime localDateTime) {

		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

	}

	@Override
	public User getUser(long id) throws LibraryException {
		User user = null;
		try {
			user = userDAO.findUserbyid(id);

			if (user == null)
				throw new EntityNotFoundException();

		} catch (Exception e) {
			logger.error("Impossible de trouver un tel utilisateur", e);
			throw new LibraryException();
		}
		return user;
	}

	@Override
	public User setAsAdmin(long id) throws LibraryException {
		try {
			User user = userDAO.findUserbyid(id);
			user.setAdmin(true);
			userDAO.update(user);

			return user;
		} catch (Exception e) {
			logger.error("Probléme lors de la mise à jour d'un administrateur", e);
			throw new LibraryException();
		}

	}

}
