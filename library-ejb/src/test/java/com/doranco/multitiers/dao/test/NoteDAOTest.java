package com.doranco.multitiers.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

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

import com.doranco.multitiers.dao.BookDAO;
import com.doranco.multitiers.dao.GenericDAO;
import com.doranco.multitiers.dao.NoteDAO;
import com.doranco.multitiers.dao.UserDAO;
import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.entity.User;

@RunWith(Arquillian.class)
public class NoteDAOTest {

	Logger logger = Logger.getLogger(NoteDAOTest.class);

	@Deployment
	public static JavaArchive createDeployment() {
		// On incorpore les classes ci-contre dans l'archive de test
		return ShrinkWrap.create(JavaArchive.class).addClasses(NoteDAO.class, GenericDAO.class, BookDAO.class);

	}

	@EJB
	private NoteDAO noteDAO;
	@EJB
	private BookDAO bookDAO;
	@EJB
	private UserDAO userDAO;

	@Test
	@InSequence(1)
	public void shouldRetrieveANoteAndGoodComment() {

		Note note = null;
		try {

			note = noteDAO.NotebyBookId(bookDAO.findBookbyId(25));

			// logger.info("contenu note: " + note.toString());

		} catch (EJBException e) {

		}

		assertNotNull(note);
		assertEquals(note.getComment(), "livre interessant");

	}

	@Test
	@InSequence(2)
	public final void shouldCreateANoteFromExistingUserAndBook() {

		try {

			User noter = userDAO.findUserbyid(8);
			Book noted = bookDAO.findBookbyId(1);

			Note note = new Note();
			note.setBook(noted);
			note.setUser(noter);
			note.setValue(4);
			note.setComment("Scénario et avneture sont au RDV");
			note.setNoteDate(new Date());
			noteDAO.create(note);

		} catch (Exception e) {
			// TODO: handle exception
			fail("shouldCreateANoteFromExistingUserAndBook ne devarit pas retourner d'exception");
		}

	}

	@Test
	@InSequence(3)
	public final void shouldUpdateANote() {

		try {

			Note note = noteDAO.findByUseridAndNodeId(8, 1);
			note.setComment("Scenario interessant");
			note = noteDAO.update(note);

			assertEquals(note.getComment(), "Scenario interessant");
		} catch (Exception e) {
			// TODO: handle exception
			fail("shouldUpdateANote ne devarit pas retourner d'exception");
		}
	}

}
