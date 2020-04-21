package com.br.apiDivinaProvidencia.services;

import java.util.List;

import com.br.apiDivinaProvidencia.documents.OrderIten;


public interface OrderItenService {
	List<OrderIten> findAll();
	OrderIten findById(String id);
	OrderIten insert (OrderIten orderIten);
	OrderIten updateUser(OrderIten orderIten);
	void removeUser(String id);
}
