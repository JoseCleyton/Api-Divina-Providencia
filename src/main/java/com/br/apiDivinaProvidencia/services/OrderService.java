package com.br.apiDivinaProvidencia.services;

import java.util.List;


import com.br.apiDivinaProvidencia.documents.Order;

public interface OrderService {
	List<Order> findAll();
	Order findById(String id);
	Order insert (Order order);
	Order updateOrder(Order order);
	void removeUser(String id);
}
