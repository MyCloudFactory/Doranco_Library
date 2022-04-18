package com.doranco.multitiers.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.utils.Page;

@Stateless
@LocalBean
public class BookDAO extends GenericDAO<Book> {

	public Book findBookbyId(long id) throws NoResultException {
		Book book;
		String jpql = "SELECT u FROM Book u WHERE u.id = :Tid";
		book = (Book) em.createQuery(jpql).setParameter("Tid", id).getSingleResult();

		//Ce commentaire ci-dessous n'a plus lieu d'être(gardé car instructif) car on a supprimé les relations bidirectionnelles de book
		// hibernateInitialization(book); // PAS BESOIN D'OUVERTURE DE SESSION
		// HIBERNATEINITIALIZATION CAR LES DEUX SEULS ATTRIBUTS
		// QU'ON SOUHAITE RECUPERER (ISBN ET TITLE) NE SONT PAS EN FETCH LAZY
		return book;
	}

	/*@SuppressWarnings("unchecked")
	public List<Book> findBookbyTitle(String str) throws NoResultException {
		List<Book> books;
		String jpql = "SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',:Ttitle,'%')";
		books = em.createQuery(jpql).setParameter("Ttitle", str).getResultList();

		//Ce commentaire ci-dessous n'a plus lieu d'être(gardé car instructif) car on a supprimé les relations bidirectionnelles de book
		// hibernateInitialization(book); // PAS BESOIN D'OUVERTURE DE SESSION
		// HIBERNATEINITIALIZATION CAR LES DEUX SEULS ATTRIBUTS
		// QU'ON SOUHAITE RECUPERER (ISBN ET TITLE) NE SONT PAS EN FETCH LAZY
		return books;
	}*/

	
	@SuppressWarnings("unchecked")
	public Page<Book> findBookbyTitle(String queryString, int pageSize, int pageNumber) throws NoResultException {

		String jpql = "SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',:Ttitle,'%') OR b.ISBN LIKE CONCAT('%',:Ttitle,'%')";
		Query query = em.createQuery(jpql).setParameter("Ttitle", queryString).setMaxResults(pageSize).setFirstResult(pageNumber * pageSize);

		//Ce commentaire ci-dessous n'a plus lieu d'être(gardé car instructif) car on a supprimé les relations bidirectionnelles de book
		// hibernateInitialization(book); // PAS BESOIN D'OUVERTURE DE SESSION
		// HIBERNATEINITIALIZATION CAR LES DEUX SEULS ATTRIBUTS
		// QU'ON SOUHAITE RECUPERER (ISBN ET TITLE) NE SONT PAS EN FETCH LAZY
		List<Book> pageContent = query.getResultList();
		
		long totalCount = (long) em.createQuery("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',:Ttitle,'%') OR b.ISBN LIKE CONCAT('%',:Ttitle,'%')")
				.setParameter("Ttitle", queryString).getSingleResult();
		
		Page<Book> books = new Page<Book>(pageSize, pageNumber, totalCount, pageContent);
		
		return books;
	}
	
	
	public Page<Note> getNotes(long bookId, int pageSize, int pageNumber) {

		Query query = em.createQuery("SELECT n FROM Note n WHERE n.book.id= :bookId").setParameter("bookId", bookId)
				.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize);

		//récupération d'une page de note
		List<Note> pageContent = query.getResultList();
		
		long totalCount = (long) em.createQuery("SELECT count(n.id) FROM Note n WHERE n.book.id= :bookId")
				.setParameter("bookId", bookId).getSingleResult();
		
		Page<Note> page = new Page<Note>(pageSize, pageNumber, totalCount, pageContent);

		return page;
		
	}
	
	public Page<Viewing> getViewings(long bookId, int pageSize, int pageNumber) {

		Query query = em.createQuery("SELECT v FROM Viewing v WHERE v.book.id= :bookId").setParameter("bookId", bookId)
				.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize);

		//récupération d'une page de viewing
		List<Viewing> pageContent = query.getResultList();
		
		long totalCount = (long) em.createQuery("SELECT count(v.id) FROM Viewing v WHERE v.book.id= :bookId")
				.setParameter("bookId", bookId).getSingleResult();
		
		Page<Viewing> page = new Page<Viewing>(pageSize, pageNumber, totalCount, pageContent);

		return page;
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() throws Exception {
		List<Book> books;
		String jpql = "SELECT u FROM Book u";

		books = em.createQuery(jpql).getResultList();
		/*
		 * for (Book book : books) { hibernateInitialization(book); // permet de
		 * conserver cette relation aprés la fermeture de transaction(les // données
		 * sont dispo) }
		 */

		return books;

	}
	
	

}
