package com.doranco.multitiers.dao.test;

import static org.junit.Assert.fail;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class DAOTest {

	@Test
	public void shouldGenerateDataBase() {
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
			emf.createEntityManager();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("erreur lors de l'initialisation d'hibernate");
		}
	}
	
}
