package com.br.apiDivinaProvidencia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apiDivinaProvidencia.documents.Order;
import com.br.apiDivinaProvidencia.documents.OrderIten;
import com.br.apiDivinaProvidencia.responses.Response;
import com.br.apiDivinaProvidencia.services.CashierService;
import com.br.apiDivinaProvidencia.services.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		return ResponseEntity.ok(this.orderService.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Order>> findById(@PathVariable(name = "id") String id) {
		List<Order> orders = this.orderService.findAll();
		Response<Order> responseError = new Response<>();
		for (Order order : orders) {
			if (order.getId().equals(id)) {
				return ResponseEntity.ok(new Response<Order>(order));
			}
		}
		responseError.setErrors("ID Inválido ou inexixstente");
		return ResponseEntity.badRequest().body((new Response<Order>(responseError.getErrors())));
	}

	@GetMapping(path = "/month/{number}")
	public ResponseEntity<Double> findMonth(@PathVariable(name = "number") int number) {
		List<Order> orders = this.orderService.findAll();
		double valueOrderMonth = 0;
		for (Order order : orders) {
			if (order.getOrderMonth() == number) {
				valueOrderMonth += order.getOrderValue();
			}
		}
		return ResponseEntity.ok(valueOrderMonth);
	}

	@GetMapping(path = "/opens")
	public ResponseEntity<List<Order>> findAllOpens() {
		List<Order> orders = this.orderService.findAll();
		List<Order> ordersOpens = new ArrayList<>();
		for (Order order : orders) {
			if (order.getStatus().equals("aberto")) {
				ordersOpens.add(order);
			}
		}
		return ResponseEntity.ok(ordersOpens);
	}

	@PostMapping
	public ResponseEntity<Response<Order>> insert(@Valid @RequestBody Order order, BindingResult result) {
		order.setOrderDate(new Date());
		order.setOrderMonth(new Date().getMonth() + 1);
		order.setStatus("aberto");

		if (result.hasErrors()) {
			List<String> listError = new ArrayList<>();
			result.getAllErrors().forEach(e -> {
				listError.add(e.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(new Response<Order>(listError));
		}
		return ResponseEntity.ok(new Response<Order>(this.orderService.insert(order)));
	}

	@PutMapping(path = "checkout")
	@CrossOrigin
	public ResponseEntity<Response<Order>> checkoutOrder(@Valid @RequestBody Order order, BindingResult result) {
		order.setDateClose(new Date());
		if (result.hasErrors()) {
			List<String> listError = new ArrayList<>();
			result.getAllErrors().forEach(e -> {
				listError.add(e.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(new Response<Order>(listError));
		}
		return ResponseEntity.ok(new Response<Order>(this.orderService.updateOrder(order)));
	}

	@PutMapping(path = "delete")
	public ResponseEntity<Response<Order>> deleteOrder(@Valid @RequestBody Order order, BindingResult result) {
		order.setDateClose(new Date());
		if (result.hasErrors()) {
			List<String> listError = new ArrayList<>();
			result.getAllErrors().forEach(e -> {
				listError.add(e.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(new Response<Order>(listError));
		}
		return ResponseEntity.ok(new Response<Order>(this.orderService.updateOrder(order)));
	}

}
