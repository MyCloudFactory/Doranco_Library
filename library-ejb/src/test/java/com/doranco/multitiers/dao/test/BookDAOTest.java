package com.doranco.multitiers.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.doranco.multitiers.dao.BookDAO;
import com.doranco.multitiers.dao.GenericDAO;
import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.utils.Page;

@RunWith(Arquillian.class)
public class BookDAOTest {

	
	
	@Deployment
	public static JavaArchive createDeployment() {
		// On incorpore les classes ci-contre dans l'archive de test
		return ShrinkWrap.create(JavaArchive.class).addClasses(BookDAO.class, GenericDAO.class);

	}

	@EJB
	private BookDAO dao;

	
	/*@InSequence(1)
	public void shouldCreateBooks() {
		try {

			for (int i = 2; i < 10; i++) {
				Book book = new Book();
				book.setISBN("45269689" + i + "X");
				book.setTitle("La suite " + i);

				dao.create(book);
			}
		} catch (Exception e) {
			fail("Creation book: ne devrait pas retourner d'exception");
		}

		List<Book> Lbook = null;
		
		try {
			Lbook = dao.findAllBooks();
		} catch (Exception e) {
			fail("Listing book : ne devrait pas retourner d'exception");
			e.printStackTrace();
		}
		assertEquals(Lbook.size(), 8);

	}*/
	
	@Test
	@InSequence(1)
	public void shouldRetrieveBook() {
		
		Book book = null;
		try {

			book = dao.findBookbyId(25);

		} catch (EJBException e) {

		}

		assertNotNull(book);
		
	}
	
	@Test
	@InSequence(2)
	public void shouldRetrieveBooks() {
		
		Page<Book> books = null;
		try {

			books = dao.findBookbyTitle("suite",10,1);

		} catch (EJBException e) {

		}

		assertEquals(books.getContent().size(), 8);
		
	}
	
	
	
	
	
	
	
	
}
