package com.doranco.multitiers.ejb.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;

import org.apache.log4j.Logger;

import com.doranco.multitiers.dao.UserDAO;
import com.doranco.multitiers.dao.ViewingDAO;
import com.doranco.multitiers.ejb.interfaces.IViewing;
import com.doranco.multitiers.entity.Viewing;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.exceptions.UserNotFoundException;
import com.doranco.multitiers.exceptions.ViewingNotFoundException;

public class ViewingBean implements IViewing{

	Logger logger = Logger.getLogger(UserBean.class);

	@EJB
	ViewingDAO viewingDAO;
	
	@Override
	public Viewing create(Viewing viewing) throws LibraryException {
		try {
			viewing = viewingDAO.create(viewing);
		} catch (Exception e) {
			logger.error("Impossible d'enregistrer l'accord de consultation", e);
			throw new LibraryException();
		}
		
		return viewing;
	}

	@Override
	public Viewing update(Viewing viewing) throws LibraryException {
		try {
			viewing = viewingDAO.update(viewing);
		} catch (Exception e) {
			logger.error("Impossible de mettre à jour l'accord de consultation", e);
			throw new LibraryException();
		}
		
		return viewing;
	}

	@Override
	public void delete(Viewing viewing) throws LibraryException {
		try {
			viewingDAO.delete(viewing);
		} catch (Exception e) {
			logger.error("Probleme survenu lors de la suppression de l'accord de consultation", e);
			throw new LibraryException();
		}
		
	}

	@Override
	public Viewing find(long idUser, long idBook) throws LibraryException {
		try {
			Viewing viewing = viewingDAO.findByUserIdAndBookId(idUser, idBook);
			
			if(viewing.getDuration() * 3600*1000 < (new Date().getTime() - viewing.getStartDate().getTime())) {
				
				ViewingNotFoundException vne = new ViewingNotFoundException();
				
				logger.error("date d'expiration dépassée", vne);
			
				throw vne;
			}
			return viewing;
			
		} catch (EJBTransactionRolledbackException nre) {
			UserNotFoundException une = new UserNotFoundException(nre);
			logger.error("Impossible de trouver un tel accord de consultation", une);
			throw une;
			
		} catch (Exception e) {
			logger.error("Probleme survenu lors de la suppression de l'accord de consultation", e);
			throw new LibraryException();
		}
	}

}
