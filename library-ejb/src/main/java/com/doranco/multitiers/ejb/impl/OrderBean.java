package com.doranco.multitiers.ejb.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.doranco.multitiers.dao.BookDAO;
import com.doranco.multitiers.dao.OrderDAO;
import com.doranco.multitiers.dao.OrderLineDAO;
import com.doranco.multitiers.dao.UserDAO;
import com.doranco.multitiers.ejb.interfaces.IOrder;
import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.entity.Order;
import com.doranco.multitiers.entity.OrderLine;
import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.exceptions.LibraryException;

@Stateless
public class OrderBean implements IOrder {

	@EJB
	OrderDAO orderDAO;

	@EJB
	UserDAO userDAO;

	@EJB
	OrderLineDAO orderLineDAO;

	@EJB
	BookDAO bookDAO;

	Logger logger = Logger.getLogger(OrderBean.class);

	@Override
	public Order create(Order order) throws LibraryException {
		try {
			// récupération de toutes les données du user correspondant à l'id renvoyé
			// depuis le client
			User user = userDAO.findUserbyid(order.getUser().getId());
			order.setUser(user);

			// enregistrement de ma commande
			Order createOrder = orderDAO.create(order);
			
			List<OrderLine> createdLines = new ArrayList<OrderLine>();

			// enregistrer les lignes de commandes à partir de la commande sus-enregistrée
			for (OrderLine line : order.getLines()) {

				// recuperation de toutes les données du book correspondant à l'id renvoyé
				// depuis le client
				Book book = bookDAO.findBookbyId(line.getBook().getId());
				line.setBook(book);
				line.setOrder(createOrder); // Seule une OrderLine est au courant de l'Order associé du point de vue
											// persistance des données (relation UniDirectionnelle JPA)
				// ATTENTION NE PAS CONFONDRE AVEC LE POINT DE VUE APPLICATIF, UN ORDER A
				// EVIDEMMENT UN LISTE D'ORDERLINE MAIS CETTE LISTE N'EST PAS PERSISTEE AVEC
				// L'ANNOTATION TRANSCIENT
				// enregistrement de la ligne de commande
				createdLines.add(orderLineDAO.create(line));
			}

			createOrder.setLines(order.getLines());

			return createOrder;

		} catch (Exception e) {

			logger.error("probleme lors de l'enregistrement d'une commande", e);
			throw new LibraryException(e);
		}

	}

}
