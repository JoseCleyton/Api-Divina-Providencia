package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public Optional<Order> findById(String id) {
		return this.orderRepository.findById(id);
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

	@Override
	public double findByOrderMonth(int orderMonth) {
		return this.orderRepository.findAll().stream().filter(o -> o.getOrderMonth() == orderMonth)
				.mapToDouble(o -> o.getOrderValue()).sum();

	}

	@Override
	public List<Order> findByOpens() {
		return this.orderRepository.findAll().stream()
				.filter(o -> o.getStatus().equalsIgnoreCase("aberto"))
				.collect(Collectors.toList());
	}

}
