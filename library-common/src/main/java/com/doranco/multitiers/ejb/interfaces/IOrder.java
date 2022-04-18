package com.doranco.multitiers.ejb.interfaces;

import javax.ejb.Remote;

import com.doranco.multitiers.entity.Order;
import com.doranco.multitiers.exceptions.LibraryException;

@Remote
public interface IOrder {

	public Order create(Order order) throws LibraryException;
		
		
}
