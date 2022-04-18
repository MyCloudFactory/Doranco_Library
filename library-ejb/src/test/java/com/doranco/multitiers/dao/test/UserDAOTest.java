package com.doranco.multitiers.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.doranco.multitiers.dao.GenericDAO;
import com.doranco.multitiers.dao.UserDAO;
import com.doranco.multitiers.entity.User;

@RunWith(Arquillian.class)
public class UserDAOTest {

	@Deployment
	public static JavaArchive createDeployment() {
		// On incorpore les classes ci-contre dans l'archive de test
		return ShrinkWrap.create(JavaArchive.class).addClasses(UserDAO.class, GenericDAO.class);

	}

	@EJB
	private UserDAO dao;

	private static Logger logger = Logger.getLogger(UserDAOTest.class);

//	@Test
//	@InSequence(1)
	public void shouldCreateAUser() {
		try {

			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setFirstName("guillaume");
				user.setLastName("PORTAL");
				user.setUserName("gportal" + i);
				user.setPassword("gportal" + i);

				dao.create(user);
			}
		} catch (Exception e) {
			fail("ne devrait pas retourner d'exception");

		}

	}

//	@Test
//	@InSequence(2)
	public void shouldFindAUserWithAGoodPassword() {
		try {

			User connectedUser = dao.findByUserNameAndPassword("gportal1", "gportal1");

			assertNotNull(connectedUser);

		} catch (Exception e) {
			logger.error("Error = ", e);
			fail("ne devrait pas retourner d'exception lors de la récupération d'un user avec de bons identifiants");
		}

	}

//	@Test
//	@InSequence(3)
	public void shouldFindAUserWithABadPassword() {
		User user = null;
		try {

			user = dao.findByUserNameAndPassword("gportal", "bidon");

		} catch (EJBException nre) {

		}

		assertNull(user);

	}
	
	@Test
	@InSequence(4)
	public void shouldFindAListOfUser() {
		List<User> Luser = null;
		try {
				Luser = dao.findAll();

		} catch (Exception e) {

		}

		assertEquals(Luser.size(), 10);

	}
}
