package com.br.apiDivinaProvidencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apiDivinaProvidencia.documents.Product;
import com.br.apiDivinaProvidencia.responses.Response;
import com.br.apiDivinaProvidencia.services.StockService;

@RestController
@RequestMapping(path = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok(this.stockService.findAll());
	}

	@PostMapping
	public ResponseEntity<Response<Product>> insert(@RequestBody Product product) {
		return ResponseEntity.ok(new Response<Product>(this.stockService.insert(product)));
	}

	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable(name = "id") String id) {
		this.stockService.remove(id);
	}

	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product product) {
		return ResponseEntity.ok(this.stockService.update(product));
	}

	@PutMapping(path = "updateStock")
	public ResponseEntity<List<Product>> updateStock(@RequestBody List<Product> products) {
		products.stream().forEach(p -> this.stockService.update(p));
		return ResponseEntity.ok(this.stockService.findAll());
	}
}
