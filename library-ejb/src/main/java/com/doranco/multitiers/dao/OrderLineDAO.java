package com.doranco.multitiers.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.doranco.multitiers.entity.OrderLine;

@Stateless
@LocalBean
public class OrderLineDAO extends GenericDAO<OrderLine> {
	

}
