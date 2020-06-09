package com.br.apiDivinaProvidencia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.br.apiDivinaProvidencia.documents.Order;

public interface OrderService {
	List<Order> findAll();

	Optional<Order> findById(String id);

	double findByOrderMonth(int orderMonth);

	List<Order> findByOpens();

	Order insert(Order order);

	Order updateOrder(Order order);

	void removeUser(String id);
}
