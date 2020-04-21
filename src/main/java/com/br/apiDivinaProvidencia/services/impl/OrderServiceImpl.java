package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.Order;
import com.br.apiDivinaProvidencia.repository.OrderRepository;
import com.br.apiDivinaProvidencia.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		return this.orderRepository.findAll();
	}

	@Override
	public Order findById(String id) {
		return null;
	}

	@Override
	public Order insert(Order order) {
		return this.orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		return this.orderRepository.save(order);
	}

	@Override
	public void removeUser(String id) {
		this.orderRepository.deleteById(id);
	}

}
