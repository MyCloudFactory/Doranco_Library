package com.doranco.multitiers.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.entity.Order;
import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.utils.Page;

@Stateless
@LocalBean
public class UserDAO extends GenericDAO<User> {

	private static Logger logger = Logger.getLogger(UserDAO.class);

	public User findByUserNameAndPassword(String givenusername, String givenpassword) throws NoResultException {
		User user;
		String jpql = "SELECT u FROM User u WHERE u.userName = :TuserName AND u.password = :TpassWord";
		user = (User) em.createQuery(jpql).setParameter("TuserName", givenusername)
				.setParameter("TpassWord", givenpassword).getSingleResult();

		// hibernateInitialization(user);

		return user;
	}

	public User findUserbyid(long id) throws NoResultException {
		User user;
		String jpql = "SELECT u FROM User u WHERE u.id = :Tid";
		user = (User) em.createQuery(jpql).setParameter("Tid", id).getSingleResult();

		// hibernateInitialization(user);

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() throws Exception {
		List<User> users;
		String jpql = "SELECT u FROM User u";

		users = em.createQuery(jpql).getResultList();
		logger.debug("Utilisateur retourné = " + users.toString());
		/*
		 * for (User user : users) { hibernateInitialization(user); // permet de
		 * conserver cette relation aprés la fermeture de transaction(les // données
		 * sont dispo) }
		 */

		return users;

	}

	/*
	 * private void hibernateInitialization(User user) {
	 * Hibernate.initialize(user.getNotes()); //recupere explicitement les
	 * dependances(infos) sur les Notes, viewings, order
	 * Hibernate.initialize(user.getViewings()); //initialize une sorte de session
	 * permettant de recuperer specifiquement ces trois infos
	 * Hibernate.initialize(user.getOrder()); // POURQUOI: TOUT SIMPLEMENT PARCE QUE
	 * DANS LA CLASSE USER ON EST SUR CES TROIS ATTRIBUTS EN FETCH LAZY }
	 */
	public Page<User> find(int pageSize, int pageNumber) {

		String jpql = "SELECT u FROM User u";

		Query query = em.createQuery(jpql);
		query.setMaxResults(pageSize);
		query.setFirstResult(pageNumber * pageSize + 1);

		// recuperation nombre total de users
		long totalCount = (long) em.createQuery("SELECT count(u.id) FROM User u").getSingleResult();

		// recuperation d'une page utilisateur
		List<User> pageContent = query.getResultList();

		/*
		 * for (User user : pageContent) { hibernateInitialization(user); // permet de
		 * conserver cette relation aprés la fermeture de transaction(les // données
		 * sont dispo) }
		 */

		Page<User> page = new Page<User>(pageSize, pageNumber, totalCount, pageContent);

		return page;
	}
	

	public Page<Note> getNotes(long userId, int pageSize, int pageNumber) {

		Query query = em.createQuery("SELECT n FROM Note n WHERE n.user.id= :userId").setParameter("userId", userId)
				.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize);

		//récupération d'une page de note
		List<Note> pageContent = query.getResultList();
		
		long totalCount = (long) em.createQuery("SELECT count(*) FROM Note n WHERE n.user.id= :userId")
				.setParameter("userId", userId).getSingleResult();
		
		Page<Note> page = new Page<Note>(pageSize, pageNumber, totalCount, pageContent);

		return page;
		
	}
	
	
	public Page<Order> getOrders(long userId, int pageSize, int pageNumber) {

		Query query = em.createQuery("SELECT o FROM Order o WHERE o.user.id= :userId").setParameter("userId", userId)
				.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize);

		//récupération d'une page de order
		List<Order> pageContent = query.getResultList();
		
		long totalCount = (long) em.createQuery("SELECT count(*) FROM Order o WHERE o.user.id= :userId")
				.setParameter("userId", userId).getSingleResult();
		
		Page<Order> page = new Page<Order>(pageSize, pageNumber, totalCount, pageContent);

		return page;
		
	}
	

	public Page<Viewing> getViewings(long userId, int pageSize, int pageNumber) {

		Query query = em.createQuery("SELECT v FROM Viewing v WHERE v.user.id= :userId").setParameter("userId", userId)
				.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize);

		//récupération d'une page de viewing
		List<Viewing> pageContent = query.getResultList();
		
		long totalCount = (long) em.createQuery("SELECT count(*) FROM Viewing v WHERE v.user.id= :userId")
				.setParameter("userId", userId).getSingleResult();
		
		Page<Viewing> page = new Page<Viewing>(pageSize, pageNumber, totalCount, pageContent);

		return page;
		
	}
}
