package com.doranco.multitiers.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.doranco.multitiers.utils.Page;

public class GenericDAO<T> {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	EntityManager em = emf.createEntityManager();

	public EntityTransaction transaction = em.getTransaction();

	public T create(T toBeCreated) throws Exception {

		transaction.begin();
		// em.persist(toBeCreated);
		em.merge(toBeCreated); // identique mais conserve certaines references en base de données
		transaction.commit();
		return toBeCreated;
	}

	public T update(T toBeUpdated) throws Exception {

		transaction.begin();
		em.merge(toBeUpdated);
		transaction.commit();
		return toBeUpdated;
	}

	public void delete(T toBeDeleted) throws Exception {

		transaction.begin();
		em.remove(toBeDeleted);
		transaction.commit();
	}

	public T find(Class<T> clazz, Integer primaryKey) throws Exception {

		return (T) em.find(clazz, primaryKey);
	}
	
}
