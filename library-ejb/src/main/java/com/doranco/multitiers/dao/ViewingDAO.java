package com.doranco.multitiers.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.exceptions.LibraryException;

@Stateless
@LocalBean
public class ViewingDAO extends GenericDAO<Viewing> {

	public Viewing findByUserIdAndBookId(long idUser, long idBook) throws LibraryException {
		
		Viewing viewing = null;
			
		Query query = em.createQuery("SELECT v FROM Viewing v WHERE v.user.id= :idUser AND v.book.id= :idBook")
				.setParameter("idUser", idUser).setParameter("idBook", idBook);
		
		viewing = (Viewing) query.getSingleResult();
		
		return viewing;
	}
}
