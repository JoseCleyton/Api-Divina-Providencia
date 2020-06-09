package com.br.apiDivinaProvidencia.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apiDivinaProvidencia.documents.Order;
import com.br.apiDivinaProvidencia.exception.NotFoundException;
import com.br.apiDivinaProvidencia.responses.Response;
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
	public Order findById(@PathVariable(name = "id") String id) throws NotFoundException {
		return this.orderService.findById(id).orElseThrow(NotFoundException::new);

	}

	@GetMapping(path = "/month/{number}")
	public ResponseEntity<Double> findMonth(@PathVariable(name = "number") int number) {
		return ResponseEntity.ok(this.orderService.findByOrderMonth(number));
	}

	@GetMapping(path = "/opens")
	public ResponseEntity<List<Order>> findAllOpens() {
		return ResponseEntity.ok(this.orderService.findByOpens());
	}

	@PostMapping
	public ResponseEntity<Response<Order>> insert(@Valid @RequestBody Order order, BindingResult result) {
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

	@PutMapping(path = "/checkout")
	public ResponseEntity<Response<Order>> checkoutOrder(@Valid @RequestBody Order order, BindingResult result) {
		if (result.hasErrors()) {
			List<String> listError = new ArrayList<>();
			result.getAllErrors().forEach(e -> {
				listError.add(e.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(new Response<Order>(listError));
		}
		return ResponseEntity.ok(new Response<Order>(this.orderService.updateOrder(order)));
	}

	@PutMapping(path = "cancel")
	public ResponseEntity<Response<Order>> cancelOrder(@Valid @RequestBody Order order, BindingResult result) {
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
